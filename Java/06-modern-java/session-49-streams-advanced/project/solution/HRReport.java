// HRReport.java — پاسخ پروژهٔ جلسه ۴۹
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee {
    String name;
    String department;
    double salary;
    int age;
    Employee(String name, String dept, double salary, int age) {
        this.name = name; this.department = dept; this.salary = salary; this.age = age;
    }
    String getName() { return name; }
    String getDepartment() { return department; }
    double getSalary() { return salary; }
    int getAge() { return age; }
}

public class HRReport {

    public static void main(String[] args) {
        List<Employee> employees = List.of(
            new Employee("Ali", "IT", 5000, 25),
            new Employee("Sara", "HR", 4000, 28),
            new Employee("Reza", "IT", 5500, 32),
            new Employee("Mina", "HR", 4200, 24),
            new Employee("Ahmed", "Finance", 6000, 35)
        );

        // تعداد کارمند هر department
        Map<String, Long> countByDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.counting()
            ));
        System.out.println("Employees by dept: " + countByDept);

        // میانگین حقوق هر department
        Map<String, Double> avgSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
            ));
        System.out.println("Avg salary by dept: " + avgSalaryByDept);

        // بیشترین حقوق
        Employee highest = employees.stream()
            .max((a, b) -> Double.compare(a.getSalary(), b.getSalary()))
            .orElse(null);
        if (highest != null) {
            System.out.println("Highest salary: " + highest.getName() + " (" + highest.getSalary() + ")");
        }

        // senior/junior
        Map<Boolean, List<Employee>> seniors = employees.stream()
            .collect(Collectors.partitioningBy(e -> e.getAge() >= 30));
        System.out.println("Senior (age>=30): " + seniors.get(true).size());
        System.out.println("Junior (age<30): " + seniors.get(false).size());

        // name → salary
        Map<String, Double> nameToSalary = employees.stream()
            .collect(Collectors.toMap(
                Employee::getName,
                Employee::getSalary
            ));
        System.out.println("Name to salary: " + nameToSalary);

        // مجموع حقوق
        double totalSalary = employees.stream()
            .mapToDouble(Employee::getSalary)
            .sum();
        System.out.println("Total salary: " + totalSalary);
    }
}
