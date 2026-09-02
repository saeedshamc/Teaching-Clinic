# ============================================================
# جلسه ۲۴ — JSONL و مدیریت خطا
# فایل: 04_jsonl_error.py
# ============================================================

import json


# --- JSON Lines: هر خط یک JSON ---
records = [
    {"event": "login", "user": "علی"},
    {"event": "logout", "user": "مریم"},
    {"event": "purchase", "user": "رضا", "amount": 150000},
]

jsonl_text = "\n".join(
    json.dumps(r, ensure_ascii=False) for r in records
)
print("JSONL:")
print(jsonl_text)

# خواندن JSONL
parsed = [json.loads(line) for line in jsonl_text.splitlines()]
print(f"\n{len(parsed)} رکورد:", parsed)

# --- JSONDecodeError ---
invalid_samples = [
    '{"name": "valid"}',
    '{broken json',
    '{"num": 42}',
]

for sample in invalid_samples:
    try:
        result = json.loads(sample)
        print(f"✓ OK: {result}")
    except json.JSONDecodeError as e:
        print(f"✗ خطا: {e.msg} (pos {e.pos})")

# --- default برای datetime ---
from datetime import datetime


def serialize_datetime(obj):
    if isinstance(obj, datetime):
        return obj.isoformat()
    raise TypeError(f"نوع {type(obj)} پشتیبانی نمی‌شود")


event = {"time": datetime(2024, 1, 15, 10, 30), "action": "start"}
print("\nبا default:", json.dumps(event, default=serialize_datetime, ensure_ascii=False))
