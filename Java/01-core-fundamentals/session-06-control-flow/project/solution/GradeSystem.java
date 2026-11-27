// GradeSystem.java — پاسخ پروژهٔ جلسه ۰۶
import java.util.Scanner;

public class GradeSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter score (0-100): ");
        int score = sc.nextInt();

        if (score < 0 || score > 100) {
            System.out.println("Error: invalid score");
            return;
        }

        // تبدیل نمره به grade با if/else if
        String grade;
        if (score >= 90) grade = "A";
        else if (score >= 80) grade = "B";
        else if (score >= 70) grade = "C";
        else if (score >= 60) grade = "D";
        else grade = "F";

        // پیام با switch expression روی رشته
        String msg = switch (grade) {
            case "A" -> "Excellent!";
            case "B" -> "Good";
            case "C" -> "Average";
            case "D" -> "Below average";
            default -> "Failed";
        };

        System.out.println("Grade: " + grade + " — " + msg);
    }
}
