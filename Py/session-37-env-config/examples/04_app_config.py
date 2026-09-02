# ============================================================
# جلسه ۳۷ — الگوی config اپلیکیشن
# ============================================================

import logging
import os
from dataclasses import dataclass
from pathlib import Path


def require_env(key: str) -> str:
    val = os.environ.get(key)
    if not val:
        raise RuntimeError(f"Environment variable {key!r} is required")
    return val


@dataclass
class AppConfig:
    env: str
    log_level: int
    data_dir: Path

    @classmethod
    def load(cls) -> "AppConfig":
        env = os.environ.get("APP_ENV", "development")
        level_name = os.environ.get("LOG_LEVEL", "INFO").upper()
        level = getattr(logging, level_name, logging.INFO)
        data = Path(os.environ.get("DATA_DIR", "data"))
        data.mkdir(exist_ok=True)
        return cls(env=env, log_level=level, data_dir=data)


def setup_logging(level: int) -> None:
    logging.basicConfig(
        level=level,
        format="%(asctime)s [%(levelname)s] %(message)s",
    )


def main():
    os.environ.setdefault("APP_ENV", "development")
    os.environ.setdefault("LOG_LEVEL", "DEBUG")

    config = AppConfig.load()
    setup_logging(config.log_level)
    log = logging.getLogger(__name__)

    log.info("Environment: %s", config.env)
    log.info("Data dir: %s", config.data_dir.resolve())

    if config.env == "production":
        require_env("SECRET_KEY")
        log.info("Production mode — secret validated")


if __name__ == "__main__":
    main()
