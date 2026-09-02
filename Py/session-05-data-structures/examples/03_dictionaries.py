# ============================================================
# جلسه ۵ — دیکشنری
# فایل: 03_dictionaries.py
# ============================================================

book = {
    "title": "یادگیری Python",
    "author": "سعید",
    "year": 2026,
}

print(book["title"])
print(book.get("isbn", "ثبت نشده"))

book["pages"] = 320

print("--- همه فیلدها ---")
for key, value in book.items():
    print(f"  {key}: {value}")

# شمارش با dict
word = "python"
freq = {}
for ch in word:
    freq[ch] = freq.get(ch, 0) + 1
print("فراوانی:", freq)
