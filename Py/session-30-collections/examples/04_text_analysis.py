# ============================================================
# جلسه ۳۰ — تحلیل متن با collections
# ============================================================

import re
from collections import Counter, defaultdict

TEXT = """
پایتون زبان برنامه‌نویسی پایتون است.
collections ماژول قدرتمند collections در پایتون است.
Counter و defaultdict از collections هستند.
"""

tokens = re.findall(r"[\w\u0600-\u06FF]+", TEXT.lower())
word_freq = Counter(tokens)

print("۱۰ کلمه پرتکرار:")
for word, count in word_freq.most_common(10):
    print(f"  {word:12} → {count}")

# گروه‌بندی بر اساس حرف اول
by_letter = defaultdict(list)
for word in set(tokens):
    by_letter[word[0]].append(word)

print("\nکلمات بر اساس حرف اول (نمونه):")
for letter in sorted(by_letter)[:3]:
    print(f"  {letter}: {sorted(by_letter[letter])[:5]}")

print(f"\nمجموع کلمات: {sum(word_freq.values())}")
print(f"کلمات یکتا: {len(word_freq)}")
