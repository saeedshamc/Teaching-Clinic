// NotificationWebSocketService.java — پاسخ کامل پروژهٔ جلسه ۱۲۲
// نیازمند: spring-websocket + spring-messaging jars برای کامپایل
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationWebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationWebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyUser(String userId, String message) {
        messagingTemplate.convertAndSendToUser(userId, "/queue/notifications", message);
    }

    public void broadcastAnnouncement(String message) {
        messagingTemplate.convertAndSend("/topic/announcements", message);
    }

    public void sendSystemMessage(String message) {
        messagingTemplate.convertAndSend("/topic/system", message);
    }
}
