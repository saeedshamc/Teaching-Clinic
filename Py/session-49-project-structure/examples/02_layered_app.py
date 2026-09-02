# ============================================================
# جلسه ۴۹ — layered application
# فایل: 02_layered_app.py
# ============================================================

from dataclasses import dataclass


@dataclass
class Task:
    title: str
    done: bool = False


class TaskRepository:
    def __init__(self):
        self._tasks: list[Task] = []

    def add(self, task: Task) -> None:
        self._tasks.append(task)

    def all(self) -> list[Task]:
        return list(self._tasks)


class TaskService:
    def __init__(self, repo: TaskRepository):
        self.repo = repo

    def create(self, title: str) -> None:
        if not title.strip():
            raise ValueError("عنوان خالی")
        self.repo.add(Task(title=title.strip()))

    def pending(self) -> list[Task]:
        return [t for t in self.repo.all() if not t.done]


class TaskCLI:
    def __init__(self, service: TaskService):
        self.service = service

    def run_add(self, title: str) -> None:
        self.service.create(title)
        print(f"✅ کار «{title}» اضافه شد")


repo = TaskRepository()
service = TaskService(repo)
cli = TaskCLI(service)
cli.run_add("یادگیری ساختار پروژه")
print("مانده:", [t.title for t in service.pending()])
