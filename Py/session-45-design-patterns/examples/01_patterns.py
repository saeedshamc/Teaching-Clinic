# ============================================================
# جلسه ۴۵ — Design Patterns
# فایل: 01_patterns.py
# ============================================================

class Logger:
    _instance = None
    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
            cls._instance.messages = []
        return cls._instance

    def log(self, msg: str) -> None:
        self.messages.append(msg)

def shape_factory(kind: str):
    if kind == "circle":
        return {"type": "circle"}
    if kind == "square":
        return {"type": "square"}
    raise ValueError("نوع ناشناخته")

a = Logger(); b = Logger()
a.log("شروع")
print("Singleton؟", a is b)
print("پیام‌ها:", b.messages)
print(shape_factory("circle"))
