# ============================================================
# جلسه ۲۷ — Logging: پایه
# ============================================================

import logging

logging.basicConfig(
    level=logging.DEBUG,
    format="%(asctime)s | %(levelname)-8s | %(message)s",
    datefmt="%H:%M:%S",
)

logging.debug("جزئیات دیباگ — فقط با level=DEBUG")
logging.info("برنامه شروع شد")
logging.warning("مقدار ورودی مشکوک است")
logging.error("عملیات ناموفق")
logging.critical("سرویس متوقف شد")

def divide(a, b):
    logging.info("تقسیم %s / %s", a, b)
    return a / b

print("نتیجه:", divide(10, 2))
