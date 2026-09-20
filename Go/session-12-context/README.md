# جلسه ۱۲: context برای لغو، مهلت و مقدار درخواست (۹۰ دقیقه)

**محیط فرض:** Go 1.22+

## پیش‌نیاز
- جلسات ۰۱ تا ۱۱ (به‌ویژه concurrency و errors)

## اهداف یادگیری
پس از این جلسه می‌توانید:
1. با `context.WithCancel` و `WithTimeout` کار کنید
2. `ctx.Done()` را در `select` چک کنید
3. بگویید context را معمولاً اولین پارامتر توابع API می‌گذارند
4. مقادیر درخواست را با احتیاط در context حمل کنید (نه برای وابستگی‌های اجباری)

## چرا این مطلب مهم است
بدون context، درخواست HTTP لغوشده هنوز goroutine و کوئری مصرف می‌کند. در سرور واقعی، timeout و cancel اجباری‌اند.

## مفهوم اصلی

```go
ctx, cancel := context.WithTimeout(context.Background(), 2*time.Second)
defer cancel()

select {
case <-time.After(5 * time.Second):
    // کار طولانی
case <-ctx.Done():
    return ctx.Err() // context.DeadlineExceeded یا Canceled
}
```

قرارداد: `func Do(ctx context.Context, ...) error` — همیشه `ctx` را پایین بدهید.

## اشتباهات رایج
1. **فراموش `defer cancel()`** — نشت منبع تا deadline والدین
2. **ذخیره کردن struct بزرگ در context به‌جای پارامتر صریح**
3. **نادیده گرفتن `ctx.Err()` بعد از خروج از select**

## مثال آموزشی قدم‌به‌قدم

```bash
cd session-12-context/examples
go run .
```

```go
package main

import (
	"context"
	"fmt"
	"time"
)

func Work(ctx context.Context) error {
	select {
	case <-time.After(3 * time.Second):
		fmt.Println("تمام")
		return nil
	case <-ctx.Done():
		return ctx.Err()
	}
}

func main() {
	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()
	if err := Work(ctx); err != nil {
		fmt.Println("لغو/مهلت:", err)
	}
}
```

## نسخهٔ بهتر / الگوی درست
`context.Background()` فقط در main/آغاز؛ در کتابخانه از همان ctx ورودی استفاده کنید. برای دادهٔ اختیاری درخواست (request id) از `WithValue` با کلید تایپ‌شدهٔ خصوصی.

## تمرین عملی

### آسان
timeout را به ۵۰۰ms تغییر دهید و پیام خطا را مشاهده کنید.

### چالش
دو مرحلهٔ متوالی `Work` بنویسید که هر دو همان ctx را بگیرند؛ با timeout کوتاه هر دو محترمانه متوقف شوند.

## راهنمای راه‌حل (چالش)
بعد از خطای اول return کنید؛ مرحلهٔ دوم را صدا نزنید، یا هر دو را با چک `ctx.Done()` در ابتدا محافظت کنید.

## خودآزمایی
- [ ] WithTimeout بلدم
- [ ] defer cancel بلدم
- [ ] ctx اولین پارامتر است
- [ ] Done را در select می‌گذارم
- [ ] Background را وسط کتابخانه نمی‌سازم
- [ ] WithValue را برای DI اجباری سوءاستفاده نمی‌کنم

## جمع‌بندی + پل به جلسه بعد
لغو و مهلت را دارید. جلسهٔ بعد testing، table-driven tests و benchmark مقدماتی.
