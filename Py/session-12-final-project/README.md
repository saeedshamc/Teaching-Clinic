# جلسه ۱۲: پروژه نهایی — دفترچه یادداشت CLI (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- مفاهیم جلسات ۱–۱۱ را در یک پروژه یکپارچه به کار ببرید
- برنامه CLI با منوی حلقه‌ای (`while True`) بسازید
- داده را در حافظه و فایل (JSON/متن) persist کنید
- کد را به توابع و ماژول‌های کوچک تقسیم کنید
- خطا را با try/except handle کنید
- پروژه را تست و مستند کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور اهداف و معماری |
| 10–25 | طراحی منو و ساختار داده |
| 25–45 | پیاده‌سازی CRUD در حافظه |
| 45–60 | ذخیره/بارگذاری فایل |
| 60–75 | refactor به توابع |
| 75–85 | تست دستی و edge case |
| 85–90 | ارائه و تکلیف گسترش |

---

## ۱. شرح پروژه

**دفترچه یادداشت ساده (Notes CLI)**

| گزینه | عمل |
|-------|-----|
| ۱ | افزودن یادداشت |
| ۲ | نمایش همه |
| ۳ | جستجو |
| ۴ | ذخیره در فایل |
| ۵ | بارگذاری از فایل |
| ۰ | خروج |

---

## ۲. ساختار پیشنهادی

```
session-12-final-project/
├── README.md
├── examples/
│   ├── 01_notes_memory.py
│   ├── 02_notes_file.py
│   ├── 03_notes_menu.py
│   └── 04_notes_complete.py
└── exercises/
```

---

## ۳. ساختار داده

```python
notes = []  # list of str

# یا با timestamp:
notes = [
    {"text": "یادداشت اول", "created": "2026-09-02"},
]
```

---

## ۴. منوی اصلی

```python
def main():
    notes = load_notes()  # یا []
    while True:
        print("\n--- دفترچه یادداشت ---")
        print("1) افزودن  2) نمایش  3) جستجو  0) خروج")
        choice = input("انتخاب: ").strip()
        if choice == "1":
            add_note(notes)
        elif choice == "2":
            show_notes(notes)
        elif choice == "0":
            save_notes(notes)
            break
```

---

## ۵. ذخیره در فایل

```python
import json

def save_notes(notes, path="notes.json"):
    with open(path, "w", encoding="utf-8") as f:
        json.dump(notes, f, ensure_ascii=False, indent=2)

def load_notes(path="notes.json"):
    try:
        with open(path, "r", encoding="utf-8") as f:
            return json.load(f)
    except FileNotFoundError:
        return []
```

---

## ۶. مفاهیم به کار رفته

| جلسه | مفهوم |
|------|--------|
| ۱–۲ | print, input, variables |
| ۳–۴ | if, while |
| ۵ | list, dict |
| ۶ | functions |
| ۷ | file I/O |
| ۸ | try/except |
| ۹ | class (اختیاری Note) |
| ۱۰ | json module |
| ۱۱ | pip (اختیاری rich) |

---

## ۷. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_notes_memory.py](./examples/01_notes_memory.py) | CRUD حافظه |
| [02_notes_file.py](./examples/02_notes_file.py) | persist فایل |
| [03_notes_menu.py](./examples/03_notes_menu.py) | منوی کامل |
| [04_notes_complete.py](./examples/04_notes_complete.py) | نسخه نهایی + class |

---

## ۸. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| ذخیره نکردن قبل exit | save در خروج |
| input بدون strip | `.strip()` |
| فایل بدون utf-8 | encoding |
| همه در main | refactor |

---

## ۹. تمرین کلاسی

1. افزودن + نمایش
2. شمارش یادداشت‌ها
3. ذخیره JSON
4. جستجو در متن

**تکلیف:** [question.md](./exercises/question.md)

### ۱۰.۱. چک‌لیست تحویل پروژه

- [ ] منو کار می‌کند
- [ ] utf-8 در فایل
- [ ] try/except برای I/O
- [ ] README کوتاه برای اجرا
- [ ] کد refactor شده (توابع < 20 خط)

### ۱۰.۲. ایده‌های گسترش

- export به TXT
- دسته‌بندی یادداشت‌ها
- رمز عبور ساده
- CLI با argparse (جلسه ۳۱)

---

## ۱۱. تکلیف خانه

1. **CRUD کامل** با فایل
2. **unit test** ساده برای add/search
3. **demo video** یا screenshot منو

---

## ۱۳. توضیح خط‌به‌خط مثال‌ها

### 📄 `01_notes_memory.py`

- `notes = []` — state در حافظه
- با خروج برنامه از بین می‌رود

### 📄 `02_notes_file.py`

```python
json.dump(notes, f, ensure_ascii=False, indent=2)
```

- `ensure_ascii=False` — ذخیره فارسی
- `indent=2` — فایل readable

### 📄 `04_notes_complete.py`

- `Note.to_dict()` / `from_dict` — serialization
- جداسازی model (Note) و app (NotesApp)

---

## ۱۵. rubric ارزیابی پروژه

| سطح | معیار |
|------|--------|
| A | CRUD + file + class + error handling |
| B | CRUD + file + functions |
| C | menu + list in memory |
| D | incomplete menu |

---

## ۱۶. مطالعه بیشتر

- [Real Python — CLI apps](https://realpython.com/)
- json module docs
- گام بعد: argparse (جلسه ۳۱)، sqlite (جلسه ۳۲)

---

## ۱۸. سناریوی ارائه (Presentation)

1. دمو افزودن یادداشت (۳۰ ثانیه)
2. نمایش لیست + شمارش
3. ذخیره و reload — persistence
4. جستجو — keyword
5. Q&A — چرا JSON؟ چرا class؟

**زمان:** ۵ دقیقه per team

---

## ۲۰. پرسش‌های کلاسی (Q&A)

**س: چرا JSON نه pickle؟**  
ج: JSON portable و human-readable — pickle فقط Python.

**س: state در memory vs file؟**  
ج: memory سریع — file persistent.

**س: while True bad practice؟**  
ج: برای CLI menu با break خروج — OK.

---

## ۲۱. خلاصه دوره

تبریک! جلسات ۱–۱۲ را تمام کردید:
- ✅ مبانی Python
- ✅ ساختار داده و توابع
- ✅ فایل و خطا
- ✅ OOP و ماژule
- ✅ پروژه CLI واقعی

**ادامه:** [جلسه ۱۳ — Comprehensions](../session-13-comprehensions/)

---

**جلسه قبل:** [۱۱ — pip](../session-11-external-libs/) | **بعد:** [۱۳ — Comprehensions](../session-13-comprehensions/)
