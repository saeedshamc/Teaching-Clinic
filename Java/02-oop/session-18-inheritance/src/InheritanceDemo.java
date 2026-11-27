// InheritanceDemo.java — extends، super، override، final، چند سطح ارث‌بری
public class InheritanceDemo {

    // ---------- کلاس پایه ----------
    static class Animal {
        String name;

        Animal(String name) {
            this.name = name;
        }

        void eat() {
            System.out.println(name + " is eating");
        }

        void sleep() {
            System.out.println(name + " is sleeping");
        }

        void makeSound() {
            System.out.println(name + " makes a sound");
        }
    }

    // ---------- فرزند با override ----------
    static class Dog extends Animal {
        int age;

        Dog(String name, int age) {
            super(name);        // سازندهٔ والد
            this.age = age;
        }

        @Override
        void eat() {            // override متد والد
            super.eat();        // صدا زدن والد
            System.out.println("Dog finished eating");
        }

        @Override
        void makeSound() {
            System.out.println(name + " barks!");
        }
    }

    // ---------- فرزند دیگر ----------
    static class Cat extends Animal {
        Cat(String name) {
            super(name);
        }

        @Override
        void makeSound() {
            System.out.println(name + " meows!");
        }
    }

    // ---------- سطح سوم ----------
    static class Puppy extends Dog {
        Puppy(String name) {
            super(name, 0);
        }

        @Override
        void makeSound() {
            System.out.println(name + " yips!");
        }
    }

    public static void main(String[] args) {
        Animal a = new Animal("Generic");
        Dog d = new Dog("Rex", 3);
        Cat c = new Cat("Whiskers");
        Puppy p = new Puppy("Buddy");

        a.eat();   // Generic is eating
        d.eat();   // Rex is eating + Dog finished eating
        c.eat();   // Whiskers is eating
        p.eat();   // Buddy is eating + Dog finished eating

        a.makeSound();  // Generic makes a sound
        d.makeSound();  // Rex barks!
        c.makeSound();  // Whiskers meows!
        p.makeSound();  // Buddy yips!
    }
}
