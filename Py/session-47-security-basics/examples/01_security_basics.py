# ============================================================
# جلسه ۴۷ — Security Basics
# فایل: 01_security_basics.py
# ============================================================

def is_safe_username(name: str) -> bool:
    return name.isalnum() and 3 <= len(name) <= 20

user_input = "saeed"
print("نام امن؟", is_safe_username(user_input))
print("نام ناامن؟", is_safe_username("a OR 1=1"))

sql = "SELECT * FROM users WHERE name=?"
params = (user_input,)
print("کوئری امن:", sql, params)
print("هرگز رمز را داخل کد قرار ندهید.")
