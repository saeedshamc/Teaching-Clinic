# ============================================================
# جلسه ۲۴ — خواندن و نوشتن فایل JSON
# فایل: 02_file_json.py
# ============================================================

import json
from pathlib import Path

USERS = [
    {"id": 1, "name": "سعید", "role": "admin"},
    {"id": 2, "name": "مینا", "role": "user"},
    {"id": 3, "name": "علی", "role": "user"},
]

path = Path(__file__).with_name("users.json")

# --- نوشتن با json.dump ---
with path.open("w", encoding="utf-8") as f:
    json.dump(USERS, f, ensure_ascii=False, indent=2)
print(f"💾 ذخیره در {path.name}")

# --- خواندن با json.load ---
with path.open(encoding="utf-8") as f:
    loaded = json.load(f)

print(f"📂 {len(loaded)} کاربر:")
for user in loaded:
    print(f"  #{user['id']} {user['name']} ({user['role']})")

# --- pretty print از رشته ---
raw = path.read_text(encoding="utf-8")
parsed = json.loads(raw)
admins = [u["name"] for u in parsed if u["role"] == "admin"]
print("ادمین‌ها:", admins)

path.unlink(missing_ok=True)
