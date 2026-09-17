# ============================================================
# جلسه ۲۱ — Context Manager زمان‌سنج
# فایل: 03_timer_cm.py
# ============================================================

import time
from contextlib import contextmanager


@contextmanager
def timer(label: str = "عملیات"):
    """زمان اجرای بلوک with را اندازه می‌گیرد"""
    start = time.perf_counter()
    yield
    elapsed = time.perf_counter() - start
    print(f"⏱ {label}: {elapsed:.4f} ثانیه")


@contextmanager
def temporary_print_suppressed():
    """نمونه — yield قبل و بعد cleanup"""
    print("  [شروع بلوک]")
    try:
        yield
    finally:
        print("  [پایان بلوک — cleanup]")


with timer("جمع ۱ میلیون"):
    total = sum(range(1_000_000))
    print(f"  نتیجه: {total}")

print()

with temporary_print_suppressed():
    print("  کار داخل بلوک")
