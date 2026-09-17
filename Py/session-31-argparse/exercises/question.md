# تمرین‌های جلسه ۳۱ — Argparse

## کلاسی (۳۰ دقیقه)

1. **جمع:** دو عدد positional — خروجی جمع.
2. **سلام:** `--name` / `-n` با default «مهمان».
3. **verbose:** `-v` با action=store_true.
4. **choices:** `--op` با choices add/sub/mul — دو عدد positional.

## خانه (۴۵ دقیقه)

5. **ماشین‌حساب کامل:** subparsers add, sub, mul, div.
6. **nargs=+:** چند فایل positional — تعداد کل خطوط.
7. **mutually exclusive:** --quiet vs --verbose.
8. **file tool:** count + search (مثل مثال 04).

## نکات

- `parse_args(["..."])` برای تست
- `type=int` برای اعداد
- `required=True` برای subparsers (3.7+)
- `raise SystemExit(main())` در `__main__`

پاسخ نمونه: [exercise_01.py](./exercise_01.py) | [exercise_02.py](./exercise_02.py)
