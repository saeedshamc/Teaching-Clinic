# ============================================================
# تمرین ۲ جلسه ۵۰ — تست service (pytest-style)
# ============================================================

from dataclasses import dataclass
from pathlib import Path
import json
import tempfile


@dataclass
class Task:
    title: str
    done: bool = False


class TaskService:
    def __init__(self):
        self.tasks: list[Task] = []

    def add(self, title: str) -> None:
        if not title.strip():
            raise ValueError("empty")
        self.tasks.append(Task(title.strip()))

    def pending_count(self) -> int:
        return sum(1 for t in self.tasks if not t.done)

    def list_done_titles(self) -> list[str]:
        return [t.title for t in self.tasks if t.done]


def test_add_and_pending():
    svc = TaskService()
    svc.add("a")
    svc.add("b")
    assert svc.pending_count() == 2


def test_empty_title_raises():
    svc = TaskService()
    try:
        svc.add("   ")
        assert False, "should raise"
    except ValueError:
        pass


def test_save_load_json():
    svc = TaskService()
    svc.add("json test")
    with tempfile.NamedTemporaryFile(suffix=".json", delete=False) as f:
        path = Path(f.name)
    data = [{"title": t.title, "done": t.done} for t in svc.tasks]
    path.write_text(json.dumps(data), encoding="utf-8")
    loaded = json.loads(path.read_text(encoding="utf-8"))
    assert loaded[0]["title"] == "json test"
    path.unlink(missing_ok=True)


if __name__ == "__main__":
    test_add_and_pending()
    test_empty_title_raises()
    test_save_load_json()
    print("✅ همه تست‌های capstone موفق")
