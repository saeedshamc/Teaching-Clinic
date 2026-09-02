# ============================================================
# تمرین جلسه ۸ — پاسخ نمونه (خانه)
# ============================================================

def read_int_retry(prompt, max_attempts=3):
    for attempt in range(max_attempts):
        raw = "abc" if attempt == 0 else "25"  # شبیه‌سازی
        try:
            return int(raw)
        except ValueError:
            print(f"تلاش {attempt + 1} ناموفق")
    return None


result = read_int_retry("")
print("نتیجه:", result)

# log خطا
try:
    1 / 0
except ZeroDivisionError as e:
    with open("errors.log", "a", encoding="utf-8") as f:
        f.write(f"ZeroDivisionError: {e}\n")
    print("خطا در errors.log ثبت شد")
