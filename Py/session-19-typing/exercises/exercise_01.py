# ============================================================
# تمرین ۱–۳ جلسه ۱۹ — Type Hints (کلاسی)
# ============================================================


def add(a: int, b: int) -> int:
    return a + b


def total_length(words: list[str]) -> int:
    return sum(len(w) for w in words)


def find(items: list[int], target: int) -> int | None:
    for i, item in enumerate(items):
        if item == target:
            return i
    return None


print("جمع:", add(3, 5))
print("طول کل:", total_length(["سلام", "پایتون"]))
print("index 5:", find([1, 3, 5, 7], 5))
print("index 9:", find([1, 3, 5], 9))
