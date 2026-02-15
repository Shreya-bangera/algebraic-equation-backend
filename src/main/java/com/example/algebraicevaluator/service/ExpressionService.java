package com.example.algebraicevaluator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.example.algebraicevaluator.model.ExpressionNode;

public class ExpressionService {

    private int precedence(String op) {
        switch (op) {
            case "+": case "-": return 1;
            case "*": case "/": return 2;
            case "^": return 3;
        }
        return 0;
    }

    private boolean isOperator(String token) {
        return token.matches("[+\\-*/^]");
    }

    // Tokenizer (handles x^2 + y^2 - 4 without spaces)
    private List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isLetterOrDigit(c) || c == '.') {
                number.append(c);
            } else {
                if (number.length() > 0) {
                    tokens.add(number.toString());
                    number.setLength(0);
                }
                if (!Character.isWhitespace(c)) {
                    tokens.add(String.valueOf(c));
                }
            }
        }

        if (number.length() > 0) {
            tokens.add(number.toString());
        }

        return tokens;
    }

    public List<String> infixToPostfix(String expression) {
        List<String> tokens = tokenize(expression);
        Stack<String> stack = new Stack<>();
        List<String> output = new ArrayList<>();

        for (String token : tokens) {
            if (Character.isLetterOrDigit(token.charAt(0))) {
                output.add(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                stack.pop();
            } else if (isOperator(token)) {
                while (!stack.isEmpty() &&
                        precedence(stack.peek()) >= precedence(token)) {
                    output.add(stack.pop());
                }
                stack.push(token);
            }
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }

    public ExpressionNode buildTree(List<String> postfix) {
        Stack<ExpressionNode> stack = new Stack<>();

        for (String token : postfix) {
            ExpressionNode node = new ExpressionNode(token);

            if (isOperator(token)) {
                node.setRight(stack.pop());
                node.setLeft(stack.pop());
            }

            stack.push(node);
        }

        return stack.pop();
    }

    public double evaluate(ExpressionNode node, Map<String, Double> variables) {

        if (node.getLeft() == null && node.getRight() == null) {

            if (Character.isLetter(node.getValue().charAt(0))) {
                Double val = variables.get(node.getValue());
                if (val == null)
                    throw new RuntimeException("Missing variable: " + node.getValue());
                return val;
            }

            return Double.parseDouble(node.getValue());
        }

        double left = evaluate(node.getLeft(), variables);
        double right = evaluate(node.getRight(), variables);

        switch (node.getValue()) {
            case "+": return left + right;
            case "-": return left - right;
            case "*": return left * right;
            case "/": return left / right;
            case "^": return Math.pow(left, right);
        }

        throw new RuntimeException("Invalid operator");
    }
}
