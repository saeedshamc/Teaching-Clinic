# ============================================================
# جلسه ۸ — مدیریت خطا با try/except
# فایل: 01_try_except.py
# ============================================================

try:
    number_text = input("یک عدد وارد کنید: ")
    number = int(number_text)
    result = 10 / number
    print("نتیجه:", result)
except ValueError:
    print("لطفاً یک عدد معتبر وارد کنید.")
except ZeroDivisionError:
    print("تقسیم بر صفر مجاز نیست.")
