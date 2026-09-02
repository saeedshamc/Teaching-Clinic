# ============================================================
# جلسه ۲۶ — pathlib: ساختار پروژه
# ============================================================

from datetime import datetime
from pathlib import Path

PROJECT = Path(__file__).resolve().parent.parent
DATA = PROJECT / "data"
LOGS = PROJECT / "logs"

for folder in (DATA, LOGS):
    folder.mkdir(exist_ok=True)

users_file = DATA / "users.txt"
if not users_file.exists():
    users_file.write_text("سعید\nمریم\nعلی\n", encoding="utf-8")

log_file = LOGS / f"{datetime.today():%Y-%m-%d}.log"
entry = f"[{datetime.now():%H:%M:%S}] برنامه اجرا شد\n"
with log_file.open("a", encoding="utf-8") as f:
    f.write(entry)

print("کاربران:")
for name in users_file.read_text(encoding="utf-8").splitlines():
    print(" -", name.strip())

print("لاگ:", log_file.read_text(encoding="utf-8").strip())
