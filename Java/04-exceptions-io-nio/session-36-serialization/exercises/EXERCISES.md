# تمرین‌های جلسه ۳۶ — Serialization

## آسان
1. کلاس `Student` را `Serializable` کنید و serialize/deserialize کنید.
2. `serialVersionUID` را اضافه کنید.

## متوسط
3. فیلد `transient` برای password اضافه کنید و نشان دهید null می‌شود.
4. چند object را در یک فایل بنویسید و بخوانید.

## سخت
5. `serialVersionUID` را تغییر دهید و `InvalidClassException` را ببینید.
6. object با reference (مثل `Student` که `Address` دارد) را serialize کنید.
