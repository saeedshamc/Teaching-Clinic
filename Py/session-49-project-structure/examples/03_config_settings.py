# ============================================================
# جلسه ۴۹ — Settings
# فایل: 03_config_settings.py
# ============================================================

import os
from dataclasses import dataclass


@dataclass
class Settings:
    app_name: str = "MyApp"
    debug: bool = False
    db_path: str = "app.db"
    log_level: str = "INFO"

    @classmethod
    def from_env(cls) -> "Settings":
        return cls(
            debug=os.getenv("DEBUG", "0") == "1",
            db_path=os.getenv("DB_PATH", "app.db"),
            log_level=os.getenv("LOG_LEVEL", "INFO"),
        )


default = Settings()
from_env = Settings.from_env()

print("default:", default)
print("from_env debug:", from_env.debug)
