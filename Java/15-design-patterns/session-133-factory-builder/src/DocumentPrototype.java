// DocumentPrototype.java — Prototype pattern demo
// قابل کامپایل با javac — بدون dependency
public class DocumentPrototype {

    public static void main(String[] args) throws CloneNotSupportedException {
        Document original = new Document("Hello World", "Author", 100);

        // Clone document
        Document copy1 = original.clone();
        Document copy2 = original.clone();

        // Modify copies — original unchanged
        copy1.setContent("Modified content 1");
        copy2.setContent("Modified content 2");

        System.out.println("Original: " + original.getContent());
        System.out.println("Copy 1: " + copy1.getContent());
        System.out.println("Copy 2: " + copy2.getContent());
    }

    static class Document implements Cloneable {
        private String content;
        private String author;
        private int version;

        public Document(String content, String author, int version) {
            this.content = content;
            this.author = author;
            this.version = version;
        }

        @Override
        public Document clone() throws CloneNotSupportedException {
            Document clone = (Document) super.clone();
            clone.content = new String(this.content);   // deep copy
            return clone;
        }

        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
        public String getAuthor() { return author; }
        public int getVersion() { return version; }
    }
}
