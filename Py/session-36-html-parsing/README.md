# جلسه ۳۶: پردازش HTML

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- HTML ساده را پارس کنید
- با html.parser تگ‌ها را استخراج کنید
- ایده اولیه وب‌اسکرپینگ را درک کنید

---

## پارس HTML چیست؟

برای خواندن صفحه وب، باید تگ‌های HTML را تحلیل کنید. کتابخانه استاندارد html.parser نقطه شروع خوبی است.

```python
from html.parser import HTMLParser

class TitleParser(HTMLParser):
    def __init__(self):
        super().__init__()
        self.in_title = False
        self.title = ""
    def handle_starttag(self, tag, attrs):
        self.in_title = tag == "title"
    def handle_data(self, data):
        if self.in_title:
            self.title += data

parser = TitleParser()
parser.feed("<html><title>سلام</title></html>")
print(parser.title)
```

### توضیح
- `HTMLParser` → پایه پارس HTML
- `handle_starttag` → شروع تگ
- `handle_data` → متن داخل تگ

---

## فایل‌های این جلسه
- [examples/01_html_parser.py](./examples/01_html_parser.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: title را استخراج کنید
- تمرین متوسط: تعداد لینک‌ها را بشمارید
