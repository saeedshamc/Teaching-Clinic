# پاسخ تمرین‌های Mini Market

## آسان
ساختار آماده است. فروش در `newSale()` — حلقه تا `x`؛ موجودی با
`p.stock -= qty` کم می‌شود؛ فاکتور در `invoices` ذخیره و چاپ می‌شود.

## متوسط
**۴.** `showReport()` با stream روی `invoices` — sum، count، top-5.
**۵.** `saveData()`/`loadData()` با فرمت `P|...` و `I|...`/`i|...` —
داده‌ها در `minimarket_data.txt` می‌مانند.
**۶.** `showStock()` انتهایش `p.stock < 5` را هشدار می‌دهد.

## سخت

**۷. مالیات:**
```java
// در Invoice
static double TAX_RATE = 0.10;
double tax() { return total * TAX_RATE; }
double grandTotal() { return total + tax(); }
// در newSale چاپ: System.out.printf("مالیات: %.0f%nجمع نهایی: %.0f%n", inv.tax(), inv.grandTotal());
```

**۸. تخفیف:**
```java
System.out.print("کد تخفیف (یا Enter برای رد): ");
String code = sc.nextLine().trim();
if (code.equalsIgnoreCase("DISCOUNT10")) inv.total *= 0.9;
```

**۹. موجودی منفی + پیشنهاد:**
```java
if (qty > p.stock) {
    Product alt = catalog.stream()
        .filter(x -> !x.code.equals(p.code) && x.stock >= qty)
        .findFirst().orElse(null);
    System.out.println(alt != null
        ? "موجودی کافی نیست — پیشنهاد: " + alt.name
        : "موجودی کافی نیست");
    continue;
}
```
