# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۷ (پیشرفته)
# ============================================================

import os
from dataclasses import dataclass
from pathlib import Path


def require_env(key: str) -> str:
    val = os.environ.get(key)
    if not val:
        raise RuntimeError(f"{key} required")
    return val


def load_dotenv(path: Path) -> None:
    if not path.exists():
        return
    for line in path.read_text(encoding="utf-8").splitlines():
        line = line.strip()
        if line and not line.startswith("#") and "=" in line:
            k, _, v = line.partition("=")
            os.environ.setdefault(k.strip(), v.strip())


@dataclass(frozen=True)
class Settings:
    host: str
    port: int
    secret_key: str

    @classmethod
    def from_env(cls, *, production: bool = False) -> "Settings":
        port = int(os.environ.get("APP_PORT", "8000"))
        if not 1 <= port <= 65535:
            raise ValueError("port invalid")
        secret = (
            require_env("SECRET_KEY")
            if production
            else os.environ.get("SECRET_KEY", "dev")
        )
        return cls(
            host=os.environ.get("APP_HOST", "127.0.0.1"),
            port=port,
            secret_key=secret,
        )


env = Path(__file__).parent / "_t.env"
env.write_text("APP_PORT=9090\n", encoding="utf-8")
load_dotenv(env)
print(Settings.from_env())
env.unlink(missing_ok=True)
