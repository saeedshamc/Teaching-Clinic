# تمرین‌های جلسه ۳۶ — HTML Parsing

## کلاسی (۳۰ دقیقه)

1. **TitleParser:** title از HTML نمونه.
2. **LinkParser:** لیست همه href.
3. **Tag counter:** dict از tag → count.
4. **escape:** `<script>` → safe string.

## خانه (۴۵ دقیقه)

5. **HeadingExtractor:** h1, h2, h3 متن.
6. **PageScraper:** title + headings + links.
7. **TextExtractor:** همه متن بدون تگ.
8. **File HTML:** خواندن فایل .html و parse.

## نکات

- state machine با flag (`_in_title`)
- `parser.feed()` می‌تواند chunk باشد
- `html.unescape` برای entities
- HTMLParser برای scraper سنگین کافی نیست

پاسخ نمونه: [exercise_01.py](./exercise_01.py) | [exercise_02.py](./exercise_02.py)
