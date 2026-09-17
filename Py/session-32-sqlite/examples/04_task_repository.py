# ============================================================
# جلسه ۳۲ — SQLite: Task Repository
# ============================================================

import sqlite3
from dataclasses import dataclass
from pathlib import Path


@dataclass
class Task:
    id: int | None
    title: str
    done: bool = False


class TaskRepository:
    def __init__(self, db_path: Path | str = ":memory:"):
        self.conn = sqlite3.connect(db_path)
        self.conn.row_factory = sqlite3.Row
        self._init_schema()

    def _init_schema(self) -> None:
        self.conn.execute("""
            CREATE TABLE IF NOT EXISTS tasks (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                done INTEGER DEFAULT 0
            )
        """)
        self.conn.commit()

    def add(self, title: str) -> Task:
        cur = self.conn.execute("INSERT INTO tasks (title) VALUES (?)", (title,))
        self.conn.commit()
        return Task(cur.lastrowid, title, False)

    def list_all(self) -> list[Task]:
        rows = self.conn.execute("SELECT * FROM tasks ORDER BY id").fetchall()
        return [Task(r["id"], r["title"], bool(r["done"])) for r in rows]

    def mark_done(self, task_id: int) -> bool:
        cur = self.conn.execute(
            "UPDATE tasks SET done = 1 WHERE id = ?", (task_id,)
        )
        self.conn.commit()
        return cur.rowcount == 1

    def close(self) -> None:
        self.conn.close()


def main():
    repo = TaskRepository()
    repo.add("یادگیری SQLite")
    repo.add("تمرین CRUD")
    t3 = repo.add("پروژه نهایی")
    repo.mark_done(t3.id)

    for t in repo.list_all():
        status = "✓" if t.done else " "
        print(f"[{status}] {t.id}. {t.title}")

    repo.close()


if __name__ == "__main__":
    main()
