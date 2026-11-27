// GradeManager.java — مینی‌پروژهٔ جمع‌بندی فاز ۱
import java.util.Scanner;

// ---------- مدل داده ----------
class Student {
    String name;
    int id;
    double[] grades;   // ۵ نمره

    Student(String name, int id, double[] grades) {
        this.name = name;
        this.id = id;
        this.grades = grades;
    }

    // میانگین نمرات
    double average() {
        double sum = 0;
        for (double g : grades) sum += g;
        return sum / grades.length;
    }

    // معادل حرفی
    String gradeLetter() {
        double avg = average();
        if (avg >= 17) return "A";
        if (avg >= 14) return "B";
        if (avg >= 12) return "C";
        return "F";
    }

    @Override
    public String toString() {
        return String.format("%-15s id=%-3d avg=%.1f grade=%s",
                name, id, average(), gradeLetter());
    }
}

// ---------- مدیریت لیست ----------
class StudentManager {
    Student[] students;
    int count;

    StudentManager(int capacity) {
        students = new Student[capacity];
    }

    void addStudent(Student s) {
        if (count >= students.length) {
            System.out.println("List is full!");
            return;
        }
        students[count++] = s;
    }

    void printAll() {
        System.out.println("\n=== Students ===");
        System.out.printf("%-15s %-5s %-8s %-6s%n", "Name", "ID", "Avg", "Grade");
        System.out.println("-".repeat(40));
        for (int i = 0; i < count; i++) {
            System.out.printf("%-15s %-5d %-8.1f %-6s%n",
                    students[i].name, students[i].id,
                    students[i].average(), students[i].gradeLetter());
        }
    }

    Student findByName(String name) {
        for (int i = 0; i < count; i++) {
            if (students[i].name.equalsIgnoreCase(name)) return students[i];
        }
        return null;
    }

    Student findById(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].id == id) return students[i];
        }
        return null;
    }

    void sortByAverage() {
        // bubble sort نزولی بر اساس میانگین
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - 1 - i; j++) {
                if (students[j].average() < students[j + 1].average()) {
                    Student tmp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = tmp;
                }
            }
        }
    }
}

public class GradeManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager gm = new StudentManager(10);   // ظرفیت ۱۰

        // دادهٔ نمونه
        gm.addStudent(new Student("Ali", 1, new double[]{18, 19, 17, 16, 18}));
        gm.addStudent(new Student("Sara", 2, new double[]{15, 16, 14, 15, 16}));
        gm.addStudent(new Student("Reza", 3, new double[]{10, 11, 9, 12, 10}));

        gm.printAll();
        gm.sortByAverage();
        System.out.println("\n=== Sorted by average ===");
        gm.printAll();

        // جستجو
        System.out.print("\nEnter name to search: ");
        String name = sc.nextLine();
        Student found = gm.findByName(name);
        if (found != null) {
            System.out.println("Found: " + found);
        } else {
            System.out.println("Not found: " + name);
        }
    }
}
