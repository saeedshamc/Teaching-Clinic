# ============================================================
# جلسه ۳۱ — argparse: optional flags
# ============================================================

import argparse


def greet(name: str, times: int, verbose: bool) -> None:
    msg = f"سلام {name}!"
    for _ in range(times):
        if verbose:
            print(f"[verbose] {msg}")
        else:
            print(msg)


def main(argv: list[str] | None = None) -> int:
    parser = argparse.ArgumentParser(description="سلام CLI")
    parser.add_argument("--name", "-n", default="کاربر", help="نام")
    parser.add_argument("--count", "-c", type=int, default=1, help="تعداد تکرار")
    parser.add_argument("--verbose", "-v", action="store_true", help="جزئیات بیشتر")
    parser.add_argument(
        "--format",
        choices=["short", "long"],
        default="short",
        help="نوع پیام",
    )
    args = parser.parse_args(argv)

    if args.format == "long":
        print(f"فرمت: long | name={args.name}")
    greet(args.name, args.count, args.verbose)
    return 0


if __name__ == "__main__":
    raise SystemExit(main(["-n", "سعید", "-c", "2", "-v"]))
