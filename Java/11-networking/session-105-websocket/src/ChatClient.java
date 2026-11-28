// ChatClient.java — WebSocket client
// نیازمند: jakarta.websocket-api + tyrus-standalone-client jars
import jakarta.websocket.*;
import java.net.URI;
import java.util.Scanner;

@ClientEndpoint
public class ChatClient {

    private Session session;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        System.out.println("Connected to WebSocket server!");
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Received: " + message);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println("Connection closed: " + reason);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
    }

    public static void main(String[] args) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            ChatClient client = new ChatClient();

            Session session = container.connectToServer(
                client, new URI("ws://localhost:8080/chat"));

            // Send messages
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type messages (or 'exit' to quit):");
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) break;
                session.getBasicRemote().sendText(input);
            }

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
