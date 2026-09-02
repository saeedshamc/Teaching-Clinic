# ============================================================
# جلسه ۴۶ — structural typing
# فایل: 02_structural_typing.py
# ============================================================

from typing import Protocol, runtime_checkable


@runtime_checkable
class Drawable(Protocol):
    def draw(self) -> str: ...


class Circle:
    def draw(self) -> str:
        return "○ دایره"


class Square:
    def draw(self) -> str:
        return "□ مربع"


def render(shape: Drawable) -> None:
    print(shape.draw())


shapes: list[Drawable] = [Circle(), Square()]
for s in shapes:
    render(s)
    print("  isinstance Drawable?", isinstance(s, Drawable))
