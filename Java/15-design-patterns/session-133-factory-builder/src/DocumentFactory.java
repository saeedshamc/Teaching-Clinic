// DocumentFactory.java — Factory Method pattern demo
// قابل کامپایل با javac — بدون dependency
public class DocumentFactory {

    public static void main(String[] args) {
        DocumentFactory factory = new DocumentFactory();

        Document pdf = factory.createDocument("pdf");
        Document word = factory.createDocument("word");

        pdf.open();
        word.open();
    }

    abstract static class Document {
        public abstract void open();
    }

    static class PdfDocument extends Document {
        @Override public void open() { System.out.println("Opening PDF document"); }
    }

    static class WordDocument extends Document {
        @Override public void open() { System.out.println("Opening Word document"); }
    }

    static class TextDocument extends Document {
        @Override public void open() { System.out.println("Opening Text document"); }
    }

    public Document createDocument(String type) {
        return switch (type.toLowerCase()) {
            case "pdf" -> new PdfDocument();
            case "word" -> new WordDocument();
            case "text" -> new TextDocument();
            default -> throw new IllegalArgumentException("Unknown document type: " + type);
        };
    }
}
