# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۵ (پیشرفته)
# ============================================================

import json
import time
from dataclasses import dataclass
from typing import Any
from urllib.error import HTTPError


@dataclass
class ApiResponse:
    status: int
    data: Any


class ApiClient:
    def __init__(self, base_url: str, token: str = ""):
        self.base_url = base_url.rstrip("/")
        self.token = token

    def _headers(self) -> dict:
        h = {"Accept": "application/json", "User-Agent": "Exercise/1.0"}
        if self.token:
            h["Authorization"] = f"Bearer {self.token}"
        return h

    def get(self, path: str) -> ApiResponse:
        # شبیه‌سازی
        if path == "/fail":
            raise HTTPError(self.base_url, 503, "Unavailable", hdrs=None, fp=None)
        return ApiResponse(200, {"path": path, "headers_sent": self._headers()})

    def get_with_retry(self, path: str, retries: int = 3) -> ApiResponse:
        for attempt in range(retries):
            try:
                return self.get(path)
            except HTTPError as e:
                if e.code < 500 or attempt == retries - 1:
                    raise
                time.sleep(0.05 * (2**attempt))
        raise RuntimeError("unreachable")


client = ApiClient("https://api.example.com", "my-token")
r = client.get("/users")
print(json.dumps(r.data, ensure_ascii=False, indent=2))

try:
    client.get_with_retry("/fail")
except HTTPError as e:
    print(f"after retries: {e.code}")
