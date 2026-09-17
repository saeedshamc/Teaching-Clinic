# ============================================================
# جلسه ۱۱ — راهنمای pip (بدون اجرای pip)
# فایل: 01_pip_demo.py
# ============================================================
"""دستورات پرکاربرد pip — در ترمینال اجرا کنید."""

COMMANDS = """
# نصب پکیج
pip install requests

# نصب نسخه مشخص
pip install requests==2.31.0

# از requirements
pip install -r requirements.txt

# لیست پکیج‌ها
pip list

# ذخیره وابستگی‌ها
pip freeze > requirements.txt

# حذف
pip uninstall requests -y
"""

print("=== دستورات pip ===")
print(COMMANDS)

try:
    import requests
    print(f"requests نصب است — نسخه: {requests.__version__}")
except ImportError:
    print("requests نصب نیست — pip install requests")
