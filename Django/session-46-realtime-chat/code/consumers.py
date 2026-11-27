# consumers.py — Realtime Chat کامل (جلسه ۴۶)
import json
from channels.generic.websocket import AsyncJsonWebsocketConsumer
from channels.db import database_sync_to_async
from django.contrib.auth import get_user_model

User = get_user_model()


class ChatConsumer(AsyncJsonWebsocketConsumer):
    """
    Consumer چت کامل — با auth، room، typing indicator و persist.
    """

    async def connect(self):
        # room_name از URL: ws/chat/<room_name>/
        self.room_name = self.scope["url_route"]["kwargs"]["room_name"]
        self.room_group_name = f"chat_{self.room_name}"
        self.user = self.scope["user"]

        # احراز هویت — anonymous نمی‌تواند وصل شود
        if self.user.is_anonymous:
            await self.close(code=4001)
            return

        # عضویت در group اتاق
        await self.channel_layer.group_add(
            self.room_group_name,
            self.channel_name,
        )
        await self.accept()

        # اطلاع به بقیهٔ اعضا
        await self.channel_layer.group_send(
            self.room_group_name,
            {
                "type": "chat_message",
                "message": f"{self.user.username} پیوست به {self.room_name}",
                "username": "system",
            },
        )

        # ارسال تاریخچهٔ چت به کاربر جدید
        history = await self.get_history()
        for msg in history:
            await self.send_json({
                "message": msg["text"],
                "username": msg["user"],
                "timestamp": msg["timestamp"].isoformat(),
            })

    async def disconnect(self, code):
        if hasattr(self, "room_group_name"):
            await self.channel_layer.group_discard(
                self.room_group_name,
                self.channel_name,
            )
            await self.channel_layer.group_send(
                self.room_group_name,
                {
                    "type": "chat_message",
                    "message": f"{self.user.username} اتاق را ترک کرد",
                    "username": "system",
                },
            )

    async def receive_json(self, content, **kwargs):
        """پردازش پیام ورودی از کلاینت."""
        msg_type = content.get("type", "message")

        if msg_type == "typing":
            # کاربر در حال تایپ است — به بقیه اطلاع بده (به خودت نه)
            await self.channel_layer.group_send(
                self.room_group_name,
                {
                    "type": "user_typing",
                    "username": self.user.username,
                },
            )
            return

        # پیام عادی
        text = content.get("message", "").strip()
        if not text:
            return

        # ذخیره در DB
        msg_id = await self.save_message(text)

        # پخش به کل گروه
        await self.channel_layer.group_send(
            self.room_group_name,
            {
                "type": "chat_message",
                "message": text,
                "username": self.user.username,
                "msg_id": msg_id,
            },
        )

    # ─── Event handlers — برای هر type در group_send ─────────────

    async def chat_message(self, event):
        """پیام چت → ارسال به کلاینت."""
        await self.send_json({
            "type": "message",
            "message": event["message"],
            "username": event.get("username", "system"),
            "msg_id": event.get("msg_id"),
        })

    async def user_typing(self, event):
        """کاربر در حال تایپ → به کلاینت اطلاع بده."""
        if event["username"] != self.user.username:
            await self.send_json({
                "type": "typing",
                "username": event["username"],
            })

    # ─── DB helpers — async → sync ────────────────────────────────

    @database_sync_to_async
    def save_message(self, text):
        """ذخیره پیام در DB — برمی‌گرداند id."""
        msg = ChatMessage.objects.create(
            room=self.room_name,
            user=self.user,
            text=text,
        )
        return msg.id

    @database_sync_to_async
    def get_history(self, limit=50):
        """دریافت N پیام آخر اتاق."""
        msgs = ChatMessage.objects.filter(
            room=self.room_name
        ).select_related("user").order_by("-created_at")[:limit]
        return [
            {
                "text": m.text,
                "user": m.user.username,
                "timestamp": m.created_at,
            }
            for m in reversed(list(msgs))   # قدیمی → جدید
        ]


# ══════════════════════════════════════════════════════════════════
# مدل ChatMessage (در models.py)
# ══════════════════════════════════════════════════════════════════
# class ChatMessage(models.Model):
#     room = models.CharField(max_length=100, db_index=True)
#     user = models.ForeignKey(User, on_delete=models.CASCADE)
#     text = models.TextField()
#     created_at = models.DateTimeField(auto_now_add=True)
#     class Meta:
#         ordering = ["-created_at"]
#         indexes = [models.Index(fields=["room", "-created_at"])]

# ══════════════════════════════════════════════════════════════════
# routing.py
# ══════════════════════════════════════════════════════════════════
# websocket_urlpatterns = [
#     path("ws/chat/<str:room_name>/", consumers.ChatConsumer.as_asgi()),
# ]

# ══════════════════════════════════════════════════════════════════
# کلاینت JavaScript — برای تست
# ══════════════════════════════════════════════════════════════════
# const ws = new WebSocket("wss://example.com/ws/chat/general/");
# ws.onopen = () => console.log("connected");
# ws.onmessage = (e) => {
#     const data = JSON.parse(e.data);
#     if (data.type === "message") appendMessage(data.username, data.message);
#     if (data.type === "typing") showTyping(data.username);
# };
# ws.onclose = (e) => console.log("closed", e.code);
#
# // ارسال پیام
# ws.send(JSON.stringify({type: "message", message: "سلام"}));
#
# // typing indicator — debounce در کلاینت
# input.addEventListener("input", debounce(() => {
#     ws.send(JSON.stringify({type: "typing"}));
# }, 500));
