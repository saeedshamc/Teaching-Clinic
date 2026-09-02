# ============================================================
# تمرین ۱–۳ جلسه ۲۳ — Regex (کلاسی)
# ============================================================

import re

text = "قیمت 150000 تومان و تخفیف 20 درصد"

# --- ۱. search ---
match = re.search(r"\d+", text)
print("اولین عدد:", match.group() if match else "ندارد")

# --- ۲. findall ---
numbers = re.findall(r"\d+", text)
print("همه اعداد:", numbers)

# --- ۳. sub ---
messy = "سلام   دنیا  !"
clean = re.sub(r"\s+", " ", messy).strip()
print("تمیز:", clean)
