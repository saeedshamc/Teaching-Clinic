# ============================================================
# جلسه ۳ — عملگرهای منطقی
# فایل: 03_logical_operators.py
# ============================================================

age = 22
has_ticket = True
is_vip = False

# and — هر دو True
can_enter = age >= 18 and has_ticket
print("ورود عادی:", can_enter)

# or — حداقل یکی
free_drink = is_vip or age >= 65
print("نوشیدنی رایگان:", free_drink)

# not
print("VIP نیست:", not is_vip)

# truthiness
username = ""
if username:
    print("کاربر:", username)
else:
    print("نام کاربری خالی است")

# ترکیب
score = 75
attended = True
passed = score >= 50 and attended
print("قبول:", passed)
