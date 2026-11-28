// ChatEndpoint.java — WebSocket server endpoint
// نیازمند: jakarta.websocket-api + tyrus-standalone-server jars
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
public class ChatEndpoint {

    private static final Set<Session> sessions = ConcurrentHashMap.newKeySet();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        sessions.add(session);
        System.out.println("Client connected: " + session.getId());
        broadcast("New client joined. Total: " + sessions.size());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("[" + session.getId() + "]: " + message);
        broadcast("[" + session.getId() + "]: " + message);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        sessions.remove(session);
        System.out.println("Client left: " + session.getId() + " — " + reason);
        broadcast("Client left. Total: " + sessions.size());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
        throwable.printStackTrace();
    }

    private static void broadcast(String message) {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                System.out.println("Send error: " + e.getMessage());
            }
        }
    }
}
