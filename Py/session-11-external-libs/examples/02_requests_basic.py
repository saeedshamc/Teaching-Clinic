# ============================================================
# جلسه ۱۱ — requests پایه
# فایل: 02_requests_basic.py
# ============================================================
try:
    import requests
except ImportError:
    print("ابتدا: pip install requests")
    raise SystemExit(1)

URL = "https://httpbin.org/get"

try:
    response = requests.get(URL, timeout=10, params={"course": "python"})
    print("Status:", response.status_code)
    print("Content-Type:", response.headers.get("Content-Type"))
    print("Body (200 chars):", response.text[:200])
except requests.exceptions.RequestException as e:
    print("خطا — اینترنت یا httpbin در دسترس نیست:", e)
    print("فایل 04_offline_mock.py را اجرا کنید.")
