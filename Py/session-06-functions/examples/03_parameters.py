# ============================================================
# جلسه ۶ — پارامترها
# فایل: 03_parameters.py
# ============================================================

def describe_product(name, price, currency="تومان", discount=0):
    """قیمت نهایی محصول را نمایش می‌دهد."""
    final = price * (1 - discount / 100)
    print(f"{name}: {final:,.0f} {currency}")


describe_product("لپ‌تاپ", 25_000_000)
describe_product("ماوس", 500_000, discount=10)
describe_product(name="کیبورد", price=800_000, discount=5, currency="تومان")


def repeat(text, times=3):
    return text * times


print(repeat("Ha"))
print(repeat("Py ", 5))
