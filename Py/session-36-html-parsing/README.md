# جلسه ۳۶: پردازش HTML — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- ساختار HTML: tags, attributes, text
- `html.parser.HTMLParser` — callback-based
- استخراج title, links, headings
- `html.escape` و `html.unescape`
- محدودیت parser استاندارد
- معرفی BeautifulSoup (مرجع — third-party)
- الگوهای real-world: scraper ساده

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | HTML structure |
| 10–25 | HTMLParser callbacks |
| 25–40 | extract title و links |
| 40–55 | شمارش تگ‌ها |
| 55–70 | escape/unescape |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## ۱. HTML ساده

```html
<html>
  <head><title>صفحه من</title></head>
  <body>
    <h1>سلام</h1>
    <a href="https://python.org">پایتون</a>
  </body>
</html>
```

---

## ۲. HTMLParser

```python
from html.parser import HTMLParser

class MyParser(HTMLParser):
    def handle_starttag(self, tag, attrs):
        print("start", tag, dict(attrs))
    def handle_endtag(self, tag):
        print("end", tag)
    def handle_data(self, data):
        print("data", data.strip())

parser = MyParser()
parser.feed("<p>سلام <b>دنیا</b></p>")
```

---

## ۳. استخراج title

```python
class TitleParser(HTMLParser):
    def __init__(self):
        super().__init__()
        self.in_title = False
        self.title = ""

    def handle_starttag(self, tag, attrs):
        self.in_title = (tag == "title")

    def handle_data(self, data):
        if self.in_title:
            self.title += data
```

---

## ۴. استخراج links

```python
class LinkParser(HTMLParser):
    def __init__(self):
        super().__init__()
        self.links = []

    def handle_starttag(self, tag, attrs):
        if tag == "a":
            href = dict(attrs).get("href")
            if href:
                self.links.append(href)
```

---

## ۵. html.escape

```python
import html

safe = html.escape('<script>alert("x")</script>')
print(safe)  # &lt;script&gt;...

text = html.unescape("&amp; پایتون")
```

---

## ۶. محدودیت HTMLParser

- HTML شکسته را tolerantly parse می‌کند اما کامل نیست
- CSS selector ندارد
- DOM tree کامل نمی‌سازد
- برای scraper جدی → **BeautifulSoup** یا **lxml**

```python
# pip install beautifulsoup4
# from bs4 import BeautifulSoup
# soup = BeautifulSoup(html, "html.parser")
# soup.find_all("a")
```

---

## ۷. handle_startendtag

```python
def handle_startendtag(self, tag, attrs):
    # <img />, <br />
    pass
```

---

## ۸. error handling

```python
class LenientParser(HTMLParser):
    def error(self, message):
        pass  # پیش‌فرض: raise
```

---

## ۹. regex vs parser

| | regex | HTMLParser |
|---|-------|------------|
| nested tags | ضعیف | بهتر |
| speed | سریع | کافی |
| maintain | سخت | بهتر |

**هرگز** HTML پیچیده را فقط با regex parse نکنید.

---

## ۱۰. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_parser_basics.py](./examples/01_parser_basics.py) | callbacks |
| [02_extract_title.py](./examples/02_extract_title.py) | title |
| [03_link_counter.py](./examples/03_link_counter.py) | links |
| [04_mini_scraper.py](./examples/04_mini_scraper.py) | scraper |

---

## ۱۱. ethics و robots.txt

- قوانین سایت را رعایت کنید
- rate limit
- robots.txt
- داده شخصی — GDPR/privacy

---

## ۱۲. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| regex برای HTML | fragile |
| فراموش reset parser | state قدیمی |
| nested tags ساده | state machine لازم |
| scrape بدون delay | ban IP |

---

## ۱۳. تمرین کلاسی

1. title extractor
2. شمارش `<a>` tags
3. لیست hrefها
4. html.escape روی user input

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۴. TextExtractor عمومی

```python
class TextExtractor(HTMLParser):
    def __init__(self):
        super().__init__()
        self.texts = []
    def handle_data(self, data):
        t = data.strip()
        if t:
            self.texts.append(t)
```

---

## ۱۵. HTMLParser.reset

```python
parser = TitleParser()
parser.feed(chunk1)
parser.feed(chunk2)
parser.close()
parser.reset()  # parse سند جدید
```

---

## ۱۶. handle_entityref

```python
def handle_entityref(self, name):
    # &amp; &lt; ...
    pass
```

---

## ۱۷. سوالات کلاسی

- چرا regex برای HTML کافی نیست؟
- state machine در parser چگونه کار می‌کند؟
- BeautifulSoup چه مزیتی دارد؟

---

## ۱۸. strip_tags ساده (احتیاط)

```python
import re
re.sub(r"<[^>]+>", "", html)  # فقط آموزش — parser بهتر
```

---

## ۱۹. encoding در HTML

```python
# <meta charset="utf-8"> — همیشه UTF-8 فرض کنید
```

---

## ۲۰. جمع‌بندی

HTMLParser برای **درک parsing** — scraper production معمولاً BeautifulSoup/lxml می‌خواهد.

**تکلیف خانه:** [question.md](./exercises/question.md) — PageScraper کامل.

### چک‌لیست پایان جلسه

- [ ] HTMLParser callbacks
- [ ] title استخراج شد
- [ ] links شمارش شد
- [ ] html.escape امتحان شد

---

## 📚 مطالعه تکمیلی

- [html.parser — Python docs](https://docs.python.org/3/library/html.parser.html)
- BeautifulSoup documentation

---

**جلسه قبل:** [۳۵ — HTTP Client](../session-35-http-client/) | **بعد:** [۳۷ — Env Config](../session-37-env-config/)
