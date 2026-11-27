// GradeManagerFull.java — پاسخ کامل پروژهٔ جلسه ۱۵
import java.util.Scanner;

class Student {
    String name;
    int id;
    double[] grades;

    Student(String name, int id, double[] grades) {
        this.name = name;
        this.id = id;
        this.grades = grades;
    }

    double average() {
        double sum = 0;
        for (double g : grades) sum += g;
        return sum / grades.length;
    }

    String gradeLetter() {
        double avg = average();
        if (avg >= 17) return "A";
        if (avg >= 14) return "B";
        if (avg >= 12) return "C";
        return "F";
    }
}

class GradeManager {
    Student[] students;
    int count;

    GradeManager(int capacity) { students = new Student[capacity]; }

    void addStudent(Student s) {
        if (count >= students.length) { System.out.println("Full!"); return; }
        students[count++] = s;
    }

    void removeById(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].id == id) {
                for (int j = i; j < count - 1; j++) students[j] = students[j + 1];
                students[--count] = null;
                return;
            }
        }
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
        for (int i = 0; i < count; i++)
            if (students[i].name.equalsIgnoreCase(name)) return students[i];
        return null;
    }

    Student findById(int id) {
        for (int i = 0; i < count; i++)
            if (students[i].id == id) return students[i];
        return null;
    }

    void printAbove(double threshold) {
        System.out.println("\n=== Above " + threshold + " ===");
        for (int i = 0; i < count; i++)
            if (students[i].average() >= threshold)
                System.out.printf("%-15s avg=%.1f%n", students[i].name, students[i].average());
    }

    void sortByAverage() {
        for (int i = 0; i < count - 1; i++)
            for (int j = 0; j < count - 1 - i; j++)
                if (students[j].average() < students[j + 1].average()) {
                    Student tmp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = tmp;
                }
    }

    void printStats() {
        if (count == 0) return;
        double totalAvg = 0, maxAvg = 0;
        int maxId = -1;
        for (int i = 0; i < count; i++) {
            double avg = students[i].average();
            totalAvg += avg;
            if (avg > maxAvg) { maxAvg = avg; maxId = students[i].id; }
        }
        System.out.printf("\nTotal: %d, Class avg: %.1f, Top: id=%d (%.1f)%n",
                count, totalAvg / count, maxId, maxAvg);
    }
}

public class GradeManagerFull {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GradeManager gm = new GradeManager(10);

        // دادهٔ نمونه
        gm.addStudent(new Student("Ali", 1, new double[]{18,19,17,16,18}));
        gm.addStudent(new Student("Sara", 2, new double[]{15,16,14,15,16}));
        gm.addStudent(new Student("Reza", 3, new double[]{10,11,9,12,10}));
        gm.addStudent(new Student("Mina", 4, new double[]{19,18,20,19,18}));

        gm.printAll();
        gm.sortByAverage();
        System.out.println("\n=== Sorted ===");
        gm.printAll();
        gm.printAbove(15);
        gm.printStats();

        System.out.print("\nSearch name: ");
        String name = sc.nextLine();
        Student found = gm.findByName(name);
        System.out.println(found != null ? "Found: " + found.name + " avg=" + found.average() : "Not found");
    }
}
