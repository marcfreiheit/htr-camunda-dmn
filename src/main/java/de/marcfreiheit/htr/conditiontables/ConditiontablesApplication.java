package de.marcfreiheit.htr.conditiontables;

import org.camunda.bpm.dmn.engine.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConditiontablesApplication {

    public static void main(String[] args) {

        DmnEngineConfiguration
            .createDefaultDmnEngineConfiguration()
            .buildEngine();

        SpringApplication.run(ConditiontablesApplication.class, args);
    }

}
