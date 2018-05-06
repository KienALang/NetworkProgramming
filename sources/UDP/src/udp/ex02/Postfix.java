package udp.ex02;

import java.util.ArrayList;
import java.util.Stack;

public class Postfix {
    private ArrayList<String> elements;
    private ArrayList<String> postfix;
    private String expression;

    public Postfix(String expression) {
        this.expression = expression;
        if (elements == null) {
            elements = new ArrayList<>();
        }

        if (postfix == null) {
            postfix = new ArrayList<>();
        }
    }

    private void setElements(String expression) {
        String element = "";
        for (char c : expression.toCharArray()) {
            if ("0123456789.".contains(String.valueOf(c))) {
                element += c;
            } else {
                if (element != "") {
                    elements.add(element);
                    element = "";
                }

                elements.add(String.valueOf(c));
            }
        }

        if (element != "") {
            elements.add(element);
        }
    }

    private void setPostfix(ArrayList<String> infix) {
        Stack<String> temp = new Stack<>();
        for (String element : infix) {
            if ("+-*/".contains(element)) {
                while (!temp.isEmpty() && getPriority(element) <= getPriority(temp.peek())) {
                    postfix.add(temp.pop());
                }
                temp.push(element);

            } else if ("(".contains(element)) {
                temp.push(element);
            } else if (")".contains(element)) {
                while (!temp.peek().equals("(")) {
                    postfix.add(temp.pop());
                }

                temp.pop(); // throw the open bracket away.
            } else {
                postfix.add(element);
            }
        }

        while (!temp.isEmpty()) {
            postfix.add(temp.pop());
        }
    }

    private int getPriority(String operator) {
        int value = 2; // is the priority of + and -
        switch (operator) {
            case "*":
            case "/":
                value = 3;
                break;
            case "(":
                value = 1;
                break;
        }

        return value;
    }

    private String calculate(String value1, String value2, String operator) {
        double val1 = Double.parseDouble(value1);
        double val2 = Double.parseDouble(value2);
        Double val = 0.0;

        switch (operator) {
            case "+":
                val = val1 + val2;
                break;
            case "-":
                val = val1 - val2;
                break;
            case "*":
                val = val1 * val2;
                break;
            case "/":
                val = val1 / val2;
                break;
        }

        return val.toString();
    }

    public String getValue() {
        setElements(expression);
        setPostfix(elements);
        Stack<String> temp = new Stack<>();

        for (String element : postfix) {
            if ("+-*/".contains(element)) {
                String value2 = temp.pop();
                String value1 = temp.pop();
                temp.push(calculate(value1, value2, element));
            } else {
                temp.push(element);
            }
        }

        return temp.pop();
    }
}
