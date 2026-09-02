# ============================================================
# تمرین ۱ جلسه ۴۳ — پیدا کردن باگ با logging
# ============================================================

import logging

logging.basicConfig(level=logging.DEBUG, format="%(message)s")
log = logging.getLogger(__name__)


def sum_first_n(n: int) -> int:
    """جمع 1 تا n — باگ: یک iteration کم دارد."""
    total = 0
    for i in range(1, n):  # باید range(1, n + 1) باشد
        log.debug("i=%d total=%d", i, total)
        total += i
    return total


def fixed_sum_first_n(n: int) -> int:
    total = 0
    for i in range(1, n + 1):
        total += i
    return total


if __name__ == "__main__":
    print("باگ‌دار sum(5):", sum_first_n(5), "(انتظار: 15)")
    print("درست sum(5):", fixed_sum_first_n(5))
