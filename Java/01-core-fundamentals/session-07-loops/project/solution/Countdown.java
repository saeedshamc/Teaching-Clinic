// Countdown.java — پاسخ پروژهٔ جلسه ۰۷
public class Countdown {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Countdown <number>");
            return;
        }
        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Error: not a number");
            return;
        }
        if (n <= 0) {
            System.out.println("Error: number must be positive");
            return;
        }
        for (int i = n; i >= 1; i--) {
            System.out.println(i);
        }
        System.out.println("Liftoff!");
    }
}
