# ============================================================
# جلسه ۲۷ — Logging: الگوی اپلیکیشن
# ============================================================

import logging
from pathlib import Path


def setup_logging(log_path: Path, level=logging.INFO) -> logging.Logger:
    logger = logging.getLogger("shop")
    logger.setLevel(level)
    logger.handlers.clear()

    fmt = logging.Formatter("%(asctime)s [%(levelname)s] %(name)s: %(message)s")

    sh = logging.StreamHandler()
    sh.setFormatter(fmt)
    logger.addHandler(sh)

    fh = logging.FileHandler(log_path, encoding="utf-8")
    fh.setFormatter(fmt)
    logger.addHandler(fh)
    return logger


def process_order(logger: logging.Logger, item: str, qty: int) -> bool:
    logger.info("سفارش: %s x%d", item, qty)
    if qty <= 0:
        logger.warning("تعداد نامعتبر: %d", qty)
        return False
    logger.info("سفارش ثبت شد")
    return True


def main():
    log_path = Path(__file__).parent / "shop.log"
    log = setup_logging(log_path)
    log.info("=== شروع ===")
    process_order(log, "کتاب پایتون", 2)
    process_order(log, "ماوس", 0)
    log.info("=== پایان ===")
    print(f"\nلاگ ذخیره در {log_path.name}")
    log_path.unlink(missing_ok=True)


if __name__ == "__main__":
    main()
