// PolymorphismDemo.java — upcasting، downcasting، instanceof، dynamic dispatch
public class PolymorphismDemo {

    static class Animal {
        String name;
        Animal(String name) { this.name = name; }
        void makeSound() { System.out.println(name + " makes a sound"); }
        void eat() { System.out.println(name + " is eating"); }
    }

    static class Dog extends Animal {
        Dog(String name) { super(name); }
        @Override void makeSound() { System.out.println(name + " barks!"); }
        void bark() { System.out.println(name + " woofs loudly!"); }
    }

    static class Cat extends Animal {
        Cat(String name) { super(name); }
        @Override void makeSound() { System.out.println(name + " meows!"); }
        void purr() { System.out.println(name + " purrs..."); }
    }

    static class Bird extends Animal {
        Bird(String name) { super(name); }
        @Override void makeSound() { System.out.println(name + " tweets!"); }
    }

    // متد که هر Animal‌ای قبول می‌کند — dynamic dispatch
    static void playSound(Animal a) {
        a.makeSound();   // در runtime متد درست اجرا می‌شود
    }

    public static void main(String[] args) {
        // ---------- upcasting ----------
        Animal a = new Dog("Rex");   // Dog را به Animal ارجاع می‌دهیم
        a.makeSound();               // Dog.makeSound() اجرا می‌شود!
        a.eat();                     // Animal.eat()

        // ---------- downcasting با instanceof ----------
        if (a instanceof Dog) {
            Dog d = (Dog) a;
            d.bark();               // متد خاص Dog
        }

        // ---------- instanceof pattern matching (Java 16+) ----------
        Animal c = new Cat("Whiskers");
        if (c instanceof Cat cat) {   // cat به‌صورت خودکار cast شده
            cat.purr();
        }

        // ---------- آرایهٔ نوع والد ----------
        Animal[] pets = {new Dog("Rex"), new Cat("Whiskers"), new Bird("Tweety")};
        for (Animal p : pets) {
            p.makeSound();
        }

        // ---------- dynamic dispatch در متد ----------
        playSound(new Dog("Buddy"));   // Buddy barks!
        playSound(new Cat("Luna"));    // Luna meows!
        playSound(new Bird("Kiwi"));   // Kiwi tweets!
    }
}
