# ============================================================
# جلسه ۳۵ — HTTP: API Client pattern
# ============================================================

import json
import time
from dataclasses import dataclass
from typing import Any
from urllib.error import HTTPError, URLError


@dataclass
class ApiResponse:
    status: int
    data: Any


class FakeApiClient:
    """کلاینت API قابل تست — بدون شبکه واقعی."""

    def __init__(self, base_url: str, token: str = ""):
        self.base_url = base_url.rstrip("/")
        self.token = token
        self._store: dict[int, dict] = {1: {"id": 1, "name": "پایتون"}}

    def _headers(self) -> dict:
        h = {"Accept": "application/json", "User-Agent": "ApiClient/1.0"}
        if self.token:
            h["Authorization"] = f"Bearer {self.token}"
        return h

    def get(self, path: str) -> ApiResponse:
        if path == "/users/1":
            return ApiResponse(200, self._store[1])
        if path == "/users/999":
            raise HTTPError(self.base_url + path, 404, "Not Found", hdrs=None, fp=None)
        return ApiResponse(200, list(self._store.values()))

    def get_with_retry(self, path: str, retries: int = 3) -> ApiResponse:
        last_err = None
        for attempt in range(retries):
            try:
                return self.get(path)
            except HTTPError as e:
                last_err = e
                if e.code < 500:
                    raise
                time.sleep(0.1 * (2**attempt))
        raise last_err  # type: ignore


def main():
    client = FakeApiClient("https://api.example.com", token="secret")
    print("headers:", client._headers())

    r = client.get("/users/1")
    print(f"GET /users/1 → {r.status}:", json.dumps(r.data, ensure_ascii=False))

    try:
        client.get("/users/999")
    except HTTPError as e:
        print(f"404 handled: {e.code}")

    all_users = client.get("/users")
    print("all users:", all_users.data)


if __name__ == "__main__":
    main()
