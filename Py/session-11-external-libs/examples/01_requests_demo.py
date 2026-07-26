# ============================================================
# جلسه ۱۱ — نمونه استفاده از requests
# فایل: 01_requests_demo.py
# ============================================================

try:
    import requests
except ImportError:
    print("کتابخانه requests نصب نشده است. با دستور 'pip install requests' آن را نصب کنید.")
    raise SystemExit(0)

try:
    response = requests.get("https://example.com", timeout=10)
    print("وضعیت HTTP:", response.status_code)
    print("متن صفحه:", response.text[:100])
except requests.RequestException as exc:
    print("درخواست با خطا مواجه شد:", exc)
