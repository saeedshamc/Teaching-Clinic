// RankingSystem.java — پاسخ پروژهٔ جلسه ۳۰
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Student {
    String name;
    int score;
    int rank;

    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

public class RankingSystem {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Ali", 88));
        students.add(new Student("Sara", 82));
        students.add(new Student("Mina", 95));
        students.add(new Student("Reza", 91));
        students.add(new Student("Neda", 76));

        // مرتب‌سازی بر اساس score نزولی
        students.sort(Comparator.comparingInt((Student s) -> s.score).reversed());

        // تخصیص rank
        for (int i = 0; i < students.size(); i++) {
            students.get(i).rank = i + 1;
        }

        // نمایش
        System.out.printf("%-5s %-10s %-6s%n", "Rank", "Name", "Score");
        System.out.println("-".repeat(25));
        for (Student s : students) {
            System.out.printf("%-5d %-10s %-6d%n", s.rank, s.name, s.score);
        }

        // با Iterator — فقط top 3
        System.out.println("\n=== Top 3 ===");
        int count = 0;
        for (Student s : students) {
            if (count >= 3) break;
            System.out.println(s.rank + ". " + s.name + " — " + s.score);
            count++;
        }
    }
}
