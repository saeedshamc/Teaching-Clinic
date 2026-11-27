# جلسه ۳۴ — Django Channels: WebSocket مقدمه

## درس

Channels به Django امکان **WebSocket** (ارتباط دوطرفهٔ real-time) می‌دهد — برای چت، نوتیفیکیشن، لایو‌دیتا.

### معماری

```
Browser ←WebSocket→ Channels (ASGI) → Consumer (Python async) → Channel Layer (Redis) → دیگر consumerها
```

### نصب

```bash
pip install channels channels_redis daphne
# settings
INSTALLED_APPS += ["channels", "daphne"]
ASGI_APPLICATION = "config.asgi.application"
CHANNEL_LAYERS = {
    "default": {
        "BACKEND": "channels_redis.core.RedisChannelLayer",
        "CONFIG": {"hosts": [("127.0.0.1", 6379)]},
    }
}
```

### Consumer — کلاس async

```python
# library/consumers.py
import json
from channels.generic.websocket import AsyncJsonWebsocketConsumer

class ChatConsumer(AsyncJsonWebsocketConsumer):
    async def connect(self):
        self.room_name = "general"
        self.room_group_name = f"chat_{self.room_name}"
        await self.channel_layer.group_add(self.room_group_name, self.channel_name)
        await self.accept()

    async def disconnect(self, code):
        await self.channel_layer.group_discard(self.room_group_name, self.channel_name)

    async def receive_json(self, content, **kwargs):
        # پیام از کلاینت → پخش به کل گروه
        await self.channel_layer.group_send(
            self.room_group_name,
            {"type": "chat_message", "message": content["message"]},
        )

    async def chat_message(self, event):
        # رویداد group_send → ارسال به همهٔ اعضای گروه
        await self.send_json({"message": event["message"]})
```

### routing.py

```python
from django.urls import path
from . import consumers

websocket_urlpatterns = [
    path("ws/chat/", consumers.ChatConsumer.as_asgi()),
]
```

### اجرا

```bash
# به‌جای runserver از daphne/asgi استفاده کنید
python manage.py runserver        # در DEBUG خودش ASGI را تشخیص می‌دهد
# یا: daphne -b 127.0.0.1 -p 8000 config.asgi:application
```

## کد نمونه

بالا کامنت‌گذاری شده — در `consumers.py`/`routing.py` پیاده کنید.

## تمرین

یک consumer برای «بازدیدکنندگان آنلاین» بنویسید که عدد آنلاین‌ها را به‌صورت real-time به همهٔ کلاینت‌ها بفرستد.

## جواب تمرین

```python
class OnlineConsumer(AsyncJsonWebsocketConsumer):
    async def connect(self):
        await self.channel_layer.group_add("online", self.channel_name)
        await self.accept()
        await self.update_count()

    async def disconnect(self, code):
        await self.channel_layer.group_discard("online", self.channel_name)
        await self.update_count()

    async def update_count(self):
        # از channel_layer.group_channels برای شمردن استفاده کنید
        ...
```

## 📖 شرح فایل `code/consumers.py`

- `ChatConsumer(AsyncJsonWebsocketConsumer)` — lifecycle: `connect` → `receive_json` (تکرار) → `disconnect`.
- `connect` — `room_group_name` از room_name ساخته می‌شود؛ `channel_layer.group_add` عضویت در group؛ `self.user` از `scope["user"]` (AuthMiddlewareStack آن را پر می‌کند)؛ anonymous با code 4001 بسته می‌شود.
- `disconnect` — `group_discard` + اطلاع خروج به گروه.
- `receive_json` — پیام کلاینت → `save_message` (با `@database_sync_to_async` چون ORM sync است) → `group_send` با `type="chat_message"` که متد هم‌نام `chat_message` را صدا می‌زند.
- `chat_message` — event از group_send → `send_json` به همین اتصال.
- کامنت‌های پایانی: `routing.py` با `websocket_urlpatterns`، `asgi.py` با `ProtocolTypeRouter`+`AuthMiddlewareStack`، و تنظیمات `CHANNEL_LAYERS` روی Redis.
