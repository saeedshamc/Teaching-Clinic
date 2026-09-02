# ============================================================
# تمرین ۴–۵ جلسه ۱۳ — Comprehensionها (خانه)
# ============================================================

# --- ۴. مربع اعداد بزرگ‌تر از ۱۵ ---
nums = [10, 15, 20, 25, 30, 35]
big_squares = [n * n for n in nums if n > 15]
print("مربع‌های بزرگ:", big_squares)

# --- ۵. سیستم نمرات ---
students = [
    {"name": "علی", "score": 18},
    {"name": "مریم", "score": 8},
    {"name": "رضا", "score": 14},
    {"name": "سارا", "score": 19},
]

passed = {s["name"]: s["score"] for s in students if s["score"] >= 10}
print("قبول‌شده‌ها:", passed)

grade_a = [s["name"] for s in students if s["score"] >= 17]
print("درجه A:", grade_a)
