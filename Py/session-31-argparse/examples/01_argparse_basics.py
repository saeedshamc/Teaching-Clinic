# ============================================================
# جلسه ۳۱ — argparse: positional
# ============================================================

import argparse


def main(argv: list[str] | None = None) -> int:
    parser = argparse.ArgumentParser(description="ماشین‌حساب جمع")
    parser.add_argument("x", type=int, help="عدد اول")
    parser.add_argument("y", type=int, help="عدد دوم")
    args = parser.parse_args(argv)

    result = args.x + args.y
    print(f"{args.x} + {args.y} = {result}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main(["10", "25"]))
