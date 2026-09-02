# ============================================================
# تمرین ۱ جلسه ۴۸ — docstring و class
# ============================================================


def subtract(a: int, b: int) -> int:
    """a منهای b.

    Args:
        a: عدد اول
        b: عدد دوم

    Returns:
        تفاضل a و b
    """
    return a - b


class Book:
    """کتاب با عنوان و نویسنده.

    Attributes:
        title: عنوان
        author: نویسنده
    """

    def __init__(self, title: str, author: str):
        self.title = title
        self.author = author

    def summary(self) -> str:
        """خلاصه یک خطی کتاب."""
        return f"«{self.title}» — {self.author}"


if __name__ == "__main__":
    print(subtract(10, 3))
    print(Book("کلین کد", "Robert Martin").summary())
