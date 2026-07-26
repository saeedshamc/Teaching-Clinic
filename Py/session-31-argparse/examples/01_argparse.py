# ============================================================
# جلسه ۳۱ — argparse
# فایل: 01_argparse.py
# ============================================================

import argparse

parser = argparse.ArgumentParser(description="ماشین‌حساب ساده")
parser.add_argument("x", type=int, help="عدد اول")
parser.add_argument("y", type=int, help="عدد دوم")
parser.add_argument("--op", choices=["add", "mul"], default="add")
args = parser.parse_args(["3", "5", "--op", "mul"])

if args.op == "add":
    print("جمع:", args.x + args.y)
else:
    print("ضرب:", args.x * args.y)
