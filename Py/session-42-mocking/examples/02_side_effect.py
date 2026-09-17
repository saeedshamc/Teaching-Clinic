# ============================================================
# جلسه ۴۲ — side_effect
# فایل: 02_side_effect.py
# ============================================================

from unittest.mock import Mock


def retry_call(fn, max_attempts: int = 3):
    last_error = None
    for _ in range(max_attempts):
        try:
            return fn()
        except ConnectionError as e:
            last_error = e
    raise last_error


unstable = Mock()
unstable.connect.side_effect = [
    ConnectionError("timeout"),
    ConnectionError("timeout"),
    "connected",
]

result = retry_call(unstable.connect, max_attempts=3)
print("نتیجه:", result)
print("تعداد تلاش:", unstable.connect.call_count)

fail_always = Mock()
fail_always.connect.side_effect = ConnectionError("down")
try:
    retry_call(fail_always.connect, max_attempts=2)
except ConnectionError as e:
    print("خطای مورد انتظار:", e)
