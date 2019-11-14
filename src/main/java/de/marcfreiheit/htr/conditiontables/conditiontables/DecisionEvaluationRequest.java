package de.marcfreiheit.htr.conditiontables.conditiontables;

import javax.validation.constraints.NotBlank;
import java.util.*;

class Variable {

    private String key;
    private Object value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

public class DecisionEvaluationRequest {

    private List<Variable> variables;

    public DecisionEvaluationRequest() { }

    public DecisionEvaluationRequest(List<Variable> variables) {
        this.variables = variables;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }
}
