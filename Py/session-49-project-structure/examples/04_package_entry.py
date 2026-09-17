# ============================================================
# جلسه ۴۹ — package entry pattern
# فایل: 04_package_entry.py
# ============================================================
# شبیه‌سازی __main__.py — در پروژه واقعی: python -m myapp


def main() -> None:
    print("=== MyApp v1.0 ===")
    print("دستورات: add | list | quit")


def run_cli(args: list[str]) -> None:
    if not args:
        main()
        return
    cmd = args[0]
    if cmd == "add" and len(args) > 1:
        print(f"افزودن: {args[1]}")
    elif cmd == "list":
        print("لیست خالی")
    else:
        print("دستور ناشناخته:", cmd)


if __name__ == "__main__":
    import sys
    run_cli(sys.argv[1:])
