# ============================================================
# تمرین جلسه ۲۱
# هدف: یک context manager بنویسید که قبل و بعد پیام چاپ کند.
# ============================================================

from contextlib import contextmanager


@contextmanager
def section(title):
    print("=== شروع:", title)
    yield
    print("=== پایان:", title)


with section("تمرین"):
    print("در حال اجرا...")
