# ============================================================
# جلسه ۴۵ — Singleton
# فایل: 01_singleton.py
# ============================================================


class Logger:
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
            cls._instance.messages: list[str] = []
        return cls._instance

    def log(self, msg: str) -> None:
        self.messages.append(msg)


a = Logger()
b = Logger()
a.log("شروع برنامه")
b.log("کاربر وارد شد")

print("همان instance؟", a is b)
print("پیام‌ها:", b.messages)
