package com.example.algebraicevaluator.model;

public class ExpressionNode {

    private String value;
    private ExpressionNode left;
    private ExpressionNode right;

    public ExpressionNode(String value) {
        this.value = value;
    }

    public String getValue() { return value; }
    public ExpressionNode getLeft() { return left; }
    public ExpressionNode getRight() { return right; }

    public void setLeft(ExpressionNode left) { this.left = left; }
    public void setRight(ExpressionNode right) { this.right = right; }
}
