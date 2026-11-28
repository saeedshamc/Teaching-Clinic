// AdvancedCalculator.java — پاسخ کامل پروژهٔ جلسه ۱۲۵
// قابل کامپایل با javac — بدون dependency
public class AdvancedCalculator {

    public int add(int a, int b) { return a + b; }
    public int subtract(int a, int b) { return a - b; }
    public int multiply(int a, int b) { return a * b; }
    public int divide(int a, int b) {
        if (b == 0) throw new ArithmeticException("Division by zero");
        return a / b;
    }
    public double power(double base, int exponent) { return Math.pow(base, exponent); }
    public boolean isEven(int n) { return n % 2 == 0; }
    public boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    public int factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative factorial");
        int result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }
    public int gcd(int a, int b) {
        while (b != 0) { int temp = b; b = a % b; a = temp; }
        return Math.abs(a);
    }
}
