# ============================================================
# تمرین جلسه ۳ — پاسخ نمونه (خانه)
# ============================================================

# ۵. BMI
weight, height = 70, 1.75
bmi = weight / (height ** 2)
if bmi < 18.5:
    cat = "کم‌وزن"
elif bmi < 25:
    cat = "نرمال"
elif bmi < 30:
    cat = "اضافه‌وزن"
else:
    cat = "چاق"
print(f"BMI={bmi:.1f} → {cat}")

# ۶. تخفیف
amount = 600_000
is_member = True
if is_member and amount >= 500_000:
    discount = 0.20
elif is_member and amount >= 200_000:
    discount = 0.15
elif amount >= 500_000:
    discount = 0.10
else:
    discount = 0
final = amount * (1 - discount)
print(f"قیمت نهایی: {final:,.0f}")

# ۷. ATM (شبیه‌سازی)
balance = 1000
choice = "1"
if choice == "1":
    print(f"موجودی: {balance}")
elif choice == "2":
    print("برداشت...")
elif choice == "3":
    print("واریز...")
else:
    print("خروج")
