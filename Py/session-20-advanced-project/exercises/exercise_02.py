# ============================================================
# تمرین ۴–۵ جلسه ۲۰ — Task Manager (خانه)
# ============================================================

import json
import unittest
from pathlib import Path


class TaskService:
    def __init__(self):
        self.tasks: list[dict] = []
        self._next_id = 1

    def add(self, title: str) -> dict:
        task = {"id": self._next_id, "title": title, "done": False}
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

    def save(self, path: Path) -> None:
        with path.open("w", encoding="utf-8") as f:
            json.dump(self.tasks, f, ensure_ascii=False)

    def load(self, path: Path) -> None:
        if path.exists():
            with path.open(encoding="utf-8") as f:
                self.tasks = json.load(f)
                self._next_id = max((t["id"] for t in self.tasks), default=0) + 1


class TestTaskService(unittest.TestCase):
    def setUp(self):
        self.svc = TaskService()

    def test_add_and_pending(self):
        self.svc.add("کار ۱")
        self.svc.add("کار ۲")
        self.assertEqual(self.svc.pending_count(), 2)

    def test_complete(self):
        t = self.svc.add("کار")
        self.svc.complete(t["id"])
        self.assertEqual(self.svc.pending_count(), 0)


# --- demo JSON ---
svc = TaskService()
svc.add("تست JSON")
path = Path(__file__).parent / "tasks_test.json"
svc.save(path)
svc2 = TaskService()
svc2.load(path)
print("بارگذاری:", svc2.tasks)
path.unlink(missing_ok=True)

if __name__ == "__main__":
    unittest.main(verbosity=2)
