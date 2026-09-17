# ============================================================
# جلسه ۱۲ — نسخه کامل با class
# فایل: 04_notes_complete.py
# ============================================================
import json
import os
from datetime import datetime

NOTES_FILE = os.path.join(os.path.dirname(__file__), "notes_complete.json")


class Note:
    def __init__(self, text):
        self.text = text
        self.created = datetime.now().strftime("%Y-%m-%d %H:%M")

    def to_dict(self):
        return {"text": self.text, "created": self.created}

    @classmethod
    def from_dict(cls, d):
        n = cls(d["text"])
        n.created = d.get("created", "")
        return n


class NotesApp:
    def __init__(self):
        self.notes = []

    def add(self, text):
        self.notes.append(Note(text))

    def list_all(self):
        for i, n in enumerate(self.notes, 1):
            print(f"  {i}. [{n.created}] {n.text}")

    def save(self, path=NOTES_FILE):
        data = [n.to_dict() for n in self.notes]
        with open(path, "w", encoding="utf-8") as f:
            json.dump(data, f, ensure_ascii=False, indent=2)

    def load(self, path=NOTES_FILE):
        try:
            with open(path, "r", encoding="utf-8") as f:
                data = json.load(f)
            self.notes = [Note.from_dict(d) for d in data]
        except FileNotFoundError:
            self.notes = []


def main():
    app = NotesApp()
    app.add("پایان دوره مقدماتی Python")
    app.add("شروع Comprehensions")
    app.list_all()
    app.save()
    print(f"\nذخیره شد — {len(app.notes)} یادداشت")


if __name__ == "__main__":
    main()
