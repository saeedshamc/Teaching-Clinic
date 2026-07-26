# ============================================================
# جلسه ۲۷ — Logging
# فایل: 01_logging.py
# ============================================================

import logging

logging.basicConfig(level=logging.INFO, format="%(levelname)s: %(message)s")
logging.info("برنامه شروع شد")
logging.warning("مقدار ورودی مشکوک است")
logging.error("خطایی رخ داد")
