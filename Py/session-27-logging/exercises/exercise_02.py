# ============================================================
# پاسخ نمونه — تمرین جلسه ۲۷ (پیشرفته)
# ============================================================

import logging
import math
from logging.handlers import RotatingFileHandler
from pathlib import Path


def setup_dual_logging(log_path: Path) -> logging.Logger:
    logger = logging.getLogger("calculator")
    logger.setLevel(logging.DEBUG)
    logger.handlers.clear()

    fmt = logging.Formatter("%(asctime)s [%(levelname)s] %(message)s")

    console = logging.StreamHandler()
    console.setLevel(logging.INFO)
    console.setFormatter(fmt)

    rotating = RotatingFileHandler(
        log_path, maxBytes=500, backupCount=3, encoding="utf-8"
    )
    rotating.setLevel(logging.DEBUG)
    rotating.setFormatter(fmt)

    logger.addHandler(console)
    logger.addHandler(rotating)
    return logger


def safe_divide(logger: logging.Logger, a, b):
    logger.debug("divide(%s, %s)", a, b)
    try:
        return a / b
    except ZeroDivisionError:
        logger.exception("تقسیم بر صفر")
        return None


def main():
    log_path = Path(__file__).parent / "calc.log"
    log = setup_dual_logging(log_path)

    for pair in [(10, 2), (7, 0), (8, 4)]:
        result = safe_divide(log, *pair)
        if result is not None:
            log.info("نتیجه: %s", result)

    log_path.unlink(missing_ok=True)


if __name__ == "__main__":
    main()
