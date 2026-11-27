// EncapsulationDemo.java — overload سازنده، this() chaining، encapsulation، immutable
public class EncapsulationDemo {

    // ---------- overload سازنده + this() ----------
    static class Person {
        private String name;
        private int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        Person(String name) {
            this(name, 0);   // this() chaining — باید اولین statement باشد
        }

        Person() {
            this("Unknown", 0);
        }

        public String getName() { return name; }
        public int getAge() { return age; }
    }

    // ---------- encapsulation کامل ----------
    static class BankAccount {
        private double balance;
        private String owner;

        public BankAccount(String owner, double initialDeposit) {
            this.owner = owner;
            this.balance = initialDeposit;
        }

        public double getBalance() { return balance; }
        public String getOwner() { return owner; }

        public void deposit(double amount) {
            if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
            balance += amount;
        }

        public boolean withdraw(double amount) {
            if (amount <= 0) return false;
            if (amount > balance) return false;
            balance -= amount;
            return true;
        }
    }

    // ---------- immutable ----------
    static final class Point {
        private final int x;
        private final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() { return x; }
        public int getY() { return y; }
        // setter ندارد — مقدار بعد از ساخت تغییر نمی‌کند
    }

    public static void main(String[] args) {
        // overload سازنده
        Person p1 = new Person("Ali", 20);
        Person p2 = new Person("Sara");
        Person p3 = new Person();
        System.out.println("p1=" + p1.getName() + " " + p1.getAge());
        System.out.println("p2=" + p2.getName() + " " + p2.getAge());
        System.out.println("p3=" + p3.getName() + " " + p3.getAge());

        // encapsulation
        BankAccount acc = new BankAccount("Ali", 1000);
        System.out.println("Initial balance: " + acc.getBalance());
        acc.deposit(500);
        System.out.println("After deposit: " + acc.getBalance());
        System.out.println("Withdraw 300: " + acc.withdraw(300));
        System.out.println("After withdraw: " + acc.getBalance());
        System.out.println("Withdraw 2000: " + acc.withdraw(2000));  // false — موجودی کافی نیست

        // immutable
        Point pt = new Point(3, 4);
        System.out.println("Point: " + pt.getX() + ", " + pt.getY());
        // pt.setX(5);  // خطا — setter ندارد
    }
}
