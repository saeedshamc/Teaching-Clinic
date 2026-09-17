# ============================================================
# جلسه ۱۲ — یادداشت در حافظه
# فایل: 01_notes_memory.py
# ============================================================

notes = []


def add_note(text):
    notes.append(text.strip())
    print("✓ یادداشت اضافه شد.")


def show_notes():
    if not notes:
        print("(خالی)")
        return
    for i, note in enumerate(notes, 1):
        print(f"  {i}. {note}")


# دمو بدون input تعاملی
add_note("یادگیری Python")
add_note("تمرین حلقه و list")
show_notes()
print("تعداد:", len(notes))
