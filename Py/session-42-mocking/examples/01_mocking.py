# ============================================================
# جلسه ۴۲ — Mocking
# فایل: 01_mocking.py
# ============================================================

from unittest.mock import Mock

api = Mock()
api.get_user.return_value = {"name": "سعید"}

user = api.get_user(1)
print("کاربر:", user["name"])
api.get_user.assert_called_once_with(1)
print("فراخوانی API تأیید شد")
