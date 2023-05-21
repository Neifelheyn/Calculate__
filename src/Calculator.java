import java.util.ArrayList;
import java.util.Scanner;


public class Calculator {
    char[] ops = new char[]{'*', '/', '+', '-'};

    Calculator() {
    }


    public void Start() {
        String formula = UserInput();
        double resultat = Calculeted(formula);
        System.out.println(resultat);
    }

    private String UserInput() {
        String formula = "";
        Scanner keyboard = new Scanner(System.in);
        formula = keyboard.nextLine();
        return formula;
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private double Calculeted(String formula) {
        ArrayList<Double> lNumbers = new ArrayList<>();
        ArrayList<Character> lOperations = new ArrayList<>();

        formula = formula.replaceAll("\\s", "");

        StringBuilder number = new StringBuilder();
        for (int i = 0; i < formula.length(); i++) {
            if (isDigit(formula.charAt(i))) {
                number.append(formula.charAt(i));
            } else {
                lNumbers.add(Double.parseDouble(number.toString()));
                lOperations.add(formula.charAt(i));
                number.setLength(0);
            }
        }
        lNumbers.add(Double.parseDouble(number.toString()));

        while (lNumbers.size() != 1) {


            for (int i = 0; i < ops.length; i++) {
                int index = lOperations.indexOf(ops[i]);
                if (index < 0)
                    continue;

                double result = 0;
                switch (lOperations.get(index)) {
                    case '+':
                        result = lNumbers.get(index) + lNumbers.get(index + 1);
                        break;
                    case '-':
                        result = lNumbers.get(index) - lNumbers.get(index + 1);
                        break;
                    case '*':
                        result = lNumbers.get(index) * lNumbers.get(index + 1);
                        break;
                    case '/':
                        result = lNumbers.get(index) / lNumbers.get(index + 1);
                        break;
                    default:
                        break;
                }
                lNumbers.remove(index);
                lNumbers.remove(index);
                lOperations.remove(index);
                lNumbers.add(index, result);
            }
        }
        return lNumbers.get(0);
    }
}
