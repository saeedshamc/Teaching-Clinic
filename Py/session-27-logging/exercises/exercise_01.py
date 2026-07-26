# ============================================================
# تمرین جلسه ۲۷
# هدف: برای موفقیت و شکست یک عملیات لاگ بنویسید.
# ============================================================

import logging

logging.basicConfig(level=logging.INFO, format="%(levelname)s: %(message)s")


def divide(a, b):
    if b == 0:
        logging.error("تقسیم بر صفر مجاز نیست")
        return None
    logging.info("تقسیم انجام شد")
    return a / b


print(divide(10, 2))
print(divide(10, 0))
