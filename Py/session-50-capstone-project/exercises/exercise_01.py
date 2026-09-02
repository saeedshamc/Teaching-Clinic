# ============================================================
# تمرین ۱ جلسه ۵۰ — TaskService
# ============================================================

from dataclasses import dataclass, asdict
import json
from pathlib import Path


@dataclass
class Task:
    title: str
    done: bool = False


class TaskService:
    def __init__(self, path: Path | None = None):
        self.path = path
        self.tasks: list[Task] = []

    def add(self, title: str) -> None:
        if not title.strip():
            raise ValueError("عنوان خالی")
        self.tasks.append(Task(title.strip()))

    def complete(self, index: int) -> None:
        if not 0 <= index < len(self.tasks):
            raise IndexError("اندیس نامعتبر")
        self.tasks[index].done = True

    def pending_count(self) -> int:
        return sum(1 for t in self.tasks if not t.done)

    def save(self, path: Path) -> None:
        path.write_text(
            json.dumps([asdict(t) for t in self.tasks], ensure_ascii=False),
            encoding="utf-8",
        )


if __name__ == "__main__":
    svc = TaskService()
    svc.add("تمرین capstone")
    svc.complete(0)
    print("مانده:", svc.pending_count())
