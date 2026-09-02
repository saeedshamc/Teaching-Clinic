# ============================================================
# تمرین ۱ جلسه ۴۶ — Readable Protocol
# ============================================================

from typing import Protocol


class Readable(Protocol):
    def read(self) -> str: ...


class StringSource:
    def __init__(self, text: str):
        self._text = text

    def read(self) -> str:
        return self._text


class CounterSource:
    def __init__(self, n: int):
        self._n = n

    def read(self) -> str:
        return str(self._n)


def dump(source: Readable) -> None:
    print("read:", source.read())


if __name__ == "__main__":
    dump(StringSource("سلام"))
    dump(CounterSource(42))
