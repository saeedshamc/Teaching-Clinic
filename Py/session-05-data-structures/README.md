# جلسه ۵: ساختارهای داده (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- لیست (`list`) — ساخت، ایندکس، برش، متدهای `append`/`pop`
- تاپل (`tuple`) — تغییرناپذیر، unpacking
- دیکشنری (`dict`) — کلید-مقدار، `get`, `keys`, `values`
- مجموعه (`set`) — یکتا بودن، عملیات مجموعه‌ای
- ساختار مناسب را برای هر سناریو انتخاب کنید
- روی ساختارها iterate کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور حلقه و iterate |
| 10–30 | لیست — CRUD ساده |
| 30–45 | تاپل و unpacking |
| 45–60 | دیکشنری |
| 60–75 | set و کاربرد یکتا |
| 75–85 | انتخاب ساختار + تمرین |
| 85–90 | تکلیف |

---

## ۱. لیست (List)

```python
fruits = ["سیب", "موز", "پرتقال"]
print(fruits[0])    # سیب — ایندکس از ۰
print(fruits[-1])   # پرتقال — آخرین

fruits.append("انگور")
fruits[1] = "کیوی"
removed = fruits.pop()
print(len(fruits))
```

### ۱.۱. برش (Slicing)

```python
nums = [0, 1, 2, 3, 4, 5]
print(nums[1:4])   # [1, 2, 3]
print(nums[:3])    # [0, 1, 2]
print(nums[::2])   # هر دو تا یکی
```

### ۱.۲. حلقه روی لیست

```python
for fruit in fruits:
    print(fruit)

for i, fruit in enumerate(fruits):
    print(i, fruit)
```

---

## ۲. تاپل (Tuple)

```python
point = (10, 20)
rgb = (255, 128, 0)
single = (42,)  # کاما برای تاپل تک‌عضو

x, y = point  # unpacking
print(x, y)
```

- **تغییرناپذیر** — بعد از ساخت edit نمی‌شود
- برای مختصات، بازگشت چند مقدار از تابع

---

## ۳. دیکشنری (Dictionary)

```python
person = {"name": "سعید", "age": 25, "city": "تهران"}
print(person["name"])
print(person.get("email", "ندارد"))

person["email"] = "s@example.com"
for key, value in person.items():
    print(f"{key}: {value}")
```

| متد | کاربرد |
|-----|--------|
| `keys()` | کلیدها |
| `values()` | مقادیر |
| `items()` | جفت (key, value) |
| `get(k, default)` | بدون KeyError |

---

## ۴. مجموعه (Set)

```python
numbers = {1, 2, 2, 3, 3}
print(numbers)  # {1, 2, 3} — تکراری حذف

a = {1, 2, 3}
b = {3, 4, 5}
print(a | b)  # اجتماع
print(a & b)  # اشتراک
```

---

## ۵. انتخاب ساختار مناسب

| نیاز | ساختار |
|------|--------|
| ترتیب + تغییر | list |
| ثابت / مختصات | tuple |
| جستجو با کلید | dict |
| یکتا بودن | set |

---

## ۶. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_lists.py](./examples/01_lists.py) | لیست |
| [02_tuples.py](./examples/02_tuples.py) | تاپل |
| [03_dictionaries.py](./examples/03_dictionaries.py) | دیکشنری |
| [04_sets_and_nested.py](./examples/04_sets_and_nested.py) | set و nested |

---

## ۷. اشتباهات رایج

| اشتباه | راه‌حل |
|--------|--------|
| `list[10]` خارج از بازه | `IndexError` — `len` چک کنید |
| `dict["missing"]` | از `get` استفاده کنید |
| `set` مرتب نیست | برای ترتیب list |
| لیست mutable به‌عنوان کلید dict | کلید باید hashable باشد |

---

## ۸. تمرین کلاسی

1. لیست اسم — append و چاپ
2. دیکشنری کتاب — title, author, year
3. حذف تکراری از لیست با set
4. nested: لیست دیکشنری دانشجو

**تکلیف:** [question.md](./exercises/question.md)

### ۵.۱. list comprehension (پیش‌نمایش)

```python
squares = [x ** 2 for x in range(10)]
evens = [x for x in nums if x % 2 == 0]
```

جلسه ۱۳ comprehensions را کامل پوشش می‌دهد.

### ۵.۲. copy vs reference

```python
a = [1, 2, 3]
b = a          # همان لیست — reference
c = a.copy()   # کپی مستقل
b.append(4)
print(a)  # [1,2,3,4] — c تغییر نمی‌کند
```

---

## ۱۰. تکلیف خانه

1. **دفترچه تلفن** dict کامل با add/search
2. **invert dict** — value→keys
3. **word frequency** از یک پارagraph

---

## ۱۲. توضیح خط‌به‌خط مثال‌ها

### 📄 `01_lists.py`

```python
fruits = ["سیب", "موز", "پرتقال"]
fruits.append("انanas")
fruits.remove("موز")
```

- لیست **ordered** و **mutable**
- `append` انتها — `insert(i, x)` وسط
- `remove(value)` — اولین match

### 📄 `03_dictionaries.py`

- `freq[ch] = freq.get(ch, 0) + 1` — الگوی شمارش
- `items()` برای iterate هم‌زمان key/value

### 📄 `04_sets_and_nested.py`

- `set` تکرار را حذف می‌کند — ترتیب حفظ نمی‌شود (تا 3.7+ insertion order در set هم هست ولی برای sort به list تبدیل کنید)

---

## ۱۴. مقایسه ساختارها (جدول کامل)

| عملیات | list | tuple | dict | set |
|--------|------|-------|------|-----|
| Ordered | ✅ | ✅ | ✅ (3.7+) | ❌* |
| Mutable | ✅ | ❌ | ✅ | ✅ |
| Index `[i]` | ✅ | ✅ | ❌ | ❌ |
| Key access | ❌ | ❌ | ✅ | ❌ |
| Duplicate | ✅ | ✅ | keys: ❌ | ❌ |

*set از Python 3.7 insertion order دارد ولی برای index از list استفاده کنید.

---

## ۱۵. مطالعه بیشتر

- [docs — dict](https://docs.python.org/3/library/stdtypes.html#dict)
- Counter و defaultdict — جلسه ۳۰
- تمرین: phone book با dict of dict

---

## ۱۷. تکلیف خانه (جزئیات)

**تمرین A — invert dict:** دیکشنری `{ "a": 1, "b": 2 }` را معکوس کنید. اگر value تکراری بود، لیست کلیدها بسازید.

**تمرین B — merge dicts:** دو dict را بدون overwrite کلیدهای مشترک ادغام کنید (Python 3.9+: `d1 | d2`).

**تمرین C — sort by value:** dict فراوانی کلمات را بر اساس value نزولی sort کنید.

```python
# راهنما
items = sorted(freq.items(), key=lambda x: x[1], reverse=True)
```

---

## ۱۹. پرسش‌های کلاسی (Q&A)

**س: تفاوت list و tuple؟**  
ج: list mutable — tuple immutable. tuple برای داده ثابت مثل مختصات.

**س: آیا dict مرتب است؟**  
ج: از Python 3.7+ insertion order حفظ می‌شود.

**س: کی set استفاده کنیم؟**  
ج: یکتا بودن، حذف تکرار، عملیات مجموعه.

**س: copy shallow vs deep؟**  
ج: `.copy()` سطح اول — nested lists نیاز به `copy.deepcopy` دارند.

---

## ۲۰. خلاصه

- ✅ list: `[ ]` — mutable, ordered
- ✅ tuple: `( )` — immutable
- ✅ dict: `{k: v}` — key-value
- ✅ set: `{ }` — unique

---

**جلسه قبل:** [۴ — حلقه‌ها](../session-04-loops/) | **بعد:** [۶ — توابع](../session-06-functions/)
