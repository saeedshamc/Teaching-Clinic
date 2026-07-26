# ============================================================
# تمرین جلسه ۲۰
# هدف: تعداد کارهای انجام‌نشده را برگردانید.
# ============================================================

tasks = [
    {"title": "یادگیری Python", "done": True},
    {"title": "نوشتن تمرین", "done": False},
    {"title": "مرور کد", "done": False},
]


def count_pending(items: list[dict]) -> int:
    count = 0
    for task in items:
        if not task["done"]:
            count += 1
    return count


print("تعداد کارهای مانده:", count_pending(tasks))
