// NotificationService.java — programmatic WebSocket messaging
// نیازمند: spring-websocket + spring-messaging jars برای کامایل
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Send to specific user
    public void sendToUser(String userId, String message) {
        messagingTemplate.convertAndSendToUser(
            userId, "/queue/notifications", message
        );
    }

    // Broadcast to all users
    public void broadcast(String message) {
        messagingTemplate.convertAndSend("/topic/announcements", message);
    }

    // Send to topic
    public void sendToTopic(String topic, Object message) {
        messagingTemplate.convertAndSend("/topic/" + topic, message);
    }
}
