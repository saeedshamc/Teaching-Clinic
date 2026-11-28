// ChatWebSocketController.java — پاسخ کامل پروژهٔ جلسه ۱۲۲
// نیازمند: spring-websocket + spring-messaging jars برای کامپایل
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.security.Principal;
import java.util.*;

@Controller
public class ChatWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final Map<String, String> onlineUsers = new ConcurrentHashMap<>();

    public ChatWebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/chat")
    public ChatMessage handleChat(ChatMessage message, Principal principal) {
        message.setSender(principal.getName());
        return message;
    }

    @MessageMapping("/chat.private")
    public void handlePrivate(ChatMessage message, Principal principal) {
        message.setSender(principal.getName());
        messagingTemplate.convertAndSendToUser(
            message.getRecipient(), "/queue/private", message
        );
    }

    @MessageMapping("/chat.typing")
    @SendTo("/topic/typing")
    public String handleTyping(Principal principal) {
        return principal.getName() + " is typing...";
    }

    @MessageMapping("/chat.join")
    @SendTo("/topic/online")
    public String handleJoin(Principal principal) {
        onlineUsers.put(principal.getName(), principal.getName());
        return "User joined: " + principal.getName();
    }
}
