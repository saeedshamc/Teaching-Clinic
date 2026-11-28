// MessageSystem.java — پاسخ پروژهٔ جلسه ۵۲
// نکته: switch pattern در Java 21+ است؛ اینجا با instanceof pattern (Java 16+) نوشته شده
sealed interface Message permits TextMessage, ImageMessage, FileMessage {}

record TextMessage(String text) implements Message {}
record ImageMessage(String filename) implements Message {}
record FileMessage(String filename, long size) implements Message {}

sealed interface Priority permits High, Medium, Low {}
record High() implements Priority {}
record Medium() implements Priority {}
record Low() implements Priority {}

public class MessageSystem {

    static String processMessage(Message msg) {
        if (msg instanceof TextMessage t) {
            return "Text: \"" + t.text() + "\" → Sent";
        } else if (msg instanceof ImageMessage i) {
            return "Image: " + i.filename() + " → Sent";
        } else if (msg instanceof FileMessage f) {
            if (f.size() > 10_000_000) {
                return "File: " + f.filename() + " → Failed (too large)";
            }
            return "File: " + f.filename() + " → Sent";
        }
        return "Unknown";
    }

    static String getPriority(Priority p) {
        if (p instanceof High) return "High";
        if (p instanceof Medium) return "Medium";
        if (p instanceof Low) return "Low";
        return "Unknown";
    }

    public static void main(String[] args) {
        Message[] messages = {
            new TextMessage("Hello World"),
            new ImageMessage("photo.jpg"),
            new FileMessage("small.txt", 1000),
            new FileMessage("big.zip", 50_000_000)
        };

        for (Message msg : messages) {
            System.out.println(processMessage(msg));
        }

        System.out.println("\nPriorities: " + getPriority(new High()) + ", "
            + getPriority(new Medium()) + ", " + getPriority(new Low()));
    }
}
