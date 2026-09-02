# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۵ (ساده)
# ============================================================

import json
from io import BytesIO
from urllib.error import HTTPError


class FakeResponse:
    status = 200
    headers = {"Content-Type": "application/json"}

    def __init__(self, body: dict):
        self._body = json.dumps(body, ensure_ascii=False).encode()

    def read(self) -> bytes:
        return self._body

    def __enter__(self):
        return self

    def __exit__(self, *args):
        pass


def fake_urlopen(url: str, timeout: float = 5):
    if "404" in url:
        raise HTTPError(url, 404, "Not Found", hdrs=None, fp=BytesIO(b""))
    return FakeResponse({"url": url, "ok": True})


with fake_urlopen("https://api.test/data") as resp:
    data = json.loads(resp.read().decode())
    print("status:", resp.status)
    print("data:", data)

try:
    fake_urlopen("https://api.test/404")
except HTTPError as e:
    print(f"HTTP {e.code}: {e.reason}")
