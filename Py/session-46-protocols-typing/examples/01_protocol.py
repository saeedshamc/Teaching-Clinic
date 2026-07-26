# ============================================================
# جلسه ۴۶ — Protocol
# فایل: 01_protocol.py
# ============================================================

from typing import Protocol

class Writable(Protocol):
    def write(self, data: str) -> None: ...

class MemoryFile:
    def __init__(self):
        self.data = ""
    def write(self, data: str) -> None:
        self.data += data

def save(dest: Writable, text: str) -> None:
    dest.write(text)

f = MemoryFile()
save(f, "سلام")
print(f.data)
