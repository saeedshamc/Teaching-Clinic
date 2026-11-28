// FacadeExample.java — Facade pattern demo
// قابل کامپایل با javac — بدون dependency
public class FacadeExample {

    public static void main(String[] args) {
        SimpleFacade facade = new SimpleFacade();
        facade.simpleOperation();
        facade.anotherOperation();
    }

    static class ComplexSubsystemA {
        public void operationA() { System.out.println("Subsystem A operation"); }
        public void operationA2() { System.out.println("Subsystem A operation 2"); }
    }

    static class ComplexSubsystemB {
        public void operationB() { System.out.println("Subsystem B operation"); }
        public void operationB2() { System.out.println("Subsystem B operation 2"); }
    }

    static class ComplexSubsystemC {
        public void operationC() { System.out.println("Subsystem C operation"); }
    }

    static class SimpleFacade {
        private final ComplexSubsystemA a = new ComplexSubsystemA();
        private final ComplexSubsystemB b = new ComplexSubsystemB();
        private final ComplexSubsystemC c = new ComplexSubsystemC();

        public void simpleOperation() {
            System.out.println("Facade: simple operation");
            a.operationA();
            b.operationB();
            c.operationC();
        }

        public void anotherOperation() {
            System.out.println("Facade: another operation");
            a.operationA2();
            b.operationB2();
        }
    }
}
