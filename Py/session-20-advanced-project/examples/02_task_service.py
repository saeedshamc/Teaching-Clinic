# ============================================================
# جلسه ۲۰ — TaskService با type hint
# فایل: 02_task_service.py
# ============================================================

from typing import TypedDict


class Task(TypedDict):
    id: int
    title: str
    done: bool
    priority: str


class TaskService:
    def __init__(self):
        self.tasks: list[Task] = []
        self._next_id = 1

    def add(self, title: str, priority: str = "medium") -> Task:
        task: Task = {
            "id": self._next_id,
            "title": title,
            "done": False,
            "priority": priority,
        }
        self._next_id += 1
        self.tasks.append(task)
        return task

    def list_tasks(self) -> None:
        for t in self.tasks:
            status = "✓" if t["done"] else " "
            print(f"  [{status}] #{t['id']} {t['title']} ({t['priority']})")

    def complete(self, task_id: int) -> bool:
        for t in self.tasks:
            if t["id"] == task_id:
                t["done"] = True
                return True
        return False

    def pending_count(self) -> int:
        return sum(1 for t in self.tasks if not t["done"])

    def high_priority_pending(self) -> list[Task]:
        return [
            t for t in self.tasks
            if t["priority"] == "high" and not t["done"]
        ]


service = TaskService()
service.add("یادگیری Python", "high")
service.add("خرید", "low")
service.add("تمرین", "medium")
service.list_tasks()
service.complete(1)
print(f"\nکارهای باز: {service.pending_count()}")
print("اولویت بالا:", [t["title"] for t in service.high_priority_pending()])
