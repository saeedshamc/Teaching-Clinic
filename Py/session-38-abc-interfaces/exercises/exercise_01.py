# ============================================================
# تمرین جلسه ۳۸
# هدف: کلاس Animal با متد speak بسازید.
# ============================================================

from abc import ABC, abstractmethod

class Animal(ABC):
    @abstractmethod
    def speak(self) -> str:
        pass

class Dog(Animal):
    def speak(self) -> str:
        return "واق واق"

print(Dog().speak())
