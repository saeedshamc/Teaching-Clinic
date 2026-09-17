# ============================================================
# جلسه ۵۰ — JSON repository
# فایل: 02_json_repository.py
# ============================================================

import json
from dataclasses import asdict
from pathlib import Path

from dataclasses import dataclass


@dataclass
class Task:
    title: str
    done: bool = False


class TaskRepository:
    def __init__(self, path: Path):
        self.path = path
        self.tasks: list[Task] = []

    def load(self) -> None:
        if not self.path.exists():
            self.tasks = []
            return
        raw = json.loads(self.path.read_text(encoding="utf-8"))
        self.tasks = [Task(**item) for item in raw]

    def save(self) -> None:
        data = [asdict(t) for t in self.tasks]
        self.path.write_text(
            json.dumps(data, ensure_ascii=False, indent=2),
            encoding="utf-8",
        )

    def add(self, task: Task) -> None:
        self.tasks.append(task)


path = Path(__file__).with_name("_demo_tasks.json")
repo = TaskRepository(path)
repo.add(Task("کار اول"))
repo.add(Task("کار دوم", done=True))
repo.save()

repo2 = TaskRepository(path)
repo2.load()
print("بارگذاری شد:", [(t.title, t.done) for t in repo2.tasks])
path.unlink(missing_ok=True)
