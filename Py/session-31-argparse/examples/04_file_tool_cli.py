# ============================================================
# جلسه ۳۱ — argparse: ابزار فایل CLI
# ============================================================

import argparse
from pathlib import Path


def cmd_count(args: argparse.Namespace) -> int:
    path = Path(args.file)
    if not path.exists():
        print(f"خطا: {path} وجود ندارد")
        return 1
    text = path.read_text(encoding="utf-8")
    words = len(text.split())
    lines = text.count("\n") + (1 if text else 0)
    print(f"فایل: {path.name}")
    print(f"  کلمات: {words}")
    print(f"  خطوط: {lines}")
    return 0


def cmd_search(args: argparse.Namespace) -> int:
    root = Path(args.directory)
    pattern = args.pattern
    found = 0
    for py in root.rglob("*.py"):
        if pattern in py.read_text(encoding="utf-8", errors="ignore"):
            print(py)
            found += 1
    print(f"\n{found} فایل یافت شد")
    return 0


def build_parser() -> argparse.ArgumentParser:
    parser = argparse.ArgumentParser(
        description="ابزار فایل ساده",
        epilog="مثال: python 04_file_tool_cli.py count myfile.txt",
    )
    subs = parser.add_subparsers(dest="cmd", required=True)

    c = subs.add_parser("count", help="شمارش کلمات")
    c.add_argument("file", help="مسیر فایل")
    c.set_defaults(func=cmd_count)

    s = subs.add_parser("search", help="جستجو در .py")
    s.add_argument("directory", help="پوشه ریشه")
    s.add_argument("pattern", help="رشته جستجو")
    s.set_defaults(func=cmd_search)

    return parser


def main(argv: list[str] | None = None) -> int:
    args = build_parser().parse_args(argv)
    return args.func(args)


if __name__ == "__main__":
    # demo با فایل خود اسکریپت
    script = Path(__file__)
    raise SystemExit(main(["count", str(script)]))
