# تمرین‌های جلسه ۳۷ — Env Config

## کلاسی (۳۰ دقیقه)

1. **get:** APP_HOST و APP_PORT با default.
2. **bool:** DEBUG از env — true/false/1/0.
3. **Settings:** dataclass frozen با from_env().
4. **require:** تابع require_env — RuntimeError.

## خانه (۴۵ دقیقه)

5. **load_dotenv:** parser ساده KEY=VALUE.
6. **validation:** port باید 1-65535.
7. **AppConfig:** env + log_level + data_dir.
8. **.gitignore:** چرا .env commit نشود؟

## نکات

- secrets هرگز در repo
- `setdefault` vs `override` در loader
- frozen Settings — immutable config
- production: require_env برای SECRET_KEY

پاسخ نمونه: [exercise_01.py](./exercise_01.py) | [exercise_02.py](./exercise_02.py)
