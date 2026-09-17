# ============================================================
# جلسه ۲۱ — Context Manager مبتنی بر کلاس
# فایل: 02_class_based_cm.py
# ============================================================


class FileLogger:
    """Context manager — لاگ قبل و بعد از عملیات"""

    def __init__(self, operation: str):
        self.operation = operation

    def __enter__(self):
        print(f"▶ شروع: {self.operation}")
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        if exc_type:
            print(f"✗ خطا در {self.operation}: {exc_val}")
        else:
            print(f"✓ پایان: {self.operation}")
        return False  # exception propagate شود


class Counter:
    """CM که مقدار را در as می‌دهد"""

    def __init__(self):
        self.count = 0

    def __enter__(self):
        self.count = 0
        return self

    def __exit__(self, *args):
        print(f"  مجموع شمارش: {self.count}")

    def increment(self):
        self.count += 1


with FileLogger("پردازش داده"):
    print("  در حال پردازش...")

print()

with Counter() as c:
    for _ in range(3):
        c.increment()
        print(f"  count = {c.count}")

print("\n--- CM با exception ---")
try:
    with FileLogger("عملیات خطرناک"):
        raise ValueError("خطای تست")
except ValueError:
    print("exception گرفته شد")
