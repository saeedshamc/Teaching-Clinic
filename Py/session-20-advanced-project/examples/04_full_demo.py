# ============================================================
# جلسه ۲۰ — demo کامل Task Manager
# فایل: 04_full_demo.py
# ============================================================

import json
from functools import wraps
from pathlib import Path
from typing import TypedDict


class Task(TypedDict):
    id: int
    title: str
    done: bool


class TaskService:
    def __init__(self):
        self.tasks: list[Task] = []
        self._next_id = 1

    def add(self, title: str) -> Task:
        task: Task = {"id": self._next_id, "title": title, "done": False}
        self._next_id += 1
        self.tasks.append(task)
        return task

    def complete(self, task_id: int) -> bool:
        for t in self.tasks:
            if t["id"] == task_id:
                t["done"] = True
                return True
        return False

    def pending_count(self) -> int:
        return sum(1 for t in self.tasks if not t["done"])


def log_action(func):
    @wraps(func)
    def wrapper(*args, **kwargs):
        print(f"  → {func.__name__}")
        return func(*args, **kwargs)
    return wrapper


@log_action
def run_demo():
    service = TaskService()
    service.add("یادگیری comprehension")
    service.add("تمرین decorator")
    service.complete(1)

    print(f"کارهای باز: {service.pending_count()}")
    pending_titles = [t["title"] for t in service.tasks if not t["done"]]
    print("باقی‌مانده:", pending_titles)

    # ذخیره JSON در حافظه (بدون فایل)
    data = json.dumps(service.tasks, ensure_ascii=False, indent=2)
    print("\nJSON:")
    print(data)


print("=== Task Manager Demo ===")
run_demo()
