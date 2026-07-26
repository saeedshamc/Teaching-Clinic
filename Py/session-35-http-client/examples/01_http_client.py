# ============================================================
# جلسه ۳۵ — HTTP Client
# فایل: 01_http_client.py
# ============================================================

from urllib.request import Request, urlopen

# برای اجرا بدون شبکه، پاسخ را شبیه‌سازی می‌کنیم.
class FakeResponse:
    status = 200
    def read(self, n=-1):
        data = b"<html>Hello</html>"
        return data if n < 0 else data[:n]

response = FakeResponse()
print("وضعیت:", response.status)
print("محتوا:", response.read().decode())
print("نکته: در پروژه واقعی از urlopen استفاده کنید.")
