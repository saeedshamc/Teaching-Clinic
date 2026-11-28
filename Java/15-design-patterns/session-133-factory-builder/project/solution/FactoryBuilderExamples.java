// FactoryBuilderExamples.java — پاسخ کامل پروژهٔ جلسه ۱۳۳
// Factory Method + Abstract Factory + Builder + Prototype
public class FactoryBuilderExamples {

    public static void main(String[] args) throws CloneNotSupportedException {
        // Factory Method
        DocumentFactory docFactory = new DocumentFactory();
        Document pdf = docFactory.createDocument("pdf");
        pdf.open();

        // Abstract Factory
        UIFactory windowsFactory = new WindowsFactory();
        windowsFactory.createUI();

        // Builder
        Computer pc = new Computer.Builder()
            .cpu("Intel i9")
            .ram(32)
            .storage(1000)
            .hasGPU(true)
            .build();
        System.out.println("PC: " + pc);

        // Prototype
        CloneableDocument original = new CloneableDocument("Hello", "Author", 1);
        CloneableDocument copy = original.clone();
        copy.setContent("Modified");
        System.out.println("Original: " + original.getContent());
        System.out.println("Copy: " + copy.getContent());
    }

    // ---------- Factory Method ----------
    abstract static class Document {
        public abstract void open();
    }
    static class PdfDocument extends Document {
        @Override public void open() { System.out.println("Opening PDF"); }
    }
    static class WordDocument extends Document {
        @Override public void open() { System.out.println("Opening Word"); }
    }
    static class DocumentFactory {
        public Document createDocument(String type) {
            return switch (type) {
                case "pdf" -> new PdfDocument();
                case "word" -> new WordDocument();
                default -> throw new IllegalArgumentException();
            };
        }
    }

    // ---------- Abstract Factory ----------
    interface Button { void render(); }
    interface CheckBox { void render(); }
    static class WindowsButton implements Button {
        @Override public void render() { System.out.println("Windows Button"); }
    }
    static class WindowsCheckBox implements CheckBox {
        @Override public void render() { System.out.println("Windows CheckBox"); }
    }
    abstract static class UIFactory {
        public abstract Button createButton();
        public abstract CheckBox createCheckBox();
        public void createUI() { createButton().render(); createCheckBox().render(); }
    }
    static class WindowsFactory extends UIFactory {
        @Override public Button createButton() { return new WindowsButton(); }
        @Override public CheckBox createCheckBox() { return new WindowsCheckBox(); }
    }

    // ---------- Builder ----------
    static class Computer {
        private final String cpu;
        private final int ram, storage;
        private final boolean hasGPU;
        private Computer(Builder b) { cpu = b.cpu; ram = b.ram; storage = b.storage; hasGPU = b.hasGPU; }
        public static class Builder {
            private String cpu = "Intel i5";
            private int ram = 8, storage = 256;
            private boolean hasGPU = false;
            public Builder cpu(String c) { cpu = c; return this; }
            public Builder ram(int r) { ram = r; return this; }
            public Builder storage(int s) { storage = s; return this; }
            public Builder hasGPU(boolean g) { hasGPU = g; return this; }
            public Computer build() { return new Computer(this); }
        }
        @Override public String toString() {
            return String.format("Computer{cpu='%s', ram=%d, storage=%d, GPU=%s}", cpu, ram, storage, hasGPU);
        }
    }

    // ---------- Prototype ----------
    static class CloneableDocument implements Cloneable {
        private String content, author;
        private int version;
        CloneableDocument(String c, String a, int v) { content = c; author = a; version = v; }
        @Override public CloneableDocument clone() throws CloneNotSupportedException {
            CloneableDocument clone = (CloneableDocument) super.clone();
            clone.content = new String(content);
            return clone;
        }
        public String getContent() { return content; }
        public void setContent(String c) { content = c; }
    }
}
