# ============================================================
# تمرین ۲ جلسه ۴۹ — mini layered project
# ============================================================

from dataclasses import dataclass


@dataclass
class Note:
    text: str


class NoteRepo:
    def __init__(self):
        self._notes: list[Note] = []

    def save(self, note: Note) -> None:
        self._notes.append(note)

    def list_all(self) -> list[Note]:
        return list(self._notes)


class NoteService:
    def __init__(self, repo: NoteRepo):
        self.repo = repo

    def add(self, text: str) -> None:
        self.repo.save(Note(text.strip()))


class NoteCLI:
    def __init__(self, svc: NoteService):
        self.svc = svc

    def add(self, text: str) -> None:
        self.svc.add(text)
        print("یادداشت ذخیره شد")


repo = NoteRepo()
svc = NoteService(repo)
cli = NoteCLI(svc)
cli.add("ساختار پروژه را تمرین کن")
print("تعداد:", len(repo.list_all()))
