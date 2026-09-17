# ============================================================
# تمرین جلسه ۲ — پاسخ نمونه (خانه)
# ============================================================

# --- ۵. ماشین‌حساب ---
a = 20
b = 4
print(f"{a} + {b} = {a + b}")
print(f"{a} - {b} = {a - b}")
print(f"{a} * {b} = {a * b}")
print(f"{a} / {b} = {a / b}" if b != 0 else "تقسیم بر صفر!")

# --- ۶. فاکتور ---
base = 1_000_000
discount_pct = 15
tax_rate = 0.09
after_disc = base * (1 - discount_pct / 100)
final = after_disc * (1 + tax_rate)
print(f"قیمت نهایی: {final:,.0f} تومان")

# --- ۷. BMI ---
weight = 70
height = 1.75
bmi = weight / (height ** 2)
print(f"BMI: {bmi:.2f}")
