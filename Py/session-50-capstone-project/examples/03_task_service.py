# ============================================================
# جلسه ۵۰ — Task Service کامل
# فایل: 03_task_service.py
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
        cleaned = title.strip()
        if not cleaned:
            raise ValueError("عنوان خالی مجاز نیست")
        self.tasks.append(Task(title=cleaned))

    def complete(self, index: int) -> None:
        if index < 0 or index >= len(self.tasks):
            raise IndexError("اندیس نامعتبر")
        self.tasks[index].done = True

    def pending_count(self) -> int:
        return sum(1 for t in self.tasks if not t.done)

    def list_done_titles(self) -> list[str]:
        return [t.title for t in self.tasks if t.done]

    def save(self) -> None:
        data = [asdict(t) for t in self.tasks]
        self.path.write_text(json.dumps(data, ensure_ascii=False, indent=2), encoding="utf-8")

    def load(self) -> None:
        if not self.path.exists():
            return
        raw = json.loads(self.path.read_text(encoding="utf-8"))
        self.tasks = [Task(**item) for item in raw]


path = Path(__file__).with_name("tasks.json")
service = TaskService(path)
service.add("یادگیری Python")
service.add("پروژه نهایی")
service.complete(0)
service.save()
service.load()
print("کارها:", [asdict(t) for t in service.tasks])
print("مانده:", service.pending_count())
print("انجام‌شده:", service.list_done_titles())
path.unlink(missing_ok=True)
