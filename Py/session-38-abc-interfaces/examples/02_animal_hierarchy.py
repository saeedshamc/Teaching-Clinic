# ============================================================
# جلسه ۳۸ — ABC: Animal hierarchy
# ============================================================

from abc import ABC, abstractmethod


class Animal(ABC):
    def __init__(self, name: str):
        self.name = name

    @abstractmethod
    def speak(self) -> str:
        pass

    @abstractmethod
    def move(self) -> str:
        pass

    def intro(self) -> str:
        return f"{self.name}: {self.speak()} — {self.move()}"


class Dog(Animal):
    def speak(self) -> str:
        return "واق واق"

    def move(self) -> str:
        return "می‌دود"


class Cat(Animal):
    def speak(self) -> str:
        return "میو میو"

    def move(self) -> str:
        return "راه می‌رود"


class Fish(Animal):
    def speak(self) -> str:
        return "..."

    def move(self) -> str:
        return "شنا می‌کند"


animals: list[Animal] = [Dog("رکس"), Cat("پرش"), Fish("نemo")]
for a in animals:
    print(a.intro())
