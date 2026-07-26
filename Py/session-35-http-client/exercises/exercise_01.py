# ============================================================
# تمرین جلسه ۳۵
# هدف: تابعی بنویسید که status و طول محتوا را برگرداند.
# ============================================================

def summarize(status: int, content: bytes) -> str:
    return f"status={status}, length={len(content)}"

print(summarize(200, b"hello"))
