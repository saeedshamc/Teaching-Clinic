# ============================================================
# تمرین جلسه ۴۷
# هدف: تابع اعتبارسنجی ایمیل خیلی ساده بنویسید.
# ============================================================

def looks_like_email(value: str) -> bool:
    return "@" in value and "." in value.split("@")[-1]

print(looks_like_email("a@b.com"))
print(looks_like_email("invalid"))
