// Calculator.java — پاسخ پروژهٔ جلسه ۲۳
public class Calculator {

    private double result;

    // inner class — به result Calculator دسترسی دارد
    class Operation {
        void add(double a) { result += a; }
        void subtract(double a) { result -= a; }
        void multiply(double a) { result *= a; }
        void divide(double a) { if (a != 0) result /= a; }
        double getResult() { return result; }
    }

    // static nested class — مستقل از Calculator
    static class Formatter {
        static String format(double value) {
            return String.format("%.2f", value);
        }
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Calculator.Operation op = calc.new Operation();

        op.add(10);       // 0 + 10 = 10
        System.out.println("After add 10: " + Formatter.format(op.getResult()));

        op.add(5);        // 10 + 5 = 15
        System.out.println("After add 5: " + Formatter.format(op.getResult()));

        op.subtract(3);   // 15 - 3 = 12
        System.out.println("After subtract 3: " + Formatter.format(op.getResult()));

        op.multiply(2);   // 12 * 2 = 24
        System.out.println("After multiply 2: " + Formatter.format(op.getResult()));

        op.divide(4);     // 24 / 4 = 6
        System.out.println("After divide 4: " + Formatter.format(op.getResult()));
    }
}
