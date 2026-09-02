# ============================================================
# جلسه ۷ — پردازش رشته
# فایل: 02_string_processing.py
# ============================================================

csv_line = "علی,25,تهران"
parts = csv_line.split(",")
print("فیلدها:", parts)

formatted = " | ".join(parts)
print(formatted)

sentence = "Python is great and Python is fun"
print("تعداد Python:", sentence.lower().count("python"))
print("شروع با Py:", sentence.startswith("Py"))

words = sentence.split()
print(f"تعداد کلمات: {len(words)}")

# palindrome ساده
word = "radar"
print(word == word[::-1])
