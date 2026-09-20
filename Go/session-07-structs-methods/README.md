# جلسه ۰۷: pointer، struct و method (۱۲۰ دقیقه)

**محیط فرض:** Go 1.22+

## پیش‌نیاز
- جلسات ۰۱ تا ۰۶

## اهداف یادگیری
پس از این جلسه می‌توانید:
1. با `&` و `*` آدرس و dereferene را درست استفاده کنید
2. **struct** تعریف کنید و فیلدها را مقداردهی کنید
3. **method** با value receiver و pointer receiver بنویسید و تفاوت را بگویید
4. صفر struct و اشارهٔ `nil` به struct را تشخیص دهید

## چرا این مطلب مهم است
تقریباً همهٔ مدل‌های دامنه در Go با struct هستند. انتخاب غلط receiver باعث می‌شود تغییر روی کپی اعمال شود و state گم شود.

## مفهوم اصلی

```go
type User struct {
	Name string
	Age  int
}

func (u User) Label() string { // value receiver — روی کپی
	return u.Name
}

func (u *User) Birthday() { // pointer receiver — mutate
	u.Age++
}
```

اگر متد state را عوض می‌کند، معمولاً pointer receiver. برای نوع‌های کوچک immutable، value OK است.

## اشتباهات رایج
1. **mutate با value receiver و تعجب از بی‌اثر بودن**
2. **dereferene روی nil pointer** — panic
3. **خروجی گرفتن آدرس متغیر حلقه در نسخه‌های قدیمی ذهنیت** — در Go 1.22+ متغیر حلقه per-iteration است؛ با این حال در بستن روی goroutine همچنان مراقب باشید (جلسه concurrency)

## مثال آموزشی قدم‌به‌قدم

```bash
cd session-07-structs-methods/examples
go run .
```

```go
package main

import "fmt"

type Counter struct{ N int }

func (c *Counter) Inc() { c.N++ }

func main() {
	c := &Counter{}
	c.Inc()
	fmt.Println(c.N)
}
```

## نسخهٔ بهتر / الگوی درست
سازندهٔ ساده: `func NewCounter() *Counter { return &Counter{} }`. فیلدهای export شده با حرف بزرگ؛ داخلی با حرف کوچک.

## تمرین عملی

### آسان
struct `Rect` با `W`, `H` و متد `Area() int`.

### چالش
`Scale(k int)` با pointer receiver که ابعاد را در `k` ضرب کند؛ تست دستی در main.

## راهنمای راه‌حل (چالش)
```go
func (r *Rect) Scale(k int) { r.W *= k; r.H *= k }
```

## خودآزمایی
- [ ] `&` و `*` را بلدم
- [ ] تفاوت دو نوع receiver را می‌گویم
- [ ] nil pointer را بدون چک dereference نمی‌کنم
- [ ] New... برای ساخت بلدم
- [ ] export با حرف بزرگ را رعایت می‌کنم
- [ ] صفر struct را می‌شناسم

## جمع‌بندی + پل به جلسه بعد
با struct رفتار را به داده وصل کردید. جلسهٔ بعد **interface** و طراحی polymorphic عملی.
