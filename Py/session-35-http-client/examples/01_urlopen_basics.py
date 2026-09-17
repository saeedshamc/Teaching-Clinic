# ============================================================
# جلسه ۳۵ — HTTP: urlopen پایه (شبیه‌سازی)
# ============================================================

from io import BytesIO
from urllib.error import HTTPError


class FakeHTTPResponse:
    """شبیه‌سازی پاسخ HTTP برای آموزش بدون شبکه."""

    def __init__(self, status: int, body: bytes, headers: dict | None = None):
        self.status = status
        self._body = body
        self.headers = headers or {"Content-Type": "text/html; charset=utf-8"}

    def read(self, n: int = -1) -> bytes:
        return self._body if n < 0 else self._body[:n]

    def __enter__(self):
        return self

    def __exit__(self, *args):
        pass


def fake_urlopen(url: str, timeout: float = 10):
    if "404" in url:
        raise HTTPError(url, 404, "Not Found", hdrs=None, fp=BytesIO(b""))
    html = f"<html><title>صفحه {url}</title><body>سلام</body></html>"
    return FakeHTTPResponse(200, html.encode("utf-8"))


with fake_urlopen("https://example.com") as resp:
    print("status:", resp.status)
    print("type:", resp.headers["Content-Type"])
    body = resp.read().decode("utf-8")
    print("body:", body[:80], "...")

try:
    fake_urlopen("https://example.com/404")
except HTTPError as e:
    print(f"\nHTTPError: {e.code} {e.reason}")

print("\nنکته: در production از urlopen واقعی استفاده کنید.")
