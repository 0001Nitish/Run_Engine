package com.example.RuleEngine.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.RuleEngine.model.Rule;
import com.example.RuleEngine.model.Data;
import com.example.RuleEngine.model.Node;
import com.example.RuleEngine.repository.RuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {

    private static final Logger logger = LoggerFactory.getLogger(RuleService.class);

    private final RuleRepository ruleRepository;

    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public Rule saveRule(String ruleString) {
        Node astNode = createRule(ruleString); // Create the AST
        Rule rule = new Rule(ruleString, astNode.toString()); // Store AST as a string (modify as needed)
        return ruleRepository.save(rule);
    }

    public Node getRuleById(Long id) {
        Rule rule = ruleRepository.findById(id).orElseThrow(() -> new RuntimeException("Rule not found"));
        return createRule(rule.getRuleString()); // Convert rule string back to AST
    }

    public Node createRule(String ruleString) {
        // Simplified parsing logic
        if (ruleString.contains("AND")) {
            String[] parts = ruleString.split("AND", 2);
            Node leftNode = createRule(parts[0].trim());
            Node rightNode = createRule(parts[1].trim());
            return new Node("operator", leftNode, rightNode, "AND");
        } else if (ruleString.contains("OR")) {
            String[] parts = ruleString.split("OR", 2);
            Node leftNode = createRule(parts[0].trim());
            Node rightNode = createRule(parts[1].trim());
            return new Node("operator", leftNode, rightNode, "OR");
        } else {
            return new Node("operand", null, null, ruleString.trim());
        }
    }

    public Node combineRules(List<String> rules) {
        Node root = new Node("operator", null, null, "AND");
        Node current = root;

        for (String rule : rules) {
            Node node = createRule(rule);
            current.setRight(node); // Link new node
            current = node; // Move to next insertion
        }
        return root;
    }

//    public boolean evaluateRule(Node node, Data data) {
//        if ("operand".equals(node.getType())) {
//            return evaluateExpression(node.getValue(), data);
//        } else {
//            boolean leftValue = evaluateRule(node.getLeft(), data);
//            boolean rightValue = evaluateRule(node.getRight(), data);
//
//            return "AND".equals(node.getValue()) ? leftValue && rightValue : leftValue || rightValue;
//        }
//    }
//
//    private boolean evaluateExpression(String expression, Data data) {
//        String evaluated = expression
//                .replace("age", String.valueOf(data.getAge()))
//                .replace("department", "'" + data.getDepartment() + "'")
//                .replace("salary", String.valueOf(data.getSalary()))
//                .replace("experience", String.valueOf(data.getExperience()));
//
//        return evaluateCondition(evaluated);
//    }
//
//    private boolean evaluateCondition(String condition) {
//        // Simplified condition evaluation (replace with actual logic)
//        // NOTE: You might want to use a safer evaluation method in production
//        return true; // Placeholder
//    }
public boolean evaluateRule(Node rule, Data data) {
    if (rule == null) {
        return false; // Handle null cases
    }

    switch (rule.getType()) {
        case "operand":
            return evaluateCondition(rule, data); // Evaluate the condition
        case "operator":
            return evaluateOperator(rule, data); // Evaluate AND/OR
        default:
            throw new IllegalArgumentException("Invalid rule type: " + rule.getType());
    }
}

    private boolean evaluateCondition(Node condition, Data data) {
        String conditionStr = condition.getValue();
        // Parse the condition string (e.g., "age > 30") and evaluate based on the data
        // Example: implement your own parsing and evaluation logic
        // This is a simple demonstration; you might want to use a library for complex expressions

        // Pseudo code:
        logger.info("Evaluating condition: {}", conditionStr);
        // Print values for debugging
        logger.info("Data: age={}, department={}, salary={}, experience={}",
                data.getAge(), data.getDepartment(), data.getSalary(), data.getExperience());

        if (conditionStr.contains("age")) {
            String operator = conditionStr.split(" ")[1];
            int ageValue = Integer.parseInt(conditionStr.split(" ")[2]);
            return evaluateNumericCondition(data.getAge(), operator, ageValue);
        }
        // Implement similar checks for department, salary, experience...

        return false; // Default case
    }

    private boolean evaluateNumericCondition(int actualValue, String operator, int comparisonValue) {
        switch (operator) {
            case ">":
                return actualValue > comparisonValue;
            case "<":
                return actualValue < comparisonValue;
            case "==":
                return actualValue == comparisonValue;
            // Add other operators as necessary
            default:
                return false;
        }
    }

    private boolean evaluateOperator(Node operatorNode, Data data) {
        boolean leftValue = evaluateRule(operatorNode.getLeft(), data);
        boolean rightValue = evaluateRule(operatorNode.getRight(), data);

        switch (operatorNode.getValue()) {
            case "AND":
                return leftValue && rightValue;
            case "OR":
                return leftValue || rightValue;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operatorNode.getValue());
        }
    }
}
