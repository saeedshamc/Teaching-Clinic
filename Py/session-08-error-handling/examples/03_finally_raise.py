# ============================================================
# جلسه ۸ — finally و raise
# فایل: 03_finally_raise.py
# ============================================================

def process_file(path):
    f = None
    try:
        f = open(path, "r", encoding="utf-8")
        return f.read()
    except FileNotFoundError:
        print(f"فایل {path} یافت نشد")
        return None
    finally:
        if f:
            f.close()
        print("بلوک try تمام شد")


process_file("nofile.txt")


def set_age(age):
    if not isinstance(age, int):
        raise TypeError("سن باید int باشد")
    if age < 0 or age > 150:
        raise ValueError("سن نامعتبر")
    return age


try:
    set_age(-5)
except ValueError as e:
    print("خطا:", e)

print("سن OK:", set_age(25))
