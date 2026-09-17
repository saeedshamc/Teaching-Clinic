# ============================================================
# جلسه ۳۶ — HTML: mini scraper
# ============================================================

import html as html_module
from html.parser import HTMLParser


class PageScraper(HTMLParser):
    def __init__(self):
        super().__init__()
        self.title = ""
        self.headings: list[str] = []
        self.links: list[tuple[str, str]] = []
        self._in_title = False
        self._in_heading: str | None = None
        self._buf = ""

    def handle_starttag(self, tag, attrs):
        attrs_d = dict(attrs)
        if tag == "title":
            self._in_title = True
        elif tag in ("h1", "h2", "h3"):
            self._in_heading = tag
            self._buf = ""
        elif tag == "a" and "href" in attrs_d:
            self._current_href = attrs_d["href"]
            self._link_text = ""

    def handle_endtag(self, tag):
        if tag == "title":
            self._in_title = False
        elif tag == "a" and hasattr(self, "_current_href"):
            self.links.append((self._current_href, self._link_text.strip()))
            del self._current_href
        elif tag == self._in_heading:
            self.headings.append(self._buf.strip())
            self._in_heading = None

    def handle_data(self, data):
        if self._in_title:
            self.title += data
        elif self._in_heading:
            self._buf += data
        elif hasattr(self, "_current_href"):
            self._link_text = getattr(self, "_link_text", "") + data


RAW = """
<html>
<head><title>وبلاگ پایتون</title></head>
<body>
  <h1>اولین پست</h1>
  <p>سلام <b>خواننده</b>!</p>
  <a href="https://python.org">سایت پایتون</a>
  <h2>منابع</h2>
  <a href="/about">درباره</a>
</body>
</html>
"""

scraper = PageScraper()
scraper.feed(RAW)

print("Title:", html_module.unescape(scraper.title))
print("Headings:", scraper.headings)
print("Links:")
for href, text in scraper.links:
    print(f"  [{text}] → {href}")
