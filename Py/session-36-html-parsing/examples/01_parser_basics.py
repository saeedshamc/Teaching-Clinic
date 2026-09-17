# ============================================================
# جلسه ۳۶ — HTMLParser: callbacks
# ============================================================

from html.parser import HTMLParser


class DebugParser(HTMLParser):
    def handle_starttag(self, tag, attrs):
        print(f"  START <{tag}> {dict(attrs)}")

    def handle_endtag(self, tag):
        print(f"  END   </{tag}>")

    def handle_data(self, data):
        text = data.strip()
        if text:
            print(f"  DATA  {text!r}")


html = """
<div class="card">
  <p>سلام <b>دنیا</b></p>
  <img src="logo.png" alt="logo"/>
</div>
"""

print("=== parse ===")
parser = DebugParser()
parser.feed(html)
