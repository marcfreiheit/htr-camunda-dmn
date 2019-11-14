package de.marcfreiheit.htr.conditiontables.conditiontables;

import de.marcfreiheit.htr.conditiontables.exceptions.ResourceNotFoundException;
import org.apache.commons.lang3.StringEscapeUtils;
import org.camunda.bpm.dmn.engine.*;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ConditionTableController {

    @Autowired
    ConditionTableRepository conditionTableRepository;

    @GetMapping("/conditiontables")
    public List<ConditionTable> getAllConditionTables() {
        return conditionTableRepository.findAll();
    }

    @PostMapping("/conditiontables")
    public ConditionTable createConditionTable(@Valid @RequestBody ConditionTable conditionTable) {
        return conditionTableRepository.save(conditionTable);
    }

    @GetMapping("/conditiontables/{id}")
    public ConditionTable getConditionTable(@PathVariable(value = "id") UUID id) {
        return conditionTableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ConditionTable", "id", id));
    }

    @PostMapping("/conditiontables/{id}/evaluate")
    public Object evaluateDecision(@PathVariable(value = "id") UUID id, @Valid @RequestBody DecisionEvaluationRequest decisionEvaluationRequest) {

        ConditionTable conditionTable = conditionTableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ConditionTable", "id", id));


        var decisionTable = conditionTable.getDecisionTable();
        var decisionKey = conditionTable.getDecisionKey();

        var requestVariables = decisionEvaluationRequest.getVariables();

        var dmnEngine = DmnEngineConfiguration
                .createDefaultDmnEngineConfiguration()
                .buildEngine();

        InputStream resourceInputStream;

        var bytes = StringEscapeUtils.unescapeXml(decisionTable).getBytes(Charset.forName("UTF-8"));
        resourceInputStream = new ByteArrayInputStream(bytes);
        DmnDecision decision = dmnEngine.parseDecision(decisionKey, resourceInputStream);

        VariableMap variables = Variables.createVariables();

        for(Variable variable: requestVariables) variables.putValue(variable.getKey(), variable.getValue());

        DmnDecisionResult decisionResult = dmnEngine.evaluateDecision(decision, variables);

        DmnDecisionResultEntries ruleResult = decisionResult.getSingleResult();

        double result = (double) ruleResult.get("price");
        String error = (String) ruleResult.get("error");

        return new DecisionEvaluationResponse(result, error);
    }
}
