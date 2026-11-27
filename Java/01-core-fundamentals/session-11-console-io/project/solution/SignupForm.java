// SignupForm.java — پاسخ پروژهٔ جلسه ۱۱
import java.util.Scanner;

public class SignupForm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();   // بافر \n را بخور

        System.out.print("Height: ");
        double height = sc.nextDouble();
        sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        // اعتبارسنجی ساده
        if (name.isBlank()) {
            System.out.println("Error: name is required");
            return;
        }
        if (age < 0 || age > 150) {
            System.out.println("Error: invalid age");
            return;
        }
        if (!email.contains("@")) {
            System.out.println("Error: invalid email");
            return;
        }

        System.out.println("---");
        System.out.printf("Name: %s, Age: %d, Height: %.2fm, Email: %s%n",
                name, age, height, email);
    }
}
