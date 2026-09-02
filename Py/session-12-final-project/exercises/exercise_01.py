# ============================================================
# تمرین جلسه ۱۲ — پاسخ نمونه (MVP)
# ============================================================

notes = []


def add_note():
    text = "یادداشت نمونه"  # input("متن: ")
    notes.append(text)


def show_notes():
    for i, n in enumerate(notes, 1):
        print(f"{i}. {n}")


def main():
    add_note()
    add_note()
    show_notes()
    print("تعداد:", len(notes))


if __name__ == "__main__":
    main()
