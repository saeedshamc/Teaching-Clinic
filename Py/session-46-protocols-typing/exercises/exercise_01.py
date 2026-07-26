# ============================================================
# تمرین جلسه ۴۶
# هدف: Protocol برای speak بسازید.
# ============================================================

from typing import Protocol

class Speaks(Protocol):
    def speak(self) -> str: ...

class Cat:
    def speak(self) -> str:
        return "میو"

def hear(animal: Speaks) -> None:
    print(animal.speak())

hear(Cat())
