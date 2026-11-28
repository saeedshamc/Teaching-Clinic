// AdapterExample.java — Adapter pattern demo
// قابل کامپایل با javac — بدون dependency
public class AdapterExample {

    public static void main(String[] args) {
        // Old printer
        OldPrinter oldPrinter = new OldPrinterImpl();

        // Adapt to new interface
        NewPrinter adapted = new PrinterAdapter(oldPrinter);
        adapted.printNew("Hello from Adapter!");
    }

    interface OldPrinter {
        void printOld(String text);
    }

    interface NewPrinter {
        void printNew(String text);
    }

    static class OldPrinterImpl implements OldPrinter {
        @Override
        public void printOld(String text) {
            System.out.println("[OLD] " + text);
        }
    }

    static class PrinterAdapter implements NewPrinter {
        private final OldPrinter oldPrinter;

        public PrinterAdapter(OldPrinter oldPrinter) {
            this.oldPrinter = oldPrinter;
        }

        @Override
        public void printNew(String text) {
            oldPrinter.printOld(text);
        }
    }
}
