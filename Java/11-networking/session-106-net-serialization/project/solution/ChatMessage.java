// ChatMessage.java — پاسخ پروژهٔ جلسه ۱۰۶
// serializable message با transient fields
import java.io.Serializable;
import java.time.LocalDateTime;

public class ChatMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum MessageType { TEXT, SYSTEM, FILE }

    private String sender;
    private String content;
    private LocalDateTime timestamp;
    private MessageType type;
    private transient byte[] fileData;   // large data — serialize نمی‌شود
    private transient String authToken;  // sensitive — serialize نمی‌شود

    public ChatMessage() {}

    public ChatMessage(String sender, String content, MessageType type) {
        this.sender = sender;
        this.content = content;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    public String getSender() { return sender; }
    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public MessageType getType() { return type; }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s): %s", timestamp, sender, type, content);
    }
}
