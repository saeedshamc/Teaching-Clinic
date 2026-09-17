# ============================================================
# تمرین ۴–۵ جلسه ۱۵ — Iterator و Generator (خانه)
# ============================================================

# --- ۴. generator expression ---
even_squares = (n * n for n in range(11) if n % 2 == 0)
print("مربع اعداد زوج:", list(even_squares))

# --- ۵. filter_by_length ---


def filter_by_length(words, min_len):
    for word in words:
        if len(word) >= min_len:
            yield word


words = ["سلام", "د", "پایتون", "کد", "برنامه"]
long_words = list(filter_by_length(words, 3))
print("کلمات بلند:", long_words)
