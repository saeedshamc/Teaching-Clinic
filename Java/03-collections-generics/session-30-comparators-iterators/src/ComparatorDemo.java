// ComparatorDemo.java — Comparable، Comparator، lambda، Iterator
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ComparatorDemo {

    // ---------- Comparable — natural ordering ----------
    static class Student implements Comparable<Student> {
        String name;
        int age;

        Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Student other) {
            return this.age - other.age;   // بر اساس سن
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Ali", 20));
        students.add(new Student("Sara", 18));
        students.add(new Student("Reza", 22));

        // ---------- Collections.sort — natural ordering ----------
        System.out.println("Before sort: " + students);
        Collections.sort(students);   // بر اساس age (Comparable)
        System.out.println("After sort: " + students);

        // ---------- Comparator — custom ordering ----------
        // بر اساس نام
        Comparator<Student> byName = (a, b) -> a.name.compareTo(b.name);
        students.sort(byName);
        System.out.println("By name: " + students);

        // بر اساس سن نزولی
        Comparator<Student> byAgeDesc = (a, b) -> b.age - a.age;
        students.sort(byAgeDesc);
        System.out.println("By age desc: " + students);

        // ---------- lambda و method reference ----------
        students.sort(Comparator.comparingInt(s -> s.age));
        System.out.println("By age asc: " + students);

        students.sort(Comparator.comparing(s -> s.name, String.CASE_INSENSITIVE_ORDER));
        System.out.println("By name (case-insensitive): " + students);

        // ---------- Iterator — حذف امن ----------
        List<String> names = new ArrayList<>(List.of("Ali", "Sara", "Reza", "Mina"));
        Iterator<String> it = names.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if (s.equals("Sara")) {
                it.remove();   // حذف امن
            }
        }
        System.out.println("After remove: " + names);

        // ---------- PriorityQueue با comparator ----------
        java.util.PriorityQueue<Student> pq = new java.util.PriorityQueue<>(byName);
        pq.offer(new Student("Ali", 20));
        pq.offer(new Student("Sara", 18));
        pq.offer(new Student("Reza", 22));
        System.out.println("PQ poll: " + pq.poll());   // بر اساس نام — Ali اول
    }
}
