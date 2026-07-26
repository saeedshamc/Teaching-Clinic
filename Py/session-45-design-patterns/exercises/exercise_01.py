# ============================================================
# تمرین جلسه ۴۵
# هدف: factory برای ساخت نقش کاربری بنویسید.
# ============================================================

def role_factory(name: str) -> dict:
    roles = {
        "admin": {"role": "admin", "access": "full"},
        "user": {"role": "user", "access": "limited"},
    }
    return roles[name]

print(role_factory("admin"))
