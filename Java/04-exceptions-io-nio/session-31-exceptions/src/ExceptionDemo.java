// ExceptionDemo.java — try-catch-finally، throw/throws، custom exception
public class ExceptionDemo {

    // ---------- custom exceptions ----------
    static class InvalidAgeException extends Exception {   // checked
        InvalidAgeException(String message) { super(message); }
    }

    static class InsufficientFundsException extends RuntimeException {  // unchecked
        InsufficientFundsException(String message) { super(message); }
    }

    // ---------- متد با throws ----------
    static void validateAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("Invalid age: " + age);
        }
    }

    static void withdraw(double balance, double amount) {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        System.out.println("Withdrew " + amount);
    }

    // ---------- متد که checked exception می‌دهد ----------
    static void riskyOperation() throws Exception {
        throw new Exception("Something went wrong");
    }

    public static void main(String[] args) {
        // ---------- try-catch-finally ----------
        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException: " + e.getMessage());
        } finally {
            System.out.println("Finally always runs");
        }

        // ---------- چند catch ----------
        try {
            String s = null;
            s.length();   // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught");
        } catch (Exception e) {
            System.out.println("Generic exception");
        }

        // ---------- throw و throws ----------
        try {
            validateAge(-5);
        } catch (InvalidAgeException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // ---------- unchecked exception ----------
        try {
            withdraw(100, 200);
        } catch (InsufficientFundsException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // ---------- finally با return ----------
        System.out.println("Result: " + finallyDemo());
    }

    static int finallyDemo() {
        try {
            return 42;
        } finally {
            System.out.println("Finally before return");
        }
    }
}
