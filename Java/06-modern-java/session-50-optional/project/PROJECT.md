# پروژهٔ جلسه ۵۰ — UserService با Optional

یک `UserService` بسازید که:
- `findById(String)` → `Optional<User>`
- `findByEmail(String)` → `Optional<User>`
- `getOrCreate(String)` → `Optional<User>` (ایجاد اگر نبود)
- `getNameOrDefault(String)` → `String` با default
- در `main` چند سناریو تست کنید

نمونه:
```
findById("1") → Optional[Ali]
findById("999") → Optional.empty
getNameOrDefault("999") → "Unknown User"
```

## صورت پروژه
پاسخ در `solution/UserService.java` — از Optional و map/orElse استفاده کنید.
