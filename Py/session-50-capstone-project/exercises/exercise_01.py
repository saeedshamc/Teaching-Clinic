# ============================================================
# تمرین جلسه ۵۰
# هدف: متدی بنویسید که فقط کارهای انجام‌شده را برگرداند.
# ============================================================

from dataclasses import dataclass

@dataclass
class Task:
    title: str
    done: bool = False

tasks = [
    Task("A", True),
    Task("B", False),
    Task("C", True),
]

def completed_titles(items: list[Task]) -> list[str]:
    return [task.title for task in items if task.done]

print(completed_titles(tasks))
