# ============================================================
# تمرین ۱–۳ جلسه ۲۰ — Task Manager (کلاسی)
# ============================================================

tasks: list[dict[str, object]] = []


def add_task(title: str) -> None:
    tasks.append({"title": title, "done": False})


def complete_task(number: int) -> None:
    if 1 <= number <= len(tasks):
        tasks[number - 1]["done"] = True


def pending_count() -> int:
    return sum(1 for t in tasks if not t["done"])


add_task("یادگیری Python")
add_task("تمرین")
add_task("مرور")
complete_task(1)

pending_titles = [t["title"] for t in tasks if not t["done"]]
print(f"کارهای باز ({pending_count()}):", pending_titles)
