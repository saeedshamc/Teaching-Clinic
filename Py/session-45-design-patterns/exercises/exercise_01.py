# ============================================================
# تمرین ۱ جلسه ۴۵ — Singleton Config + Factory
# ============================================================


class Config:
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
            cls._instance._data = {"debug": "true", "lang": "fa"}
        return cls._instance

    def get(self, key: str) -> str:
        return self._data.get(key, "")


def notifier_factory(kind: str):
    if kind == "email":
        return lambda msg: print(f"Email: {msg}")
    if kind == "sms":
        return lambda msg: print(f"SMS: {msg}")
    raise ValueError(kind)


if __name__ == "__main__":
    c1 = Config()
    c2 = Config()
    print(c1 is c2, c1.get("lang"))
    notify = notifier_factory("email")
    notify("سلام")
