# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۷ (ساده)
# ============================================================

import os
from dataclasses import dataclass


def env_bool(key: str, default: bool = False) -> bool:
    v = os.environ.get(key)
    return default if v is None else v.lower() in ("1", "true", "yes")


@dataclass(frozen=True)
class Settings:
    host: str
    port: int
    debug: bool

    @classmethod
    def from_env(cls) -> "Settings":
        return cls(
            host=os.environ.get("APP_HOST", "localhost"),
            port=int(os.environ.get("APP_PORT", "8000")),
            debug=env_bool("DEBUG"),
        )


os.environ["DEBUG"] = "true"
s = Settings.from_env()
print(f"{s.host}:{s.port} debug={s.debug}")
