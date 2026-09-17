# ============================================================
# تمرین ۴–۵ جلسه ۲۴ — JSON (خانه)
# ============================================================

import json
from pathlib import Path

users = [
    {"id": 1, "name": "سعید", "age": 30},
    {"id": 2, "name": "مینا", "age": 25},
    {"id": 3, "name": "علی", "age": 28},
]

path = Path(__file__).with_name("users.json")

# --- ۴. ذخیره و بارگذاری ---
with path.open("w", encoding="utf-8") as f:
    json.dump(users, f, ensure_ascii=False, indent=2)

with path.open(encoding="utf-8") as f:
    loaded = json.load(f)
print(f"بارگذاری {len(loaded)} کاربر")

# --- ۵. JSONDecodeError ---
for sample in ['{"ok": true}', "{bad json"]:
    try:
        json.loads(sample)
        print(f"✓ parse OK")
    except json.JSONDecodeError as e:
        print(f"✗ خطا: {e.msg}")

path.unlink(missing_ok=True)
