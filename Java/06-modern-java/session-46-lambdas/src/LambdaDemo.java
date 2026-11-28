// LambdaDemo.java — lambda syntax، functional interface، variants، lambda vs anonymous
public class LambdaDemo {

    // ---------- functional interfaces ----------
    @FunctionalInterface
    interface Greeter {
        void greet(String name);
    }

    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }

    @FunctionalInterface
    interface StringProcessor {
        String process(String input);
    }

    @FunctionalInterface
    interface Predicate<T> {
        boolean test(T value);
    }

    public static void main(String[] args) {
        // ---------- lambda بدون پرانتز برای یک پارامتر ----------
        Greeter g1 = name -> System.out.println("Hello, " + name);
        g1.greet("Ali");

        // ---------- lambda با پرانتز ----------
        Greeter g2 = (name) -> System.out.println("Hi " + name);
        g2.greet("Sara");

        // ---------- lambda با return ----------
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;
        System.out.println("add(3,4)=" + add.calculate(3, 4));       // 7
        System.out.println("multiply(3,4)=" + multiply.calculate(3, 4)); // 12

        // ---------- lambda با block body ----------
        StringProcessor upper = input -> {
            String result = input.toUpperCase();
            return result + "!";
        };
        System.out.println(upper.process("hello"));   // HELLO!

        // ---------- lambda بدون return برای void ----------
        Predicate<String> isEmpty = s -> s.isEmpty();
        System.out.println("isEmpty('')=" + isEmpty.test(""));   // true
        System.out.println("isEmpty('a')=" + isEmpty.test("a")); // false

        // ---------- lambda در برابر anonymous class ----------
        Runnable old = new Runnable() {
            @Override public void run() { System.out.println("Old style"); }
        };
        Runnable modern = () -> System.out.println("Modern style");
        old.run();
        modern.run();

        // ---------- lambda با چند statement ----------
        StringProcessor complex = input -> {
            StringBuilder sb = new StringBuilder();
            for (char c : input.toCharArray()) {
                sb.append(Character.toUpperCase(c));
            }
            return sb.toString();
        };
        System.out.println(complex.process("abc"));   // ABC

        // ---------- lambda به‌عنوان پارامتر ----------
        processWithLambda("test", s -> String.valueOf(s.length() * 2));
    }

    // متد که lambda به‌عنوان پارامتر می‌گیرد
    static void processWithLambda(String input, StringProcessor processor) {
        System.out.println("Processed: " + processor.process(input));
    }
}
