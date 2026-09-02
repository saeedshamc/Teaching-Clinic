# تمرین‌های جلسه ۲۹ — JSON Serialization

## کلاسی (۳۰ دقیقه)

1. کلاس `Book` (Title, Author, Year, Price) → JSON → object
2. لیست 3 کتاب serialize با `WriteIndented = true`
3. JSON را در `library.json` ذخیره و دوباره load کنید

## خانه (۴۵ دقیقه)

4. `ApiResponse<T>` با Success, Data, ErrorMessage — generic serialize
5. `[JsonPropertyName]` برای فیلد `"isbn"` روی property `ISBN`
6. deserialize JSON نامعتبر — catch `JsonException`

## نکات

- `System.Text.Json` built-in است
- camelCase برای APIهای web
- null-check بعد از Deserialize
- record types هم serialize می‌شوند

پاسخ نمونه: [exercise_01.cs](./exercise_01.cs)
