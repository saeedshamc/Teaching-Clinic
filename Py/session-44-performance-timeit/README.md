# جلسه ۴۴: عملکرد و timeit

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- زمان اجرای کد را اندازه بگیرید
- دو پیاده‌سازی را مقایسه کنید
- بهینه‌سازی را بر اساس عدد انجام دهید

---

## timeit چیست؟

timeit ابزار اندازه‌گیری زمان اجرای تکه‌کد است تا حدس نزنید؛ اندازه بگیرید.

```python
import timeit

list_time = timeit.timeit("sum([i*i for i in range(1000)])", number=1000)
gen_time = timeit.timeit("sum(i*i for i in range(1000))", number=1000)
print(list_time)
print(gen_time)
```

### توضیح
- `number` → تعداد تکرار آزمایش
- نتیجه کوچک‌تر معمولاً سریع‌تر است
- اول درست بنویسید، بعد بهینه کنید

---

## فایل‌های این جلسه
- [examples/01_timeit.py](./examples/01_timeit.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک عبارت را با timeit اندازه بگیرید
- تمرین متوسط: += و join را مقایسه کنید
