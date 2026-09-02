# ============================================================
# جلسه ۴ — break و continue
# فایل: 03_break_continue.py
# ============================================================

print("--- break ---")
for i in range(1, 8):
    if i == 5:
        break
    print(i, end=" ")
print()

print("--- continue (فقط اعداد فرد) ---")
for i in range(1, 8):
    if i % 2 == 0:
        continue
    print(i, end=" ")
print()

print("--- else روی for ---")
for n in [2, 4, 8]:
    if n % 2 == 1:
        print("فرد یافت شد")
        break
else:
    print("همه اعداد زوج بودند")
