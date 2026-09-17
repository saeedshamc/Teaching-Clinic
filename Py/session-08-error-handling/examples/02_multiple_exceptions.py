# ============================================================
# جلسه ۸ — چند Exception
# فایل: 02_multiple_exceptions.py
# ============================================================

def safe_divide(a, b):
    try:
        return a / b
    except ZeroDivisionError:
        print("تقسیم بر صفر!")
        return None
    except TypeError:
        print("نوع نامعتبر — عدد بدهید")
        return None


print(safe_divide(10, 2))
print(safe_divide(10, 0))
print(safe_divide("10", 2))

# dict
data = {"name": "سعید"}
try:
    print(data["email"])
except KeyError:
    print("کلید email وجود ندارد")
    print("مقدار پیش‌فرض:", data.get("email", "ندارد"))
