# ============================================================
# جلسه ۱۲ — منوی CLI (دمو با انتخاب از پیش تعریف‌شده)
# فایل: 03_notes_menu.py
# ============================================================

notes = []


def add_note(notes, text):
    notes.append(text)


def show_notes(notes):
    for i, n in enumerate(notes, 1):
        print(f"  {i}. {n}")


def search_notes(notes, keyword):
    return [n for n in notes if keyword.lower() in n.lower()]


def run_demo(choices):
    """choices: لیست رشته‌های '1:text', '2', '3:kw'"""
    for cmd in choices:
        parts = cmd.split(":", 1)
        op = parts[0]
        if op == "1" and len(parts) > 1:
            add_note(notes, parts[1])
        elif op == "2":
            show_notes(notes)
        elif op == "3" and len(parts) > 1:
            hits = search_notes(notes, parts[1])
            print("جستجو:", hits)


run_demo(["1:Python", "1:Java", "2", "3:py"])
print("تعداد کل:", len(notes))
