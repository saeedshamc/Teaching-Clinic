# ============================================================
# تمرین جلسه ۴۲
# هدف: سرویس ایمیل را mock کنید.
# ============================================================

from unittest.mock import Mock

mailer = Mock()
mailer.send.return_value = True
assert mailer.send("a@b.com", "سلام") is True
mailer.send.assert_called_once()
print("OK")
