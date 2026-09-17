# ============================================================
# جلسه ۲۰ — ذخیره و بارگذاری JSON
# فایل: 03_json_storage.py
# ============================================================

import json
from pathlib import Path
from typing import TypedDict


class Task(TypedDict):
    id: int
    title: str
    done: bool


TASKS_FILE = Path(__file__).parent / "tasks_sample.json"


def save_tasks(tasks: list[Task], path: Path) -> None:
    with path.open("w", encoding="utf-8") as f:
        json.dump(tasks, f, ensure_ascii=False, indent=2)
    print(f"💾 {len(tasks)} کار در {path.name} ذخیره شد")


def load_tasks(path: Path) -> list[Task]:
    if not path.exists():
        return []
    with path.open(encoding="utf-8") as f:
        return json.load(f)


# --- demo ---
sample_tasks: list[Task] = [
    {"id": 1, "title": "یادگیری Python", "done": False},
    {"id": 2, "title": "نوشتن تمرین", "done": True},
]

save_tasks(sample_tasks, TASKS_FILE)
loaded = load_tasks(TASKS_FILE)
print("📂 بارگذاری شده:")
for t in loaded:
    print(f"  #{t['id']} {t['title']} — {'✓' if t['done'] else ' '}")

# پاکسازی فایل نمونه
TASKS_FILE.unlink(missing_ok=True)
