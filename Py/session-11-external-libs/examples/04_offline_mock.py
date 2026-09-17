# ============================================================
# جلسه ۱۱ — mock بدون اینترنت
# فایل: 04_offline_mock.py
# ============================================================
import json


class MockResponse:
    """شبیه‌ساز پاسخ requests برای آموزش offline."""

    def __init__(self, status_code, data):
        self.status_code = status_code
        self._data = data
        self.headers = {"Content-Type": "application/json"}

    @property
    def text(self):
        return json.dumps(self._data, ensure_ascii=False)

    def json(self):
        return self._data

    def raise_for_status(self):
        if self.status_code >= 400:
            raise Exception(f"HTTP {self.status_code}")


def mock_get(url):
    if "404" in url:
        return MockResponse(404, {"error": "not found"})
    return MockResponse(200, {"url": url, "message": "offline demo OK"})


# استفاده
r = mock_get("https://example.com/api/users")
print("Status:", r.status_code)
print("JSON:", r.json())

r404 = mock_get("https://example.com/404")
print("404 test:", r404.status_code)
