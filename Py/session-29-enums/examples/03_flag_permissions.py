# ============================================================
# جلسه ۲۹ — Flag: Permission
# ============================================================

from enum import Flag, auto


class Permission(Flag):
    READ = auto()
    WRITE = auto()
    EXECUTE = auto()
    DELETE = auto()


READ_WRITE = Permission.READ | Permission.WRITE
ADMIN = Permission.READ | Permission.WRITE | Permission.DELETE | Permission.EXECUTE


def check(user_perm: Permission, required: Permission) -> bool:
    return required in user_perm


guest = Permission.READ
editor = Permission.READ | Permission.WRITE

print("guest READ?", check(guest, Permission.READ))
print("guest WRITE?", check(guest, Permission.WRITE))
print("editor WRITE?", check(editor, Permission.WRITE))
print("ADMIN flags:", ADMIN)
print("READ in ADMIN?", Permission.READ in ADMIN)
