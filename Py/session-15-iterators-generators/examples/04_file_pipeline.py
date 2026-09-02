# ============================================================
# جلسه ۱۵ — pipeline داده با generator
# فایل: 04_file_pipeline.py
# ============================================================
# generator برای پردازش زنجیره‌ای بدون بارگذاری همه داده در حافظه
# ============================================================

SAMPLE_DATA = [
    "علی,18,تهران",
    "مریم,22,اصفهان",
    "رضا,15,شیراز",
    "سارا,19,تهران",
    "امیر,12,مشهد",
]


def read_lines(lines):
    """شبیه‌سازی خواندن خط‌به‌خط فایل"""
    for line in lines:
        yield line.strip()


def parse_csv_lines(lines):
    """تبدیل خط CSV به dict"""
    for line in lines:
        parts = line.split(",")
        yield {"name": parts[0], "age": int(parts[1]), "city": parts[2]}


def filter_adults(records):
    """فقط بزرگسالان (سن >= 18)"""
    for record in records:
        if record["age"] >= 18:
            yield record


def filter_city(records, city):
    """فیلتر بر اساس شهر"""
    for record in records:
        if record["city"] == city:
            yield record


# --- pipeline: هر مرحله generator به بعدی می‌دهد ---
lines = read_lines(SAMPLE_DATA)
records = parse_csv_lines(lines)
adults = filter_adults(records)
tehran_adults = filter_city(adults, "تهران")

print("بزرگسالان تهران:")
for person in tehran_adults:
    print(f"  {person['name']} — {person['age']} سال")

# --- generator expression در pipeline ---
numbers = range(1, 11)
evens = (n for n in numbers if n % 2 == 0)
squares = (n * n for n in evens)
big_squares = (n for n in squares if n > 20)
print("\nمربع‌های زوج بزرگ:", list(big_squares))
