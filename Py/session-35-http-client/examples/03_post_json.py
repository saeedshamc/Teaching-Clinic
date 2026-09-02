# ============================================================
# جلسه ۳۵ — HTTP: POST JSON (شبیه‌سازی)
# ============================================================

import json
from dataclasses import dataclass


@dataclass
class PostResult:
    status: int
    body: dict


def fake_post(url: str, payload: dict) -> PostResult:
    if not payload.get("name"):
        return PostResult(400, {"error": "name required"})
    return PostResult(
        201,
        {"id": 42, "name": payload["name"], "created": True},
    )


user = {"name": "سعید", "email": "s@example.com"}
json_bytes = json.dumps(user, ensure_ascii=False).encode("utf-8")
print("request body:", json_bytes.decode())

result = fake_post("https://api.example.com/users", user)
print(f"\nstatus: {result.status}")
print("response:", json.dumps(result.body, ensure_ascii=False, indent=2))

bad = fake_post("https://api.example.com/users", {})
print("\nبدون name:", bad.body)
