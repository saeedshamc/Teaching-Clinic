# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۱ (پیشرفته)
# ============================================================

import argparse
from pathlib import Path


def cmd_calc(args: argparse.Namespace) -> int:
    try:
        match args.op:
            case "add":
                result = args.x + args.y
            case "sub":
                result = args.x - args.y
            case "mul":
                result = args.x * args.y
            case "div":
                result = args.x / args.y
            case _:
                raise ValueError(args.op)
    except ZeroDivisionError:
        print("خطا: تقسیم بر صفر")
        return 1
    if not args.quiet:
        print(result)
    return 0


def cmd_lines(args: argparse.Namespace) -> int:
    total = sum(
        sum(1 for _ in Path(f).open(encoding="utf-8"))
        for f in args.files
        if Path(f).exists()
    )
    if args.verbose:
        print(f"{len(args.files)} فایل")
    print(total)
    return 0


def build_parser() -> argparse.ArgumentParser:
    parser = argparse.ArgumentParser()
    subs = parser.add_subparsers(dest="cmd", required=True)

    c = subs.add_parser("calc")
    c.add_argument("op", choices=["add", "sub", "mul", "div"])
    c.add_argument("x", type=float)
    c.add_argument("y", type=float)
    g = c.add_mutually_exclusive_group()
    g.add_argument("-q", "--quiet", action="store_true")
    g.add_argument("-v", "--verbose", action="store_true")
    c.set_defaults(func=cmd_calc)

    l = subs.add_parser("lines")
    l.add_argument("files", nargs="+")
    l.add_argument("-v", "--verbose", action="store_true")
    l.set_defaults(func=cmd_lines)

    return parser


def main(argv: list[str] | None = None) -> int:
    args = build_parser().parse_args(argv)
    return args.func(args)


if __name__ == "__main__":
    raise SystemExit(main(["calc", "div", "10", "4"]))
    # raise SystemExit(main(["lines", __file__, "-v"]))
