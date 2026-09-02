# ============================================================
# جلسه ۳۰ — Counter
# ============================================================

from collections import Counter

fruits = ["سیب", "موز", "سیب", "پرتقال", "سیب", "موز", "سیب"]
counts = Counter(fruits)

print("شمارش:", counts)
print("تعداد سیب:", counts["سیب"])
print("تعداد انگور:", counts["انگور"])  # 0 — بدون KeyError

print("\nپرتکرارترین ۲:")
for item, n in counts.most_common(2):
    print(f"  {item}: {n}")

letters = Counter("abracadabra")
print("\nحروف:", letters)
print("جمع a+c:", letters["a"] + letters["c"])

counts.update(["موز", "کیوی", "کیوی"])
print("\nبعد از update:", counts)
