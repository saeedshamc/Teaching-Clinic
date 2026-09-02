# ============================================================
# تمرین جلسه ۶ — پاسخ نمونه (خانه)
# ============================================================

def calc(a, b, op):
    if op == "+":
        return a + b
    if op == "-":
        return a - b
    if op == "*":
        return a * b
    if op == "/":
        return a / b if b != 0 else None
    return None


def password_check(pwd):
    has_digit = any(c.isdigit() for c in pwd)
    return len(pwd) >= 8 and has_digit


print(calc(10, 3, "+"))
print("رمز OK:", password_check("abc12345"))
print("رمز ضعیف:", password_check("abc"))
