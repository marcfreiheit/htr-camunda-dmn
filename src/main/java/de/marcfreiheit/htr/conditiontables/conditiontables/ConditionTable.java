package de.marcfreiheit.htr.conditiontables.conditiontables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
public class ConditionTable {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(columnDefinition="TEXT")
    private String decisionTable;

    @NotBlank
    private String decisionKey;

    public ConditionTable() { }

    public ConditionTable(UUID id, String name, String decisionTable, String decisionKey) {
        this.id = id;
        this.name = name;
        this.decisionTable = decisionTable;
        this.decisionKey = decisionKey;
    }

    public String getDecisionTable() {
        return decisionTable;
    }

    public void setDecisionTable(String decisionTable) {
        this.decisionTable = decisionTable;
    }

    public String getDecisionKey() {
        return decisionKey;
    }

    public void setDecisionKey(String decisionKey) {
        this.decisionKey = decisionKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }
}
