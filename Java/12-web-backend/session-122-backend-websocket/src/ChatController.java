// ChatController.java — WebSocket message handlers
// نیازمند: spring-websocket + spring-messaging jars برای کامپایل
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.security.Principal;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // ---------- Public chat — broadcast to all ----------
    @MessageMapping("/chat.send")
    @SendTo("/topic/chat")
    public ChatMessage handleChatMessage(ChatMessage message) {
        System.out.println("Chat: " + message.getSender() + " — " + message.getContent());
        return message;   // broadcast to all /topic/chat subscribers
    }

    // ---------- Private message — send to specific user ----------
    @MessageMapping("/chat.private")
    public void handlePrivateMessage(ChatMessage message, Principal principal) {
        System.out.println("Private: " + principal.getName() + " → " + message.getRecipient());
        messagingTemplate.convertAndSendToUser(
            message.getRecipient(),
            "/queue/private",
            message
        );
    }

    // ---------- Typing indicator ----------
    @MessageMapping("/chat.typing")
    @SendTo("/topic/typing")
    public String handleTyping(Principal principal) {
        return principal.getName() + " is typing...";
    }
}
