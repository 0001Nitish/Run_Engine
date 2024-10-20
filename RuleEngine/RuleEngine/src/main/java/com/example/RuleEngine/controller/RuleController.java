package com.example.RuleEngine.controller;

import com.example.RuleEngine.model.Rule;
import com.example.RuleEngine.model.Data;
import com.example.RuleEngine.model.Node;
import com.example.RuleEngine.service.RuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class RuleController {

    private final RuleService ruleService;

    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping("/create")
    public Rule createRule(@RequestBody String ruleString) {
        return ruleService.saveRule(ruleString);
    }

    @GetMapping("/{id}")
    public Node getRule(@PathVariable Long id) {
        return ruleService.getRuleById(id);
    }

    @PostMapping("/combine")
    public Node combineRules(@RequestBody List<String> rules) {
        return ruleService.combineRules(rules);
    }

    @GetMapping("/evaluate/{ruleId}")
    public boolean evaluateRule(@PathVariable Long ruleId,
                                @RequestParam int age,
                                @RequestParam String department,
                                @RequestParam double salary,
                                @RequestParam int experience) {
        Node rule = ruleService.getRuleById(ruleId);
        Data data = new Data(age, department, salary, experience);
        return ruleService.evaluateRule(rule, data);
    }
}
