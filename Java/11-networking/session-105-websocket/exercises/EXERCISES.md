# تمرین‌های جلسه ۱۰۵ — WebSocket

## آسان
1. `@ServerEndpoint` برای WebSocket server بسازید.
2. `@ClientEndpoint` برای WebSocket client بسازید.

## متوسط
3. `@OnOpen`, `@OnMessage`, `@OnClose` را پیاده کنید.
4. `session.getBasicRemote().sendText()` برای send message استفاده کنید.

## سخت
5. `sessions` set برای broadcast به همهٔ clients استفاده کنید.
6. `CloseReason` برای handle connection close استفاده کنید.
