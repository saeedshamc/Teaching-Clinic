# ============================================================
# پاسخ نمونه — تمرین جلسه ۲۷ (ساده)
# ============================================================

import logging
import math

logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s | %(levelname)s | %(message)s",
    datefmt="%H:%M:%S",
)

log = logging.getLogger("math_ops")


def safe_sqrt(x: float) -> float | None:
    if x < 0:
        log.error("ورودی منفی: %s", x)
        return None
    result = math.sqrt(x)
    log.info("sqrt(%s) = %s", x, result)
    return result


log.info("شروع")
print(safe_sqrt(16))
print(safe_sqrt(-4))
log.warning("تست تمام شد")
