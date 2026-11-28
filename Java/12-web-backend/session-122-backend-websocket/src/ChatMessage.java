// ChatMessage.java — WebSocket message model
// قابل کامپایل با javac — بدون dependency
public class ChatMessage {

    private String sender;
    private String content;
    private String recipient;   // for private messages
    private long timestamp;

    public ChatMessage() {
        this.timestamp = System.currentTimeMillis();
    }

    public ChatMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
    }

    public ChatMessage(String sender, String content, String recipient) {
        this.sender = sender;
        this.content = content;
        this.recipient = recipient;
        this.timestamp = System.currentTimeMillis();
    }

    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }
    public long getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("[%s] %s: %s", timestamp, sender, content);
    }
}
