# ============================================================
# جلسه ۳۷ — Settings dataclass
# ============================================================

import os
from dataclasses import dataclass


def _bool_env(key: str, default: bool = False) -> bool:
    val = os.environ.get(key)
    if val is None:
        return default
    return val.lower() in ("1", "true", "yes", "on")


@dataclass(frozen=True)
class Settings:
    app_name: str
    host: str
    port: int
    debug: bool
    database_url: str
    secret_key: str

    @classmethod
    def from_env(cls) -> "Settings":
        return cls(
            app_name=os.environ.get("APP_NAME", "MyApp"),
            host=os.environ.get("APP_HOST", "127.0.0.1"),
            port=int(os.environ.get("APP_PORT", "8000")),
            debug=_bool_env("DEBUG"),
            database_url=os.environ.get("DATABASE_URL", "sqlite:///local.db"),
            secret_key=os.environ.get("SECRET_KEY", "dev-only-change-me"),
        )

    def server_url(self) -> str:
        return f"http://{self.host}:{self.port}"


# demo با env مصنوعی
os.environ.update({"APP_NAME": "Shop", "DEBUG": "true", "APP_PORT": "9000"})
settings = Settings.from_env()
print(settings)
print("URL:", settings.server_url())

if settings.secret_key == "dev-only-change-me":
    print("⚠ در production SECRET_KEY را تنظیم کنید")
