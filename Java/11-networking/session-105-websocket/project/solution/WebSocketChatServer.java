// WebSocketChatServer.java — پاسخ کامل پروژهٔ جلسه ۱۰۵
// نیازمند: jakarta.websocket-api + tyrus-standalone-server jars
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

@ServerEndpoint("/chat")
public class WebSocketChatServer {

    private static final Map<String, Session> clients = new ConcurrentHashMap<>();
    private static final Set<Session> sessions = ConcurrentHashMap.newKeySet();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        clients.put(session.getId(), session);
        broadcast("[System] New client joined. Total: " + sessions.size());
        System.out.println("Client connected: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("[" + session.getId() + "]: " + message);
        broadcast("[" + session.getId() + "]: " + message);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        sessions.remove(session);
        clients.remove(session.getId());
        broadcast("[System] Client left. Total: " + sessions.size());
        System.out.println("Client left: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
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

    // برای server container — نیازمند Grizzly یا Jetty
    public static void main(String[] args) {
        System.out.println("WebSocket Chat Server — deploy to servlet container");
        System.out.println("Endpoint: ws://localhost:8080/chat");
    }
}
