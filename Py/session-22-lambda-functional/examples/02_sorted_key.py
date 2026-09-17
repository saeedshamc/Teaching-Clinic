# ============================================================
# جلسه ۲۲ — sorted با key=lambda
# فایل: 02_sorted_key.py
# ============================================================

students = [
    {"name": "علی", "score": 18, "city": "تهران"},
    {"name": "مریم", "score": 15, "city": "اصفهان"},
    {"name": "رضا", "score": 20, "city": "تهران"},
    {"name": "سارا", "score": 12, "city": "شیراز"},
]

# --- مرتب‌سازی بر اساس نمره (نزولی) ---
by_score = sorted(students, key=lambda s: s["score"], reverse=True)
print("بر اساس نمره:")
for s in by_score:
    print(f"  {s['name']}: {s['score']}")

# --- مرتب‌سازی بر اساس نام ---
by_name = sorted(students, key=lambda s: s["name"])
print("\nبر اساس نام:", [s["name"] for s in by_name])

# --- مرتب‌سازی چندکلیده: شهر سپس نمره ---
by_city_score = sorted(students, key=lambda s: (s["city"], -s["score"]))
print("\nشهر + نمره:")
for s in by_city_score:
    print(f"  {s['city']} — {s['name']}: {s['score']}")

# --- min/max با key ---
top = max(students, key=lambda s: s["score"])
print(f"\nبالاترین نمره: {top['name']} ({top['score']})")
