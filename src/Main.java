public class Main {
    public static void main(String[] args) {

        try {
            double result = Calculator.Result();
            System.out.println(result);
        } catch (Exception ex) {
            System.out.println("ERROR: Unknown");
        }
    }
}