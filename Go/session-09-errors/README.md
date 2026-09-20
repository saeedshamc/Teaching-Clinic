# جلسه ۰۹: مدیریت خطا به سبک Go (۱۲۰ دقیقه)

**محیط فرض:** Go 1.22+

## پیش‌نیاز
- جلسات ۰۱ تا ۰۸

## اهداف یادگیری
پس از این جلسه می‌توانید:
1. خطا را با `fmt.Errorf` و `%w` بپیچید (**wrap**)
2. با `errors.Is` و `errors.As` خطای sentinel / نوع‌دار را تشخیص دهید
3. بگویید چه وقت خطا را برگردانید، لاگ کنید یا به کاربر ترجمه کنید
4. از panic برای خطای معمولی برنامه استفاده نکنید

## چرا این مطلب مهم است
در سرویس واقعی زنجیرهٔ فراخوانی بدون context خطا به «something failed» تبدیل می‌شود. Wrapping درست، دیباگ و شاخه‌بندی منطق را ممکن می‌کند.

## مفهوم اصلی

```go
var ErrNotFound = errors.New("not found") // sentinel

func Find(id string) error {
	return fmt.Errorf("user %s: %w", id, ErrNotFound)
}

if errors.Is(err, ErrNotFound) { ... }
```

- **برگردان به بالا:** وقتی لایهٔ فعلی تصمیم ندارد
- **لاگ + برگردان:** مرز سرویس/handler
- **ترجمه به پیام کاربر:** فقط در لبهٔ UI/API
- **panic:** برای باگ غیرقابل ادامه (نه اعتبارسنجی ورودی)

## اشتباهات رایج
1. **`fmt.Errorf("... %v", err)` به‌جای `%w`** — زنجیره برای `errors.Is` قطع می‌شود
2. **بلعیدن خطا (`return nil` بعد از لاگ)** بدون تصمیم آگاهانه
3. **panic روی ورودی بد کاربر**

## مثال آموزشی قدم‌به‌قدم

```bash
cd session-09-errors/examples
go run .
```

```go
package main

import (
	"errors"
	"fmt"
)

var ErrNotFound = errors.New("not found")

func Find(id string) error {
	return fmt.Errorf("user %s: %w", id, ErrNotFound)
}

func main() {
	err := Find("u-1")
	if errors.Is(err, ErrNotFound) {
		fmt.Println("پیدا نشد:", err)
	}
}
```

## نسخهٔ بهتر / الگوی درست
پیام wrap باید عملیات و شناسه را بگوید؛ sentinel را در پکیج export کنید اگر بخشی از API است.

## تمرین عملی

### آسان
تابعی بنویسید که اگر رشته خالی بود `ErrEmpty` برگرداند.

### چالش
دو لایه wrap کنید (`repo` سپس `service`) و با `errors.Is` همان sentinel را از بیرونی‌ترین خطا تشخیص دهید.

## راهنمای راه‌حل (چالش)
هر لایه `fmt.Errorf("service: %w", err)`؛ در `main` فقط `errors.Is`.

## خودآزمایی
- [ ] `%w` را بلدم
- [ ] `errors.Is` بلدم
- [ ] تفاوت sentinel و error نوع‌دار را می‌دانم
- [ ] panic را برای ورودی کاربر استفاده نمی‌کنم
- [ ] می‌دانم کجا لاگ کنم
- [ ] پیام wrap را معنادار می‌نویسم

## جمع‌بندی + پل به جلسه بعد
خطا الان قابل ردیابی است. جلسهٔ بعد **generics** کاربردی امروزی.
