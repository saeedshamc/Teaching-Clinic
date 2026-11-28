# پروژهٔ جلسه ۷۷ — سیستم چند Activity

یک سیستم چند Activity بسازید که:
- `MainActivity` با `Button` برای navigation
- `SecondActivity` با `TextView` و `Button` برای result
- `Intent` برای data passing
- `startActivityForResult` برای result
- `onActivityResult` برای handle result
- در manifest هر دو activity را اضافه کنید

نمونه:
```
MainActivity → [Button: Go to Second] → SecondActivity
SecondActivity → [Button: Send Result] → MainActivity (with result)
```

## صورت پروژه
پاسخ در `solution/` — MainActivity.java + SecondActivity.java + layouts + manifest.
