# ============================================================
# جلسه ۵۰ — Capstone: Task Service
# فایل: 01_task_service.py
# ============================================================

from dataclasses import dataclass, asdict
import json
from pathlib import Path

@dataclass
class Task:
    title: str
    done: bool = False

class TaskService:
    def __init__(self, path: Path):
        self.path = path
        self.tasks: list[Task] = []

    def add(self, title: str) -> None:
        self.tasks.append(Task(title=title))

    def complete(self, index: int) -> None:
        self.tasks[index].done = True

    def save(self) -> None:
        data = [asdict(task) for task in self.tasks]
        self.path.write_text(json.dumps(data, ensure_ascii=False, indent=2), encoding="utf-8")

    def load(self) -> None:
        if not self.path.exists():
            return
        raw = json.loads(self.path.read_text(encoding="utf-8"))
        self.tasks = [Task(**item) for item in raw]

    def pending_count(self) -> int:
        return sum(1 for task in self.tasks if not task.done)

path = Path(__file__).with_name("tasks.json")
service = TaskService(path)
service.add("یادگیری Python")
service.add("ساختن پروژه نهایی")
service.complete(0)
service.save()
service.load()
print("کارها:", [asdict(t) for t in service.tasks])
print("مانده:", service.pending_count())
path.unlink(missing_ok=True)
