# جلسه ۴: حلقه‌ها و کنترل جریان

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با `for` و `while` آشنا شوید
- از `break` و `continue` برای کنترل حلقه استفاده کنید
- تکرار کارها را به‌صورت خودکار انجام دهید

---

## حلقه چیست؟

حلقه به شما اجازه می‌دهد یک بخش از کد را چند بار اجرا کنید بدون اینکه آن را چند بار بنویسید.

```python
for i in range(3):
    print(i)
```

### توضیح
- `for` یک حلقه‌ی تکرار است
- `range(3)` سه بار تکرار می‌کند

---

## حلقه `for`

```python
for number in range(1, 6):
    print(number)
```

---

## حلقه `while`

```python
count = 0
while count < 3:
    print("تکرار", count)
    count += 1
```

---

## `break` و `continue`

```python
for i in range(1, 6):
    if i == 3:
        break
    print(i)
```

```python
for i in range(1, 6):
    if i == 3:
        continue
    print(i)
```

---

## خلاصه جلسه

در این جلسه یاد گرفتید:
- حلقه `for` و `while`
- استفاده از `break` و `continue`

---

## فایل‌های این جلسه
- [examples/01_for_loop.py](./examples/01_for_loop.py)
- [examples/02_while_loop.py](./examples/02_while_loop.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها

### تمرین ساده
برنامه‌ای بنویسید که اعداد ۱ تا ۱۰ را چاپ کند.

### تمرین متوسط
برنامه‌ای بنویسید که جمع اعداد ۱ تا ۱۰۰ را حساب کند.

---

[جلسه بعدی: ساختارهای داده](../session-05-data-structures/)
