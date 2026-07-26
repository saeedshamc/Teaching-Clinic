# ============================================================
# تمرین جلسه ۱۱
# هدف: کتابخانه requests را امتحان کنید.
# ============================================================

try:
    import requests
except ImportError:
    print("requests نصب نشده است.")
    raise SystemExit(0)

response = requests.get("https://example.com", timeout=10)
print("کد وضعیت:", response.status_code)
