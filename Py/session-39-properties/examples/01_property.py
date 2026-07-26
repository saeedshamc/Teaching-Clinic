# ============================================================
# جلسه ۳۹ — property
# فایل: 01_property.py
# ============================================================

class User:
    def __init__(self, age: int):
        self.age = age

    @property
    def age(self) -> int:
        return self._age

    @age.setter
    def age(self, value: int) -> None:
        if value < 0:
            raise ValueError("سن منفی مجاز نیست")
        self._age = value

user = User(30)
print("سن:", user.age)
user.age = 31
print("سن جدید:", user.age)
