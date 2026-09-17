# ============================================================
# جلسه ۳۵ — HTTP: Request و headers
# ============================================================

from dataclasses import dataclass, field


@dataclass
class FakeRequest:
    url: str
    headers: dict = field(default_factory=dict)
    method: str = "GET"


@dataclass
class FakeResponse:
    status: int
    body: bytes
    request_headers: dict

    def read(self) -> bytes:
        return self.body


def send(req: FakeRequest) -> FakeResponse:
    ua = req.headers.get("User-Agent", "unknown")
    accept = req.headers.get("Accept", "*/*")
    body = (
        f'{{"url": "{req.url}", "method": "{req.method}", '
        f'"user_agent": "{ua}", "accept": "{accept}"}}'
    ).encode()
    return FakeResponse(200, body, req.headers)


req = FakeRequest(
    url="https://api.example.com/users",
    headers={
        "User-Agent": "PythonCourse/1.0",
        "Accept": "application/json",
        "Authorization": "Bearer demo-token",
    },
)

resp = send(req)
print("status:", resp.status)
print("response:", resp.read().decode())
