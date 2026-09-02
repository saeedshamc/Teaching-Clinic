# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۱ (ساده)
# ============================================================

import argparse


def main(argv: list[str] | None = None) -> int:
    parser = argparse.ArgumentParser(description="ماشین‌حساب")
    parser.add_argument("x", type=float)
    parser.add_argument("y", type=float)
    parser.add_argument("--name", "-n", default="مهمان")
    parser.add_argument("--verbose", "-v", action="store_true")
    parser.add_argument("--op", choices=["add", "sub", "mul"], default="add")
    args = parser.parse_args(argv)

    ops = {
        "add": args.x + args.y,
        "sub": args.x - args.y,
        "mul": args.x * args.y,
    }
    result = ops[args.op]

    if args.verbose:
        print(f"[{args.name}] {args.op}({args.x}, {args.y})")
    print(f"نتیجه: {result}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main(["5", "3", "-n", "سعید", "-v", "--op", "mul"]))
