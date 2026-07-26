# ============================================================
# تمرین جلسه ۳۱
# هدف: آرگومانی برای نام کاربر بسازید و خوش‌آمد بگویید.
# ============================================================

import argparse

parser = argparse.ArgumentParser()
parser.add_argument("--name", default="مهمان")
args = parser.parse_args(["--name", "سعید"])
print("سلام", args.name)
