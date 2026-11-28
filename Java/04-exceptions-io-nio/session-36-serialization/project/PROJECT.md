# پروژهٔ جلسه ۳۶ — ذخیره‌سازی شیء

یک سیستم ذخیره‌سازی بسازید که:
- `GameSave` با `playerName`, `level`, `score`, `inventory` (List<String>)
- `Serializable` با `serialVersionUID`
- `transient` برای `sessionToken`
- متدهای `save()` و `load()`
- در `main` یک save بسازید، ذخیره و بارگذاری کنید

نمونه:
```
Saved: Ali level=5 score=1000
Loaded: Ali level=5 score=1000
sessionToken: null (transient)
```

## صورت پروژه
پاسخ در `solution/GameSave.java` — از `Serializable` و try-with-resources استفاده کنید.
