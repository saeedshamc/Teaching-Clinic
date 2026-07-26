# ============================================================
# جلسه ۲۰ — پروژه پیشرفته: مدیر کارهای روزانه
# فایل: 01_task_manager.py
# ============================================================

tasks: list[dict[str, object]] = []


def add_task(title: str) -> None:
    tasks.append({"title": title, "done": False})
    print("کار اضافه شد:", title)


def list_tasks() -> None:
    if not tasks:
        print("هنوز کاری وجود ندارد.")
        return

    print("فهرست کارها:")
    for index, task in enumerate(tasks, start=1):
        status = "✓" if task["done"] else " "
        print(f"{index}. [{status}] {task['title']}")


def complete_task(number: int) -> None:
    if number < 1 or number > len(tasks):
        print("شماره کار نامعتبر است.")
        return
    tasks[number - 1]["done"] = True
    print("کار انجام شد:", tasks[number - 1]["title"])


# اجرای نمونه بدون نیاز به ورودی تعاملی
add_task("یادگیری Python")
add_task("نوشتن تمرین")
list_tasks()
complete_task(1)
list_tasks()
