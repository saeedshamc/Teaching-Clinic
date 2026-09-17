# ============================================================
# جلسه ۴۶ — Protocol پایه
# فایل: 01_protocol_basics.py
# ============================================================

from typing import Protocol


class Writable(Protocol):
    def write(self, data: str) -> None: ...


class MemoryFile:
    def __init__(self):
        self.data = ""

    def write(self, data: str) -> None:
        self.data += data


class FileLogger:
    def __init__(self):
        self.lines: list[str] = []

    def write(self, data: str) -> None:
        self.lines.append(data)


def save(dest: Writable, text: str) -> None:
    dest.write(text)


mem = MemoryFile()
save(mem, "سلام ")
save(mem, "دنیا")
print("MemoryFile:", mem.data)

logger = FileLogger()
save(logger, "log entry")
print("FileLogger:", logger.lines)
