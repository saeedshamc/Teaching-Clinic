# تمرین‌های جلسه ۳۵ — HTTP Client

## کلاسی (۳۰ دقیقه)

1. **FakeResponse:** status, read(), context manager.
2. **Headers:** User-Agent و Accept در Request.
3. **POST JSON:** ارسال dict — پاسخ 201.
4. **HTTPError:** URL با 404 — catch و چاپ code.

## خانه (۴۵ دقیقه)

5. **ApiClient:** کلاس با get/post و base_url.
6. **Retry:** 3 بار تلاش با backoff.
7. **JSON parse:** پاسخ API → dict → فیلد name.
8. **Real urlopen:** (اختیاری) GET به https://example.com

## نکات

- همیشه timeout
- API key در header Authorization
- `json.dumps(..., ensure_ascii=False)` برای فارسی
- FakeResponse برای تست بدون شبکه

پاسخ نمونه: [exercise_01.py](./exercise_01.py) | [exercise_02.py](./exercise_02.py)
