# consumers.py — Django Channels WebSocket (جلسه ۳۴)
import json
from channels.generic.websocket import AsyncJsonWebsocketConsumer
from channels.db import database_sync_to_async
from django.contrib.auth import get_user_model

User = get_user_model()


class ChatConsumer(AsyncJsonWebsocketConsumer):
    """
    Consumer برای چت real-time.
    Lifecycle: connect → receive_json (تکرار) → disconnect
    """

    async def connect(self):
        """وقتی کلاینت WebSocket وصل می‌شود."""
        # گروه چت — همهٔ اعضای اتاق در یک group
        self.room_name = "general"
        self.room_group_name = f"chat_{self.room_name}"

        # کاربر از session — در middleware احراز هویت شده
        self.user = self.scope["user"]
        if self.user.is_anonymous:
            await self.close(code=4001)   # ۴۰۰۱ = unauthorized
            return

        # عضویت در group
        await self.channel_layer.group_add(
            self.room_group_name,
            self.channel_name,
        )
        await self.accept()

        # اطلاع به بقیه
        await self.channel_layer.group_send(
            self.room_group_name,
            {
                "type": "chat_message",
                "message": f"{self.user.username} پیوست",
                "username": "system",
            },
        )

    async def disconnect(self, code):
        """وقتی اتصال قطع می‌شود."""
        if hasattr(self, "room_group_name"):
            await self.channel_layer.group_discard(
                self.room_group_name,
                self.channel_name,
            )
            await self.channel_layer.group_send(
                self.room_group_name,
                {
                    "type": "chat_message",
                    "message": f"{self.user.username} رفت",
                    "username": "system",
                },
            )

    async def receive_json(self, content, **kwargs):
        """وقتی پیامی از کلاینت می‌آید."""
        message = content.get("message", "").strip()
        if not message:
            return

        # ذخیره در دیتابیس (async → sync با دکوریتور)
        await self.save_message(message)

        # پخش به کل گروه — همهٔ اعضا دریافت می‌کنند
        await self.channel_layer.group_send(
            self.room_group_name,
            {
                "type": "chat_message",   # → chat_message() صدا زده می‌شود
                "message": message,
                "username": self.user.username,
            },
        )

    async def chat_message(self, event):
        """رویداد group_send → ارسال به خودِ این اتصال."""
        # event همان dictی است که در group_send فرستادیم
        await self.send_json({
            "message": event["message"],
            "username": event.get("username", "system"),
        })

    @database_sync_to_async   # تبدیل async به sync برای ORM
    def save_message(self, text):
        """ذخیره در DB — با دکوریتور چون ORM sync است."""
        # ChatMessage.objects.create(
        #     room=self.room_name,
        #     user=self.user,
        #     text=text,
        # )
        pass


# ══════════════════════════════════════════════════════════════════
# routing.py — مسیریابی WebSocket
# ══════════════════════════════════════════════════════════════════
# from django.urls import path
# from . import consumers
#
# websocket_urlpatterns = [
#     path("ws/chat/", consumers.ChatConsumer.as_asgi()),
# ]

# ══════════════════════════════════════════════════════════════════
# asgi.py — نقطهٔ ورود ASGI
# ══════════════════════════════════════════════════════════════════
# import os
# from django.core.asgi import get_asgi_application
# from channels.routing import ProtocolTypeRouter, URLRouter
# from channels.auth import AuthMiddlewareStack
# from channels.security.websocket import AllowedHostsOriginValidator
# from .routing import websocket_urlpatterns
#
# os.environ.setdefault("DJANGO_SETTINGS_MODULE", "config.settings")
#
# application = ProtocolTypeRouter({
#     "http": get_asgi_application(),
#     "websocket": AllowedHostsOriginValidator(
#         AuthMiddlewareStack(URLRouter(websocket_urlpatterns))
#     ),
# })

# ══════════════════════════════════════════════════════════════════
# settings.py — تنظیمات Channels
# ══════════════════════════════════════════════════════════════════
# INSTALLED_APPS += ["channels", "daphne"]
# ASGI_APPLICATION = "config.asgi.application"
#
# CHANNEL_LAYERS = {
#     "default": {
#         "BACKEND": "channels_redis.core.RedisChannelLayer",
#         "CONFIG": {"hosts": [("127.0.0.1", 6379)]},
#     }
# }

# ══════════════════════════════════════════════════════════════════
# اجرا:
#   python manage.py runserver   # در DEBUG خودش ASGI را تشخیص می‌دهد
#   یا: daphne -b 127.0.0.1 -p 8000 config.asgi:application
#
# کلاینت تست (JavaScript):
#   const ws = new WebSocket("ws://localhost:8000/ws/chat/");
#   ws.onopen = () => ws.send(JSON.stringify({message: "سلام"}));
#   ws.onmessage = (e) => console.log(JSON.parse(e.data));
