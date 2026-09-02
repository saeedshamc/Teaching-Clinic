# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۶ (پیشرفته)
# ============================================================

from html.parser import HTMLParser
from pathlib import Path


class TextExtractor(HTMLParser):
    def __init__(self):
        super().__init__()
        self.texts: list[str] = []

    def handle_data(self, data):
        t = data.strip()
        if t:
            self.texts.append(t)


HTML = """
<html>
<head><title>Test</title></head>
<body>
  <h1>عنوان</h1>
  <p>پارagraph اول.</p>
  <p>پارagraph دوم.</p>
</body>
</html>
"""


def extract_text(html: str) -> str:
    p = TextExtractor()
    p.feed(html)
    return "\n".join(p.texts)


print(extract_text(HTML))

# از فایل (demo)
demo = Path(__file__).parent / "_demo.html"
demo.write_text(HTML, encoding="utf-8")
print("\nfrom file:")
print(extract_text(demo.read_text(encoding="utf-8")))
demo.unlink(missing_ok=True)
