# پروژه ۱ — مدل‌های «کتابخانه» با روابط کامل (جلسات ۱–۵)

## هدف
اپ `library` با مدل‌های `Author`, `Book`, `Tag`, `Profile` و روابط کامل بسازید.

## تسک‌ها

1. `Author`: name, birth_year, bio — `__str__` و `Meta.ordering`
2. `Book`: title, author(FK→Author, related_name="books"), tags(M2M→Tag), pages, price, is_available, created_at/updated_at
3. `Tag`: name (unique)
4. `Profile`: user(OneToOne→User), phone, avatar(ImageField)
5. `makemigrations` + `migrate` + چند رکورد نمونه در `shell`

## معیارهای پذیرش

- [ ] هر ۴ مدل migrate شده‌اند
- [ ] `author.books.all()` کار می‌کند
- [ ] `book.tags.all()` کار می‌کند
- [ ] `user.profile` قابل دسترسی است

## نکات

- `on_delete=CASCADE` برای MOST relations، `SET_NULL` برای `created_by`
- `blank=True, null=True` برای فیلدهای اختیاری
- از `python manage.py shell` برای تست QuerySet استفاده کنید
