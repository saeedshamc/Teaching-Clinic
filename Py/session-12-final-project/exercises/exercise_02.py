# ============================================================
# تمرین جلسه ۱۲ — پاسخ نمونه (گسترش یافته)
# ============================================================
import json

notes = ["اول", "دوم", "Python course"]


def search(keyword):
    return [n for n in notes if keyword.lower() in n.lower()]


def delete(index):
    if 0 <= index < len(notes):
        return notes.pop(index)
    return None


print("جستجوی py:", search("py"))
removed = delete(1)
print("حذف شد:", removed)
print("باقی:", notes)

with open("notes_backup.json", "w", encoding="utf-8") as f:
    json.dump(notes, f, ensure_ascii=False)
