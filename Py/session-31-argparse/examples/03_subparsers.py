# ============================================================
# جلسه ۳۱ — argparse: subparsers
# ============================================================

import argparse


def cmd_add(args: argparse.Namespace) -> int:
    print(f"افزودن: {args.item} (اولویت {args.priority})")
    return 0


def cmd_list(args: argparse.Namespace) -> int:
    items = ["خرید نان", "تمرین پایتون", "جلسه"]
    if args.done:
        items = [i for i in items if "تمرین" in i]
    for i, item in enumerate(items, 1):
        print(f"{i}. {item}")
    return 0


def build_parser() -> argparse.ArgumentParser:
    parser = argparse.ArgumentParser(description="مدیریت todo")
    subs = parser.add_subparsers(dest="command", required=True)

    add_p = subs.add_parser("add", help="افزودن آیتم")
    add_p.add_argument("item", help="متن todo")
    add_p.add_argument("-p", "--priority", type=int, default=1, choices=[1, 2, 3])
    add_p.set_defaults(func=cmd_add)

    list_p = subs.add_parser("list", help="نمایش لیست")
    list_p.add_argument("--done", action="store_true", help="فقط انجام‌شده")
    list_p.set_defaults(func=cmd_list)

    return parser


def main(argv: list[str] | None = None) -> int:
    parser = build_parser()
    args = parser.parse_args(argv)
    return args.func(args)


if __name__ == "__main__":
    print("=== add ===")
    main(["add", "خواندن کتاب", "-p", "2"])
    print("\n=== list ===")
    main(["list"])
