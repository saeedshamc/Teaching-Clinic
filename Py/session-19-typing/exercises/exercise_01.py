# ============================================================
# تمرین جلسه ۱۹
# هدف: تابعی با type hint بنویسید که طول کل رشته‌ها را برگرداند.
# ============================================================


def total_length(words: list[str]) -> int:
    total = 0
    for word in words:
        total += len(word)
    return total


names: list[str] = ["سعید", "مینا", "علی"]
print(total_length(names))
