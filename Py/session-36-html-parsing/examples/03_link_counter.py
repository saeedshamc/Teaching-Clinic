# ============================================================
# جلسه ۳۶ — HTML: شمارش و استخراج لینک
# ============================================================

from html.parser import HTMLParser


class LinkParser(HTMLParser):
    def __init__(self):
        super().__init__()
        self.links: list[str] = []
        self.tag_counts: dict[str, int] = {}

    def handle_starttag(self, tag, attrs):
        self.tag_counts[tag] = self.tag_counts.get(tag, 0) + 1
        if tag == "a":
            href = dict(attrs).get("href")
            if href:
                self.links.append(href)


HTML = """
<html>
<body>
  <h1>منابع</h1>
  <a href="https://python.org">پایتون</a>
  <a href="/docs">مستندات</a>
  <a href="https://docs.python.org">Docs</a>
  <p>متن <a href="#top">بالا</a></p>
</body>
</html>
"""

parser = LinkParser()
parser.feed(HTML)

print(f"تعداد <a>: {parser.tag_counts.get('a', 0)}")
print("لینک‌ها:")
for href in parser.links:
    print(f"  {href}")
