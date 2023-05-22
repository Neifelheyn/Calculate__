import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    private static final char[] ops = new char[]{'*', '/', '+', '-'};

    public Calculator() { }

    public void Start() {
        String formula = UserInput();

        double result = Calculeted(formula);
        System.out.println(result);
    }

    private String UserInput() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine().replaceAll("\\s", "");
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private double Calculeted(String formula) {
        ArrayList<Double> lNumbers = new ArrayList<>();
        ArrayList<Character> lOperations = new ArrayList<>();

        StringBuilder number = new StringBuilder(String.valueOf(formula.charAt(0)));
        for (int i = 1; i < formula.length(); i++) {
            char symbol = formula.charAt(i);
            if (isDigit(symbol)) {
                number.append(symbol);
            } else {
                lNumbers.add(Double.parseDouble(number.toString()));
                lOperations.add(symbol);
                number.setLength(0);
            }
        }
        lNumbers.add(Double.parseDouble(number.toString()));

        for (int i = 0; i < ops.length; ) {
            int index = lOperations.indexOf(ops[i]);
            if (index < 0) {
                i++;
                continue;
            }

            double number1 = lNumbers.get(index);
            double number2 = lNumbers.get(index + 1);
            char operation = lOperations.get(index);
            double result = 0;

            switch (operation) {
                case '+' -> result = number1 + number2;
                case '-' -> result = number1 - number2;
                case '*' -> result = number1 * number2;
                case '/' -> {
                    if (number2 != 0) {
                        result = number1 / number2;
                    } else {
                        System.out.println("ZERO");
                    }
                }


                default -> {
                }
            }

            lNumbers.remove(index);
            lNumbers.remove(index);

            lOperations.remove(index);

            lNumbers.add(index, result);
        }

        return lNumbers.get(0);
    }
}
