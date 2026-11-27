// ClassDemo.java — کلاس، آبجکت، سازنده، this، getter/setter
public class ClassDemo {

    // ---------- کلاس ساده ----------
    static class Dog {
        String name;
        int age;

        void bark() {
            System.out.println(name + " says woof!");
        }
    }

    // ---------- کلاس با سازنده و this ----------
    static class Person {
        private String name;
        private int age;

        // سازنده — نام = نام کلاس، بدون return type
        Person(String name, int age) {
            this.name = name;      // this = این نمونه
            this.age = age;
        }

        // getter/setter — encapsulation
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) {
            if (age >= 0 && age <= 150) this.age = age;
        }
    }

    public static void main(String[] args) {
        // ---------- نمونهٔ ساده ----------
        Dog d1 = new Dog();
        Dog d2 = new Dog();
        d1.name = "Rex";
        d2.name = "Buddy";
        d1.age = 3;
        d2.age = 5;
        d1.bark();   // Rex says woof!
        d2.bark();   // Buddy says woof!

        // ---------- نمونه با سازنده ----------
        Person p1 = new Person("Ali", 20);
        Person p2 = new Person("Sara", 22);
        System.out.println("p1=" + p1.getName() + " " + p1.getAge());
        System.out.println("p2=" + p2.getName() + " " + p2.getAge());

        // ---------- getter/setter با validation ----------
        p1.setAge(-5);   // رد می‌شود چون منفی است
        System.out.println("p1.age after setAge(-5)=" + p1.getAge());  // 20
        p1.setAge(25);
        System.out.println("p1.age after setAge(25)=" + p1.getAge());  // 25

        // ---------- چند نمونه از یک کلاس ----------
        Dog[] dogs = new Dog[3];
        for (int i = 0; i < dogs.length; i++) {
            dogs[i] = new Dog();
            dogs[i].name = "Dog" + i;
            dogs[i].age = i + 1;
        }
        for (Dog d : dogs) {
            System.out.println(d.name + " is " + d.age + " years old");
        }
    }
}
