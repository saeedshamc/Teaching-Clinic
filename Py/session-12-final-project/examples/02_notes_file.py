# ============================================================
# جلسه ۱۲ — ذخیره در فایل JSON
# فایل: 02_notes_file.py
# ============================================================
import json
import os

NOTES_FILE = os.path.join(os.path.dirname(__file__), "notes_data.json")


def save_notes(notes):
    with open(NOTES_FILE, "w", encoding="utf-8") as f:
        json.dump(notes, f, ensure_ascii=False, indent=2)
    print(f"ذخیره در {NOTES_FILE}")


def load_notes():
    try:
        with open(NOTES_FILE, "r", encoding="utf-8") as f:
            return json.load(f)
    except FileNotFoundError:
        return []


notes = ["یادداشت اول", "یادداشت دوم"]
save_notes(notes)
loaded = load_notes()
print("بارگذاری:", loaded)
