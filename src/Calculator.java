import java.util.ArrayList;
import java.util.Scanner;


public class Calculator {

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

int i = 0;
        while (lNumbers.size() != 1) {

            double result = 0;

            switch (lOperations.get(i)) {
                case '+':
                    result = lNumbers.get(0) + lNumbers.get(1);
                    break;
                case '-':
                    result = lNumbers.get(0) - lNumbers.get(1);
                    break;
                case '*':
                    result = lNumbers.get(0) * lNumbers.get(1);
                    break;
                case '/':
                    result = lNumbers.get(0) / lNumbers.get(1);
                    break;
                default:
                    break;
            }
            lNumbers.remove(0);
            lNumbers.remove(0);
            lNumbers.add(0, result);
            i++;
        }
        return lNumbers.get(0);
    }
}
