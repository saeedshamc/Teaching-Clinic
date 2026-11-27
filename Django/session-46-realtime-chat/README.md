# جلسه ۴۶ — Realtime Chat با Channels

## درس

### Consumer برای چت اتاق‌ها

```python
# library/consumers.py
import json
from channels.generic.websocket import AsyncJsonWebsocketConsumer
from channels.db import database_sync_to_async

class ChatConsumer(AsyncJsonWebsocketConsumer):
    async def connect(self):
        # self.scope["url_route"]["kwargs"]["room_name"] از URL می‌آید
        self.room_name = self.scope["url_route"]["kwargs"]["room_name"]
        self.room_group_name = f"chat_{self.room_name}"

        # احراز هویت از session
        self.user = self.scope["user"]
        if self.user.is_anonymous:
            await self.close(code=4001)
            return

        await self.channel_layer.group_add(self.room_group_name, self.channel_name)
        await self.accept()
        await self.channel_layer.group_send(
            self.room_group_name,
            {"type": "chat_message", "message": f"{self.user.username} پیوست", "username": "system"},
        )

    async def disconnect(self, code):
        await self.channel_layer.group_discard(self.room_group_name, self.channel_name)

    async def receive_json(self, content, **kwargs):
        # ذخیره در دیتابیس (async → sync)
        await self.save_message(content["message"])
        await self.channel_layer.group_send(
            self.room_group_name,
            {
                "type": "chat_message",
                "message": content["message"],
                "username": self.user.username,
            },
        )

    async def chat_message(self, event):
        # event از group_send → ارسال به خودِ این اتصال
        await self.send_json({"message": event["message"], "username": event.get("username", "system")})

    @database_sync_to_async   # تبدیل async به sync برای ORM
    def save_message(self, text):
        ChatMessage.objects.create(room=self.room_name, user=self.user, text=text)
```

### routing.py

```python
websocket_urlpatterns = [
    path("ws/chat/<str:room_name>/", consumers.ChatConsumer.as_asgi()),
]
```

## تمرین

یک consumer برای «تایپینگ indicator» بنویسید که وقتی کاربر در حال تایپ است به بقیهٔ اعضای گروه اطلاع دهد.

## جواب تمرین

```python
async def receive_json(self, content, **kwargs):
    if content.get("type") == "typing":
        await self.channel_layer.group_send(
            self.room_group_name,
            {"type": "user_typing", "username": self.user.username},
        )

async def user_typing(self, event):
    await self.send_json({"typing": event["username"]})
```
