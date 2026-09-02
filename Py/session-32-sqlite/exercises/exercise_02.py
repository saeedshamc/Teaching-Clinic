# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۲ (پیشرفته)
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
    def __init__(self, db_path: Path):
        self.conn = sqlite3.connect(db_path)
        self.conn.row_factory = sqlite3.Row
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
        return Task(cur.lastrowid, title)

    def delete(self, task_id: int) -> bool:
        cur = self.conn.execute("DELETE FROM tasks WHERE id = ?", (task_id,))
        self.conn.commit()
        return cur.rowcount == 1

    def list_all(self) -> list[Task]:
        return [
            Task(r["id"], r["title"], bool(r["done"]))
            for r in self.conn.execute("SELECT * FROM tasks")
        ]

    def close(self):
        self.conn.close()


db = Path(__file__).parent / "tasks.db"
repo = TaskRepository(db)
repo.add("SQL تمرین")
repo.add("Repository pattern")
for t in repo.list_all():
    print(t)
repo.close()
db.unlink(missing_ok=True)
