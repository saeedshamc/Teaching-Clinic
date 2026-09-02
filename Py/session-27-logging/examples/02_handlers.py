# ============================================================
# جلسه ۲۷ — Logging: Handlerها
# ============================================================

import logging
from pathlib import Path

log_file = Path(__file__).parent / "demo_app.log"

logger = logging.getLogger("demo")
logger.setLevel(logging.DEBUG)
logger.handlers.clear()

console = logging.StreamHandler()
console.setLevel(logging.INFO)
console.setFormatter(logging.Formatter("%(levelname)s: %(message)s"))

file_handler = logging.FileHandler(log_file, encoding="utf-8")
file_handler.setLevel(logging.DEBUG)
file_handler.setFormatter(
    logging.Formatter("%(asctime)s [%(levelname)s] %(message)s")
)

logger.addHandler(console)
logger.addHandler(file_handler)

logger.debug("این فقط در فایل است")
logger.info("این در کنسول و فایل است")
logger.warning("هشدار")

print(f"\nمحتوای {log_file.name}:")
print(log_file.read_text(encoding="utf-8"))

log_file.unlink(missing_ok=True)
