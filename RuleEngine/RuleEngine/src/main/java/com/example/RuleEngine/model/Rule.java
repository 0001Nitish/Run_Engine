package com.example.RuleEngine.model;



import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rules")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private List<Rule> rules;
    @Column(nullable = false)
    private String ruleString;

    @Column(nullable = false)
    private String ast; // You can choose to store the AST as a string or in a different structure

    public Rule() {}

    public Rule(String ruleString, String ast) {
        this.ruleString = ruleString;
        this.ast = ast;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleString() {
        return ruleString;
    }

    public void setRuleString(String ruleString) {
        this.ruleString = ruleString;
    }

    public String getAst() {
        return ast;
    }

    public void setAst(String ast) {
        this.ast = ast;
    }
}
