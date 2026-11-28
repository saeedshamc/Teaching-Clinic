// ValidationForm.java — پاسخ پروژهٔ جلسه ۳۱
public class ValidationForm {

    static class InvalidAgeException extends Exception {
        InvalidAgeException(String msg) { super(msg); }
    }
    static class InvalidEmailException extends Exception {
        InvalidEmailException(String msg) { super(msg); }
    }
    static class InvalidPasswordException extends Exception {
        InvalidPasswordException(String msg) { super(msg); }
    }

    static void validateAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) throw new InvalidAgeException("Age must be 0-150");
    }

    static void validateEmail(String email) throws InvalidEmailException {
        if (!email.contains("@") || !email.contains(".")) {
            throw new InvalidEmailException("Invalid email format");
        }
    }

    static void validatePassword(String password) throws InvalidPasswordException {
        if (password.length() < 8) throw new InvalidPasswordException("Too short");
        boolean hasUpper = false, hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isDigit(c)) hasDigit = true;
        }
        if (!hasUpper || !hasDigit) {
            throw new InvalidPasswordException("Need uppercase and digit");
        }
    }

    public static void main(String[] args) {
        // تست سن
        try { validateAge(-5); } catch (InvalidAgeException e) {
            System.out.println("Age error: " + e.getMessage());
        }
        try { validateAge(25); System.out.println("Age OK"); } catch (InvalidAgeException e) {
            System.out.println("Age error: " + e.getMessage());
        }

        // تست ایمیل
        try { validateEmail("invalid"); } catch (InvalidEmailException e) {
            System.out.println("Email error: " + e.getMessage());
        }
        try { validateEmail("ali@mail.com"); System.out.println("Email OK"); } catch (InvalidEmailException e) {
            System.out.println("Email error: " + e.getMessage());
        }

        // تست رمز
        try { validatePassword("123"); } catch (InvalidPasswordException e) {
            System.out.println("Password error: " + e.getMessage());
        }
        try { validatePassword("Abc12345"); System.out.println("Password OK"); } catch (InvalidPasswordException e) {
            System.out.println("Password error: " + e.getMessage());
        }
    }
}
