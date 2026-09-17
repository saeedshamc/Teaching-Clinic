# ============================================================
# جلسه ۳۹ — property پایه
# فایل: 01_property_basics.py
# ============================================================
# @property = getter | @age.setter = نوشتن کنترل‌شده
# در __init__ از self.age استفاده کنید تا setter اجرا شود
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
        if value > 150:
            raise ValueError("سن غیرواقعی است")
        self._age = value


user = User(30)
print("سن:", user.age)
user.age = 31
print("سن جدید:", user.age)

try:
    user.age = -1
except ValueError as e:
    print("خطای مورد انتظار:", e)
