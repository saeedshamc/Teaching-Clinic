// EmployeeSystem.java — پاسخ پروژهٔ جلسه ۲۰
public class EmployeeSystem {

    static abstract class Employee {
        String name;
        int id;
        String hireDate;

        Employee(String name, int id, String hireDate) {
            this.name = name;
            this.id = id;
            this.hireDate = hireDate;
        }

        abstract double calculateSalary();
        abstract String getType();

        void printPayslip() {
            System.out.printf("%s (id=%d, %s, hired=%s): salary=%.2f%n",
                    name, id, getType(), hireDate, calculateSalary());
        }
    }

    static class FullTime extends Employee {
        double baseSalary, bonus;
        FullTime(String n, int id, String d, double base, double bonus) {
            super(n, id, d); this.baseSalary = base; this.bonus = bonus;
        }
        @Override double calculateSalary() { return baseSalary + bonus; }
        @Override String getType() { return "FullTime"; }
    }

    static class PartTime extends Employee {
        double hourlyRate;
        int hours;
        PartTime(String n, int id, String d, double rate, int hours) {
            super(n, id, d); this.hourlyRate = rate; this.hours = hours;
        }
        @Override double calculateSalary() { return hourlyRate * hours; }
        @Override String getType() { return "PartTime"; }
    }

    public static void main(String[] args) {
        Employee[] employees = {
            new FullTime("Ali", 1, "2024-01-15", 5000, 500),
            new PartTime("Sara", 2, "2024-03-01", 50, 80),
            new FullTime("Reza", 3, "2023-06-10", 6000, 300)
        };

        for (Employee e : employees) e.printPayslip();
    }
}
