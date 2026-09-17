# ============================================================
# پاسخ نمونه — تمرین جلسه ۲۶ (پیشرفته)
# ============================================================

import shutil
from datetime import datetime
from pathlib import Path

ROOT = Path(__file__).parent
DATA = ROOT / "data"
LOGS = ROOT / "logs"
BACKUP = ROOT / "backup"


def largest_file(folder: Path) -> Path | None:
    files = [p for p in folder.rglob("*") if p.is_file()]
    return max(files, key=lambda p: p.stat().st_size) if files else None


def count_lines(path: Path) -> int:
    if not path.exists():
        return 0
    return sum(1 for _ in path.open(encoding="utf-8"))


def backup_txt_files(source: Path, dest: Path) -> int:
    dest.mkdir(parents=True, exist_ok=True)
    count = 0
    for txt in source.glob("*.txt"):
        shutil.copy2(txt, dest / txt.name)
        count += 1
    return count


# راه‌اندازی
for d in (DATA, LOGS):
    d.mkdir(exist_ok=True)

(DATA / "a.txt").write_text("خط یک\nخط دو\n", encoding="utf-8")
(DATA / "b.txt").write_text("تک خط\n", encoding="utf-8")

big = largest_file(DATA)
print("بزرگترین:", big.name if big else "—")
print("خطوط a.txt:", count_lines(DATA / "a.txt"))

copied = backup_txt_files(DATA, BACKUP)
print(f"کپی شد: {copied} فایل")

log = LOGS / f"{datetime.today():%Y-%m-%d}.log"
log.write_text(f"[{datetime.now():%H:%M:%S}] backup انجام شد\n", encoding="utf-8")

# پاکسازی
shutil.rmtree(BACKUP, ignore_errors=True)
shutil.rmtree(DATA, ignore_errors=True)
shutil.rmtree(LOGS, ignore_errors=True)
