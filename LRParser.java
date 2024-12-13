import java.util.*;

class LRParser {

    public float evaluate(String expression) {
        List<String> tokens = tokenize(expression);
        return parseExpression(tokens);
    }

    // Tokenizes the input expression
    private List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);

            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            if (Character.isDigit(c) || c == '.') {
                StringBuilder number = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    number.append(expression.charAt(i));
                    i++;
                }
                tokens.add(number.toString());
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
                tokens.add(String.valueOf(c));
                i++;
            } else {
                throw new IllegalArgumentException("Invalid character in expression: " + c);
            }
        }
        return tokens;
    }

    // LR parsing using a stack
    private float parseExpression(List<String> tokens) {
        Stack<Float> values = new Stack<>();
        Stack<String> operators = new Stack<>();
        int i = 0;

        while (i < tokens.size()) {
            String token = tokens.get(i);

            if (isNumber(token)) {
                values.push(Float.valueOf(token));
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    processOperator(values, operators.pop());
                }
                if (operators.isEmpty() || !operators.pop().equals("(")) {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                    processOperator(values, operators.pop());
                }
                operators.push(token);
            } else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }
            i++;
        }

        while (!operators.isEmpty()) {
            processOperator(values, operators.pop());
        }

        if (values.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return values.pop();
    }

    private boolean isNumber(String token) {
        try {
            Float.valueOf(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private int precedence(String operator) {
        if (operator.equals("+") || operator.equals("-")) {
            return 1;
        } else if (operator.equals("*") || operator.equals("/")) {
            return 2;
        } else {
            return 0;
        }
    }

    private void processOperator(Stack<Float> values, String operator) {
        if (values.size() < 2) {
            throw new IllegalArgumentException("Invalid expression");
        }

        float b = values.pop();
        float a = values.pop();

        switch (operator) {
            case "+":
                values.push(a + b);
                break;
            case "-":
                values.push(a - b);
                break;
            case "*":
                values.push(a * b);
                break;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                values.push(a / b);
                break;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}
