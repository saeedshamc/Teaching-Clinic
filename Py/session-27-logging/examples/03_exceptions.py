# ============================================================
# جلسه ۲۷ — Logging: Exception
# ============================================================

import logging

logging.basicConfig(level=logging.INFO, format="%(levelname)s: %(message)s")


def safe_divide(a, b):
    try:
        return a / b
    except ZeroDivisionError:
        logging.exception("تقسیم بر صفر — a=%s b=%s", a, b)
        return None
    except TypeError as e:
        logging.error("نوع نامعتبر: %s", e)
        return None


print("10/2 =", safe_divide(10, 2))
print("10/0 =", safe_divide(10, 0))
print("10/'x' =", safe_divide(10, "x"))
