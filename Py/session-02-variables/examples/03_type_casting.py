# ============================================================
# جلسه ۲ — تبدیل نوع (Type Casting)
# فایل: 03_type_casting.py
# ============================================================

# --- str به int ---
age_text = "25"
age = int(age_text)
print("سن (int):", age, "| نوع:", type(age))

# --- str به float ---
price_text = "19.99"
price = float(price_text)
print("قیمت (float):", price)

# --- int/float به str ---
count = 100
message = "تعداد: " + str(count)
print(message)

# --- bool ---
print("bool(1):", bool(1))       # True
print("bool(0):", bool(0))       # False
print("bool(''):", bool(""))     # False — رشته خالی
print("bool('hi'):", bool("hi")) # True

# --- خطای رایج (در کامنت) ---
# int("abc")  # ValueError — رشته غیرعددی
