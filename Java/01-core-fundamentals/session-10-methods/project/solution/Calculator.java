// Calculator.java — پاسخ پروژهٔ جلسه ۱۰
public class Calculator {

    static int add(int a, int b) { return a + b; }
    static double add(double a, double b) { return a + b; }
    static int subtract(int a, int b) { return a - b; }
    static double subtract(double a, double b) { return a - b; }
    static int multiply(int a, int b) { return a * b; }
    static double multiply(double a, double b) { return a * b; }
    static int divide(int a, int b) { return b == 0 ? 0 : a / b; }
    static double divide(double a, double b) { return b == 0 ? 0 : a / b; }

    static int power(int base, int exp) {
        int result = 1;
        for (int i = 0; i < exp; i++) result *= base;
        return result;
    }

    static int factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("add(3,4)=" + add(3, 4));
        System.out.println("add(1.5,2.5)=" + add(1.5, 2.5));
        System.out.println("power(2,10)=" + power(2, 10));
        System.out.println("factorial(5)=" + factorial(5));
        System.out.println("isPrime(17)=" + isPrime(17));
        System.out.println("isPrime(18)=" + isPrime(18));
    }
}
