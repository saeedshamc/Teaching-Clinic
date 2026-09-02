# ============================================================
# جلسه ۴۳ — راهنمای دستورات pdb
# فایل: 02_debug_commands.py
# ============================================================

PDB_COMMANDS = {
    "n (next)": "اجرای خط فعلی، بدون ورود به تابع",
    "s (step)": "ورود به داخل تابع فراخوانی‌شده",
    "c (continue)": "ادامه تا breakpoint بعدی",
    "l (list)": "نمایش ۱۱ خط کد اطراف",
    "p expr": "چاپ مقدار expression",
    "pp expr": "pretty print",
    "w (where)": "stack trace فعلی",
    "u / d": "بالا / پایین در stack",
    "q (quit)": "خروج از debugger",
    "h (help)": "راهنما",
}

print("=== دستورات pdb ===")
for cmd, desc in PDB_COMMANDS.items():
    print(f"  {cmd:20} → {desc}")

print("\n=== workflow پیشنهادی ===")
print("1. breakpoint() بگذارید")
print("2. python script.py")
print("3. p variable — مقادیر را ببینید")
print("4. n — خط‌به‌خط")
print("5. c — ادامه یا q — خروج")
