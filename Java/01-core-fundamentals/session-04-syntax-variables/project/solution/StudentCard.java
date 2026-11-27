// StudentCard.java — پاسخ پروژهٔ جلسه ۰۴
public class StudentCard {
    // فیلدهای instance — هر دانش‌آموز مقادیر خودش را دارد
    String firstName;
    String lastName;
    int age;
    double gpa;

    // ثابت — سقف منطقی سن
    static final int MAX_AGE = 100;

    // سازنده — برای مقداردهی اولیه (جزئیات در جلسهٔ بعد)
    StudentCard(String firstName, String lastName, int age, double gpa) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gpa = gpa;
    }

    // متد برای چاپ — بدون static چون به فیلدهای instance نیاز دارد
    void print() {
        System.out.println("Student: " + firstName + " " + lastName
                + ", age=" + age + ", gpa=" + gpa);
    }

    public static void main(String[] args) {
        StudentCard s = new StudentCard("Ali", "Ahmadi", 20, 17.5);
        s.print();   // Student: Ali Ahmadi, age=20, gpa=17.5
    }
}
