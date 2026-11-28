# پروژهٔ جلسه ۴۸ — تحلیل دادهٔ فروش

یک سیستم تحلیل فروش بسازید که:
- `List<Sale>` با `product`, `quantity`, `price`
- stream برای: مجموع فروش، بیشترین فروش، میانگین قیمت، فیلتر بر اساس مقدار
- `Collectors.groupingBy` برای گروه‌بندی بر اساس محصول
- در `main` دادهٔ نمونه بسازید و گزارش چاپ کنید

نمونه:
```
Total sales: 15000
Top product: Laptop (5000)
Average price: 2500
Sales by product: {Laptop=2, Phone=3, Tablet=1}
```

## صورت پروژه
پاسخ در `solution/SalesAnalyzer.java` — از stream و Collectors استفاده کنید.
