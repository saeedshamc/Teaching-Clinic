# ============================================================
# جلسه ۳۶ — HTML: استخراج title
# ============================================================

from html.parser import HTMLParser


class TitleParser(HTMLParser):
    def __init__(self):
        super().__init__()
        self._in_title = False
        self.title = ""

    def handle_starttag(self, tag, attrs):
        if tag == "title":
            self._in_title = True

    def handle_endtag(self, tag):
        if tag == "title":
            self._in_title = False

    def handle_data(self, data):
        if self._in_title:
            self.title += data


SAMPLES = [
    "<html><head><title>صفحه اصلی</title></head><body></body></html>",
    "<html><head><title>پایتون &amp; داده</title></head></html>",
    "<html><body><h1>بدون title</h1></body></html>",
]

for i, html in enumerate(SAMPLES, 1):
    p = TitleParser()
    p.feed(html)
    print(f"{i}. title = {p.title!r}")
