# ============================================================
# جلسه ۴۳ — logging برای دیباگ
# فایل: 04_logging_debug.py
# ============================================================

import logging

logging.basicConfig(
    level=logging.DEBUG,
    format="%(levelname)s | %(funcName)s | %(message)s",
)
logger = logging.getLogger(__name__)


def factorial(n: int) -> int:
    logger.debug("شروع factorial(n=%d)", n)
    if n <= 1:
        logger.debug("پایه: return 1")
        return 1
    result = n * factorial(n - 1)
    logger.debug("نتیجه n=%d → %d", n, result)
    return result


def process_order(order_id: str, items: list[str]) -> None:
    logger.info("پردازش سفارش %s با %d قلم", order_id, len(items))
    for item in items:
        logger.debug("  - %s", item)
    logger.info("سفارش %s تکمیل شد", order_id)


print("factorial(5) =", factorial(5))
process_order("ORD-001", ["کتاب", "ماوس"])
