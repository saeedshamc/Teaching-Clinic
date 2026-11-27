// Calc.java — پاسخ پروژهٔ جلسه ۰۵
// اجرا: java Calc 10 3 +
public class Calc {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Calc <a> <b> <op>");
            return;
        }
        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        String op = args[2];
        double result;

        switch (op) {
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "*" -> result = a * b;
            case "/" -> {
                if (b == 0) { System.out.println("Error: division by zero"); return; }
                result = a / b;
            }
            case "%" -> {
                if (b == 0) { System.out.println("Error: mod by zero"); return; }
                result = a % b;
            }
            default -> { System.out.println("Unknown op: " + op); return; }
        }
        System.out.println(result);
    }
}
