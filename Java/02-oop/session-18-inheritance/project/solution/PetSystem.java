// PetSystem.java — پاسخ پروژهٔ جلسه ۱۸
public class PetSystem {

    static class Pet {
        String name;
        int age;

        Pet(String name, int age) {
            this.name = name;
            this.age = age;
        }

        void eat() { System.out.println(name + " is eating"); }
        void sleep() { System.out.println(name + " is sleeping"); }
        void makeSound() { System.out.println(name + " makes a sound"); }
    }

    static class Dog extends Pet {
        Dog(String name, int age) { super(name, age); }
        @Override void makeSound() { System.out.println(name + " barks!"); }
    }

    static class Cat extends Pet {
        Cat(String name, int age) { super(name, age); }
        @Override void makeSound() { System.out.println(name + " meows!"); }
    }

    static class Bird extends Pet {
        Bird(String name, int age) { super(name, age); }
        @Override void makeSound() { System.out.println(name + " tweets!"); }
    }

    public static void main(String[] args) {
        Pet[] pets = {
            new Dog("Rex", 3),
            new Cat("Whiskers", 2),
            new Bird("Tweety", 1)
        };

        for (Pet p : pets) {
            System.out.println("--- " + p.name + " ---");
            p.eat();
            p.makeSound();
            p.sleep();
        }
    }
}
