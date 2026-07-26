# ============================================================
# جلسه ۳۷ — متغیر محیطی
# فایل: 01_env_config.py
# ============================================================

import os

os.environ["APP_HOST"] = "127.0.0.1"
host = os.environ.get("APP_HOST", "localhost")
port = int(os.environ.get("APP_PORT", "8000"))
debug = os.environ.get("APP_DEBUG", "false").lower() == "true"

print("host:", host)
print("port:", port)
print("debug:", debug)
