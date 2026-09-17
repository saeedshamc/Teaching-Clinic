# ============================================================
# جلسه ۲ — ثابت‌ها و محاسبه عملی
# فایل: 04_constants_demo.py
# ============================================================

# ثابت‌ها با UPPER_SNAKE_CASE (قرارداد Python)
TAX_RATE = 0.09          # ۹٪ مالیات
DISCOUNT_PERCENT = 10    # ۱۰٪ تخفیف

base_price = 500_000
discount = base_price * DISCOUNT_PERCENT / 100
after_discount = base_price - discount
tax = after_discount * TAX_RATE
final_price = after_discount + tax

print(f"قیمت پایه:     {base_price:,.0f} تومان")
print(f"تخفیف ({DISCOUNT_PERCENT}%): {discount:,.0f} تومان")
print(f"مالیات ({TAX_RATE*100:.0f}%):  {tax:,.0f} تومان")
print(f"قیمت نهایی:    {final_price:,.0f} تومان")

# تبدیل دما — بدون input برای اجرای خودکار
celsius = 25
fahrenheit = celsius * 9 / 5 + 32
print(f"\n{celsius}°C = {fahrenheit}°F")
