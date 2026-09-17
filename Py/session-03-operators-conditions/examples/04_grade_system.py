# ============================================================
# جلسه ۳ — سیستم نمره
# فایل: 04_grade_system.py
# ============================================================

def grade_label(score: int) -> str:
    """نمره ۰–۱۰۰ را به برچسب تبدیل می‌کند."""
    if score < 0 or score > 100:
        return "نامعتبر"
    if score >= 90:
        return "A — عالی"
    if score >= 80:
        return "B — خوب"
    if score >= 70:
        return "C — متوسط"
    if score >= 60:
        return "D — ضعیف"
    if score >= 50:
        return "E — قبول"
    return "F — مردود"


scores = [95, 82, 67, 45, 150, -5]

for s in scores:
    label = grade_label(s)
    status = "قبول" if s >= 50 and s <= 100 else "—"
    print(f"نمره {s:>3} → {label:<15} {status}")
