# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۶ (ساده)
# ============================================================

import html
from html.parser import HTMLParser


class TitleParser(HTMLParser):
    def __init__(self):
        super().__init__()
        self._in = False
        self.title = ""

    def handle_starttag(self, tag, attrs):
        self._in = tag == "title"

    def handle_endtag(self, tag):
        if tag == "title":
            self._in = False

    def handle_data(self, data):
        if self._in:
            self.title += data


class LinkParser(HTMLParser):
    def __init__(self):
        super().__init__()
        self.links = []

    def handle_starttag(self, tag, attrs):
        if tag == "a":
            href = dict(attrs).get("href")
            if href:
                self.links.append(href)


sample = '<html><title>T</title><a href="/a">A</a><a href="/b">B</a></html>'

tp = TitleParser()
tp.feed(sample)
print("title:", tp.title)

lp = LinkParser()
lp.feed(sample)
print("links:", lp.links)

unsafe = '<script>alert("x")</script>'
print("safe:", html.escape(unsafe))
