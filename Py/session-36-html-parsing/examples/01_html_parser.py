# ============================================================
# جلسه ۳۶ — HTML Parsing
# فایل: 01_html_parser.py
# ============================================================

from html.parser import HTMLParser

class TitleParser(HTMLParser):
    def __init__(self):
        super().__init__()
        self.in_title = False
        self.title = ""

    def handle_starttag(self, tag, attrs):
        self.in_title = tag == "title"

    def handle_endtag(self, tag):
        if tag == "title":
            self.in_title = False

    def handle_data(self, data):
        if self.in_title:
            self.title += data

parser = TitleParser()
parser.feed("<html><head><title>دوره Python</title></head></html>")
print("عنوان:", parser.title)
