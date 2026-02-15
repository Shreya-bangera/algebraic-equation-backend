package com.example.algebraicevaluator.model;

public class Equations {

    private int id;
    private String expression;
    private ExpressionNode root;

    public Equations() {}

    public Equations(int id, String expression, ExpressionNode root) {
        this.id = id;
        this.expression = expression;
        this.root = root;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getExpression() { return expression; }
    public void setExpression(String expression) { this.expression = expression; }

    public ExpressionNode getRoot() { return root; }
    public void setRoot(ExpressionNode root) { this.root = root; }
}
