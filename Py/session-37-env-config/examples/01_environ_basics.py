# ============================================================
# جلسه ۳۷ — os.environ پایه
# ============================================================

import os

# خواندن با پیش‌فرض
host = os.environ.get("APP_HOST", "localhost")
port = int(os.environ.get("APP_PORT", "8000"))
debug = os.environ.get("DEBUG", "false").lower() in ("1", "true", "yes")

print(f"Server: {host}:{port}")
print(f"Debug: {debug}")

# تنظیم موقت برای demo
os.environ["APP_NAME"] = "PythonCourse"
print("APP_NAME:", os.environ.get("APP_NAME"))

# متغیرهای سیستمی
print("USER:", os.environ.get("USERNAME") or os.environ.get("USER", "unknown"))
print("PATH length:", len(os.environ.get("PATH", "")))
