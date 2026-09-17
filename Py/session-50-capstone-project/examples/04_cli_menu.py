# ============================================================
# جلسه ۵۰ — CLI menu
# فایل: 04_cli_menu.py
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
        if not title.strip():
            raise ValueError("عنوان خالی")
        self.tasks.append(Task(title=title.strip()))

    def complete(self, index: int) -> None:
        self.tasks[index].done = True

    def list_tasks(self) -> None:
        for i, t in enumerate(self.tasks):
            mark = "✅" if t.done else "⏳"
            print(f"  [{i}] {mark} {t.title}")

    def save(self) -> None:
        self.path.write_text(
            json.dumps([asdict(t) for t in self.tasks], ensure_ascii=False, indent=2),
            encoding="utf-8",
        )

    def load(self) -> None:
        if self.path.exists():
            raw = json.loads(self.path.read_text(encoding="utf-8"))
            self.tasks = [Task(**x) for x in raw]


def run_menu(service: TaskService) -> None:
    actions = {
        "1": lambda: service.add(input("عنوان: ")),
        "2": service.list_tasks,
        "3": lambda: service.complete(int(input("اندیس: "))),
        "4": service.save,
    }
    print("\n=== Task Service ===")
    print("1.add  2.list  3.done  4.save  q.quit")
    while True:
        choice = input("> ").strip().lower()
        if choice == "q":
            service.save()
            break
        if choice in actions:
            try:
                actions[choice]()
            except (ValueError, IndexError) as e:
                print("خطا:", e)
        else:
            print("دستور نامعتبر")


if __name__ == "__main__":
    path = Path(__file__).with_name("tasks_demo.json")
    svc = TaskService(path)
    svc.load()
    svc.add("نمونه capstone")
    svc.list_tasks()
    svc.save()
    path.unlink(missing_ok=True)
    print("\n💡 برای menu تعاملی: run_menu(svc) را uncomment کنید")
