# ============================================================
# جلسه ۳۹ — readonly و deleter
# فایل: 04_readonly_deleter.py
# ============================================================


class Employee:
    def __init__(self, name: str, employee_id: str):
        self.name = name
        self._employee_id = employee_id

    @property
    def employee_id(self) -> str:
        """شناسه پرسنلی — فقط خواندنی."""
        return self._employee_id


class Session:
    def __init__(self, token: str):
        self.token = token

    @property
    def token(self) -> str:
        return self._token

    @token.setter
    def token(self, value: str) -> None:
        if not value:
            raise ValueError("توکن خالی مجاز نیست")
        self._token = value

    @token.deleter
    def token(self) -> None:
        print("توکن باطل شد (logout)")
        del self._token


emp = Employee("مینا", "E-1001")
print("کارمند:", emp.name, "| شناسه:", emp.employee_id)

session = Session("abc-123")
print("توکن:", session.token)
del session.token

try:
    print(session.token)
except AttributeError:
    print("بعد از deleter، token وجود ندارد")
