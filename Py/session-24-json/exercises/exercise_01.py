# ============================================================
# تمرین ۱–۳ جلسه ۲۴ — JSON (کلاسی)
# ============================================================

import json

person = {"name": "سعید", "age": 30, "city": "تهران"}

# --- ۱. dumps ---
text = json.dumps(person, ensure_ascii=False, indent=2)
print("JSON string:")
print(text)

# --- ۲. loads ---
loaded = json.loads(text)
print("\nname:", loaded["name"])

# --- ۳. ensure_ascii ---
with_ascii = json.dumps({"سلام": "دنیا"}, ensure_ascii=True)
without_ascii = json.dumps({"سلام": "دنیا"}, ensure_ascii=False)
print("\nبا ensure_ascii=True:", with_ascii[:30], "...")
print("با ensure_ascii=False:", without_ascii)
