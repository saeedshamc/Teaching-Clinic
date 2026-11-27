// InnerClassDemo.java — inner، static nested، anonymous، local
public class InnerClassDemo {

    // ---------- inner class ----------
    static class Outer {
        int outerField = 10;

        class Inner {
            int innerField = 20;

            void display() {
                System.out.println("outer=" + outerField);   // فیلد Outer
                System.out.println("inner=" + innerField);
            }
        }
    }

    // ---------- static nested class ----------
    static class OuterStatic {
        static int staticField = 100;

        static class Nested {
            void display() {
                System.out.println("static=" + staticField);
            }
        }
    }

    // ---------- interface برای anonymous ----------
    interface Greeting {
        void greet();
    }

    // ---------- متد با local class ----------
    static void methodWithLocal() {
        class Local {
            void print() {
                System.out.println("Local class inside method");
            }
        }
        Local l = new Local();
        l.print();
    }

    public static void main(String[] args) {
        // inner class — از طریق outer instance
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.display();

        // static nested — بدون outer instance
        OuterStatic.Nested nested = new OuterStatic.Nested();
        nested.display();

        // anonymous class
        Greeting g = new Greeting() {
            @Override public void greet() {
                System.out.println("Hello from anonymous!");
            }
        };
        g.greet();

        // local class
        methodWithLocal();
    }
}
