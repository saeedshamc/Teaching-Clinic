# ============================================================
# تمرین جلسه ۳۶
# هدف: تعداد تگ‌های a را بشمارید.
# ============================================================

from html.parser import HTMLParser

class LinkCounter(HTMLParser):
    def __init__(self):
        super().__init__()
        self.count = 0
    def handle_starttag(self, tag, attrs):
        if tag == "a":
            self.count += 1

parser = LinkCounter()
parser.feed('<a href="#">۱</a><a href="#">۲</a>')
print(parser.count)
