# پاسخ تمرین‌های جلسه ۱۰۵

## آسان

**۱ و ۲.**
```java
@ServerEndpoint("/chat")
public class ChatEndpoint { ... }

@ClientEndpoint
public class ChatClient { ... }
```

## متوسط

**۳ و ۴.**
```java
@OnOpen public void onOpen(Session session) { sessions.add(session); }
@OnMessage public void onMessage(String msg, Session session) { broadcast(msg); }
@OnClose public void onClose(Session session) { sessions.remove(session); }

session.getBasicRemote().sendText("Hello");
```

## سخت

**۵ و ۶.**
```java
private static Set<Session> sessions = ConcurrentHashMap.newKeySet();

@OnClose
public void onClose(Session session, CloseReason reason) {
    sessions.remove(session);
    broadcast("Left: " + reason.getReasonPhrase());
}
```
