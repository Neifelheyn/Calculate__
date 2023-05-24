import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    private Calculator() { }
    public static double Result() {
        String formula = UserInput();
        return Start(formula);
    }
    private static String UserInput() {
        System.out.print("ENTER YOUR FORMULA : ");
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine().replaceAll("\\s", "");
    }
    private static boolean isDigit(char ch) { return ch >= '0' && ch <= '9'; }
    private static boolean isOperator(char c) {
         return c == '+' || c == '-' || c == '*' || c == '/';
    }
    private static boolean Priority(char op1, char op2) {
        return ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')
                || (op1 == '+' || op1 == '-') && (op2 == '+' || op2 == '-'));
    }
    private static double Start(String formula) {
        Stack<Double> stackNumbers = new Stack<>();
        Stack<Character> stackOperations = new Stack<>();

        int i = 0;
        while (i < formula.length()) {
            char c = formula.charAt(i);

            if (isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < formula.length() && isDigit(formula.charAt(i))) {
                    sb.append(formula.charAt(i));
                    i++;
                }

                double number = Double.parseDouble(sb.toString());
                stackNumbers.push(number);

            } else if (isOperator(c)) {
                while (!stackOperations.isEmpty() && Priority(stackOperations.peek(), c)) {
                    double number2 = stackNumbers.pop();
                    double number1 = stackNumbers.pop();
                    char operator = stackOperations.pop();
                    double result = performOperation(operator, number1, number2);
                    stackNumbers.push(result);
                }
                stackOperations.push(c);
                i++;
            } else if (c == '(') {
                stackOperations.push(c);
                i++;
            } else if (c == ')') {
                while (!stackOperations.isEmpty() && stackOperations.peek() != '(') {
                    double number2 = stackNumbers.pop();
                    double number1 = stackNumbers.pop();
                    char operator = stackOperations.pop();
                    double result = performOperation(operator, number1, number2);
                    stackNumbers.push(result);
                }
                if (!stackOperations.isEmpty() && stackOperations.peek() == '(') {
                    stackOperations.pop();
                }
                i++;
            } else {
                throw new IllegalArgumentException("Неверный символ: " + c);
            }
        }
        while (!stackOperations.isEmpty()) {
            double number2 = stackNumbers.pop();
            double number1 = stackNumbers.pop();
            char operator = stackOperations.pop();
            double result = performOperation(operator, number1, number2);
            stackNumbers.push(result);
        }
        return stackNumbers.pop();
    }
    private static double performOperation(char operator, double number1, double number2) {
        double result = 0;

        switch (operator) {
            case '+' -> result = number1 + number2;
            case '-' -> result = number1 - number2;
            case '*' -> result = number1 * number2;
            case '/' -> {
                if (number2 != 0) {
                    result = number1 / number2;
                } else {
                    throw new IllegalArgumentException("ERROR: Division on zero is not possible");
                }
            }
            default -> {}
        }
        return result;
    }
}
