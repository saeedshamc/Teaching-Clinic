// Message.java — serializable message class
// با javac کامپایل می‌شود — java.io.Serializable بخشی از JDK است
import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sender;
    private String content;
    private LocalDateTime timestamp;
    private transient String password;   // serialize نمی‌شود

    public Message() {}

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public String getSender() { return sender; }
    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("[%s] %s — %s", timestamp, sender, content);
    }
}
