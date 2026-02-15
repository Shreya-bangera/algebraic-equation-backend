package com.example.algebraicevaluator.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algebraicevaluator.model.Equations;
import com.example.algebraicevaluator.model.ExpressionNode;
import com.example.algebraicevaluator.service.ExpressionService;

@RestController
@RequestMapping("/api/equations")
public class HelloController {

    private ConcurrentHashMap<Integer, Equations> store = new ConcurrentHashMap<>();
    private AtomicInteger idCounter = new AtomicInteger(1);
    private ExpressionService service = new ExpressionService();

    @PostMapping("/store")
    public Equations storeEquation(@RequestBody Map<String, String> body) {

        String expression = body.get("equation");

        List<String> postfix = service.infixToPostfix(expression);
        ExpressionNode root = service.buildTree(postfix);

        int id = idCounter.getAndIncrement();
        Equations eq = new Equations(id, expression, root);

        store.put(id, eq);
        return eq;
    }

    @GetMapping
    public Collection<Equations> getAll() {
        return store.values();
    }

    @PostMapping("/{id}/evaluate")
    public double evaluate(
            @PathVariable int id,
            @RequestBody Map<String, Double> variables) {

        Equations eq = store.get(id);
        if (eq == null)
            throw new RuntimeException("Equation not found");

        return service.evaluate(eq.getRoot(), variables);
    }
}
