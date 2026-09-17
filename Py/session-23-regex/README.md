# جلسه ۲۳: عبارات منظم — Regex (۱.۵ ساعت)

## ۰. مرور

جلسه ۷ string methods (`find`, `split`) — regex وقتی **الگو** پیچیده‌تر از equality ساده است.

---

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- مفهوم Regular Expression را توضیح دهید
- با ماژول `re` کار کنید: `search`، `match`، `findall`، `sub`
- meta-characterهای رایج را بشناسید: `.` `\d` `\w` `\s` `+` `*` `?`
- گروه‌بندی `(...)` و `group()` را استفاده کنید
- raw string `r"..."` را برای الگو به‌کار ببرید
- اعتبارسنجی اولیه ایمیل، موبایل و عدد انجام دهید
- `re.compile` برای الگوهای تکراری بدانید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | جستجوی متنی ساده vs الگو |
| 10–25 | re.search و match | اولین تطبیق |
| 25–40 | findall و finditer | همه تطبیق‌ها |
| 40–55 | meta-characters | \d \w + * ? |
| 55–70 | groups و sub | استخراج و جایگزینی |
| 70–85 | تمرین | ایمیل، موبایل |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. Regex چیست؟

**Regular Expression** الگویی برای جستجو، تطبیق و جایگزینی در متن است.

**تشبیه:** مثل «فیلتر عکس» — به‌جای جستجوی دقیق یک رشته، **الگو** می‌دهید: «هر چیزی شبیه شماره موبایل».

```python
import re

text = "ایمیل: saeed@example.com"
match = re.search(r"[\w.-]+@[\w.-]+", text)
if match:
    print(match.group())  # saeed@example.com
```

---

## ۲. Raw String — چرا r"..."?

```python
# ❌ \d در string معمولی escape می‌شود
pattern = "\d+"  # ممکن است مشکل‌ساز

# ✅ raw string — backslash literal
pattern = r"\d+"
```

---

## ۳. re.search vs re.match

| تابع | جستجو |
|------|-------|
| `re.search` | هر جای رشته |
| `re.match` | فقط **ابتدای** رشته |

```python
re.match(r"\d+", "abc123")   # None
re.search(r"\d+", "abc123")  # Match '123'
```

---

## ۴. findall و finditer

```python
text = "سال 1403 و نسخه 3.12 و 42"
numbers = re.findall(r"\d+", text)
print(numbers)  # ['1403', '3', '12', '42']

for m in re.finditer(r"\d+", text):
    print(m.group(), "در موقعیت", m.start())
```

---

## ۵. Meta-characters پرکاربرد

| الگو | معنی |
|------|------|
| `.` | هر کاراکتر (جز newline) |
| `\d` | رقم |
| `\D` | غیررقم |
| `\w` | word character |
| `\s` | whitespace |
| `+` | یک یا بیشتر |
| `*` | صفر یا بیشتر |
| `?` | صفر یا یک |
| `{n,m}` | بین n تا m |
| `^` | ابتدای رشته |
| `$` | انتهای رشته |
| `[abc]` | یکی از a,b,c |
| `[^abc]` | هیچ‌کدام از a,b,c |

---

## ۶. Groups — استخراج بخش‌ها

```python
text = "علی 25 ساله"
match = re.search(r"(\w+)\s+(\d+)", text)
if match:
    print(match.group(1))  # علی
    print(match.group(2))  # 25
    print(match.groups())  # ('علی', '25')
```

---

## ۷. re.sub — جایگزینی

```python
text = "سلام   دنیا  !"
clean = re.sub(r"\s+", " ", text)
print(clean)  # سلام دنیا !

# backreference
masked = re.sub(r"(\d{4})(\d{3})(\d{4})", r"\1-***-\3", "09121234567")
print(masked)
```

---

## ۸. re.compile — الگوی از پیش کامپایل

```python
phone_pattern = re.compile(r"09\d{9}")
phones = phone_pattern.findall("09121234567 و 09351234567")
```

برای الگوی تکراری سریع‌تر است.

---

## ۹. مثال‌های اعتبارسنجی

```python
EMAIL = re.compile(r"^[\w.-]+@[\w.-]+\.\w+$")
MOBILE_IR = re.compile(r"^09\d{9}$")

def is_valid_email(s): return bool(EMAIL.match(s))
def is_valid_mobile(s): return bool(MOBILE_IR.match(s))
```

---

## ۱۰. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_regex.py](./examples/01_regex.py) | search، findall، sub |
| [02_groups_compile.py](./examples/02_groups_compile.py) | groups، compile |
| [03_validation.py](./examples/03_validation.py) | اعتبارسنجی |
| [04_text_processing.py](./examples/04_text_processing.py) | پردازش متن |

---

## ۱۱. اشتباهات رایج (Pitfalls)

### ❌ فراموش کردن raw string
### ❌ greedy vs non-greedy
```python
re.search(r"<.+>", "<a><b>")   # greedy: '<a><b>'
re.search(r"<.+?>", "<a><b>")  # non-greedy: '<a>'
```

### ❌ over-engineering regex
گاهی `str.split` یا `in` ساده‌تر است.

---

## ۱۲. خلاصه

- ✅ `re.search` / `findall` / `sub`
- ✅ raw string `r"..."`
- ✅ groups برای استخراج
- ✅ compile برای تکرار

---

## ۱۳. تکلیف

راهنما: [exercises/question.md](./exercises/question.md)  
پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۴. flags پرکاربرد

| flag | کاربرد |
|------|--------|
| `re.IGNORECASE` / `re.I` | بدون حساسیت حروف |
| `re.MULTILINE` / `re.M` | ^ و $ هر خط |
| `re.DOTALL` / `re.S` | . شامل newline |

```python
re.findall(r"^\d+", text, re.MULTILINE)
```

---

## ۱۵. re.escape — literal string

```python
keyword = "C++"
pattern = re.escape(keyword)  # C\+\+
```

---

## ۱۶. توضیح فایل‌های مثال

### 📄 `01_regex.py` — search، findall، sub
### 📄 `02_groups_compile.py` — named groups
### 📄 `03_validation.py` — ایمیل و موبایل
### 📄 `04_text_processing.py` — پارس لاگ

---

## ۱۷. سوالات کلاسی

1. search vs match؟
2. greedy vs non-greedy (`+?`)؟
3. چه زمانی regex overkill است؟

---

## ۱۸. ابزار آنلاین

- [regex101.com](https://regex101.com) — تست و debug
- Python flavor را انتخاب کنید

---

## راهنمای مدرس (۱۵ دقیقه)

- قبل از شروع: مرور کوتاه جلسه قبل و اهداف روی تخته
- حین جلسه: دانشجو کد را خودش تایپ کند — copy/paste نکند
- بعد از تمرین: ۵ دقیقه Q&A و اشاره به exercises/question.md
- تکلیف خانه: exercise_02.py — deadline هفته بعد
- ارزیابی: حضور در تمرین کلاسی + تحویل exercise_02

---

## منابع تکمیلی

- [re — Python docs](https://docs.python.org/3/library/re.html)
- [regex101.com](https://regex101.com) — تست interactives

### چک‌لیست پایان جلسه

- [ ] search و findall
- [ ] groups
- [ ] sub
- [ ] compile

---

## نکات تکمیلی برای مدرس

regex101.com را live demo کنید. تأکید: وقتی `in` یا `split` کافی است regex نزنید. raw string `r""` را همیشه یادآوری کنید.

### اجرای تمرین‌ها

```bash
python session-23-regex/examples/03_validation.py
python session-23-regex/exercises/exercise_02.py
```

برای HTML/XML parsing از regex پرهیز کنید — جلسه ۳۶ html.parser مناسب‌تر است.

---

**جلسه بعدی:** [جلسه ۲۴ — JSON](../session-24-json/)
