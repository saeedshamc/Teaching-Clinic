# ============================================================
# جلسه ۵۰ — Task model
# فایل: 01_task_model.py
# ============================================================

from dataclasses import dataclass, field, asdict
from datetime import datetime


@dataclass
class Task:
    """یک کار در سیستم مدیریت.

    Attributes:
        title: عنوان کار
        done: آیا انجام شده
        created_at: زمان ایجاد ISO
    """

    title: str
    done: bool = False
    created_at: str = field(default_factory=lambda: datetime.now().isoformat())

    def mark_done(self) -> None:
        self.done = True


tasks = [
    Task("یادگیری Python"),
    Task("ساخت پروژه capstone"),
]
tasks[0].mark_done()

for t in tasks:
    status = "✅" if t.done else "⏳"
    print(f"{status} {t.title} ({t.created_at[:10]})")

print("\nJSON-ready:", asdict(tasks[0]))
