# جلسه ۱۰: Generics کاربردی (۹۰ دقیقه)

**محیط فرض:** Go 1.22+ (generics از 1.18)

## پیش‌نیاز
- جلسات ۰۱ تا ۰۹

## اهداف یادگیری
پس از این جلسه می‌توانید:
1. تابع جنریک با پارامتر نوع بنویسید
2. محدودیت (**constraint**) ساده مثل `comparable` را به‌کار ببرید
3. بگویید چه وقت generic مفید است و چه وقت interface کافی است
4. یک `Map`/`Filter` کوچک روی slice بسازید

## چرا این مطلب مهم است
بدون generic، یا `interface{}` + type assert تکراری می‌نویسید یا برای هر نوع کپی کد. در کتابخانه‌های کمکی slice، generic خوانایی و ایمنی نوع می‌آورد.

## مفهوم اصلی

```go
func First[T any](items []T) (T, bool) {
	var zero T
	if len(items) == 0 {
		return zero, false
	}
	return items[0], true
}

func Keys[K comparable, V any](m map[K]V) []K { ... }
```

`any` معادل `interface{}` است. برای کلید map به `comparable` نیاز دارید.

## اشتباهات رایج
1. **generic کردن همه‌چیز** — برای یک نوع مشخص، تابع ساده خواناتر است
2. **فراموش constraint وقتی عملگر لازم است**
3. **برگرداندن zero بدون `bool`/error** و ابهام با مقدار واقعی صفر

## مثال آموزشی قدم‌به‌قدم

```bash
cd session-10-generics/examples
go run .
```

```go
package main

import "fmt"

func MapSlice[T any, R any](in []T, f func(T) R) []R {
	out := make([]R, 0, len(in))
	for _, v := range in {
		out = append(out, f(v))
	}
	return out
}

func main() {
	nums := []int{1, 2, 3}
	strs := MapSlice(nums, func(n int) string { return fmt.Sprintf("#%d", n) })
	fmt.Println(strs)
}
```

## نسخهٔ بهتر / الگوی درست
اول با یک نوع خاص بنویسید؛ وقتی تکرار نوع‌ها درد شد، generic کنید. از پکیج `slices` استاندارد هم آگاه باشید.

## تمرین عملی

### آسان
`Contains[T comparable](items []T, want T) bool` بنویسید.

### چالش
`Filter[T any](items []T, ok func(T) bool) []T` و روی اعداد زوج امتحان کنید.

## راهنمای راه‌حل (چالش)
slice خروجی بسازید؛ اگر `ok(v)` آن را append کنید؛ ظرفیت اولیه اختیاری است.

## خودآزمایی
- [ ] سینتکس `[T any]` را بلدم
- [ ] `comparable` را می‌شناسم
- [ ] zero value جنریک را بلدم
- [ ] می‌دانم کی generic نکنم
- [ ] MapSlice را می‌فهمم
- [ ] تفاوت any و constraint اختصاصی را بلدم

## جمع‌بندی + پل به جلسه بعد
generic ابزار است نه هدف. جلسهٔ بعد goroutine، channel، select و خطر race.
