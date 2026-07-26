# ============================================================
# جلسه ۳ — شرط‌ها
# فایل: 02_conditions.py
# ============================================================

age_input = input("سن شما چند است؟ ")
try:
    age = int(age_input)
except ValueError:
    age = 20

if age >= 18:
    print("شما بزرگسال هستید.")
else:
    print("شما جوان هستید.")

score = 85
if score >= 90:
    print("عالی")
elif score >= 70:
    print("خوب")
else:
    print("نیاز به تلاش بیشتر")
