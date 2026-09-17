# تمرین‌های جلسه ۳۲ — NuGet

## کلاسی (۳۰ دقیقه)

1. پروژه `NuGetDemo` بسازید
2. `dotnet list package` قبل و بعد از add
3. csproj را باز کنید و PackageReference را پیدا کنید

## خانه (۴۵ دقیقه)

4. package `Humanizer` نصب کنید — `"hello world".Titleize()`
5. solution دو پروژه + ProjectReference
6. `dotnet list package --outdated` و یک package update

## نکات

- version pinning در production
- package معتبر از nuget.org
- restore در CI pipeline

پاسخ نمونه: [exercise_01.cs](./exercise_01.cs)
