# جلسه ۰۸: interface و طراحی polymorphic عملی (۱۲۰ دقیقه)

**محیط فرض:** Go 1.22+

## پیش‌نیاز
- جلسات ۰۱ تا ۰۷ (به‌ویژه struct و method)

## اهداف یادگیری
پس از این جلسه می‌توانید:
1. یک **interface** کوچک با چند متد تعریف کنید
2. بگویید ارضای interface در Go ضمنی (**implicit**) است
3. وابستگی را به interface تزریق کنید نه به نوع concrete
4. خطر **typed nil** داخل interface را تشخیص دهید

## چرا این مطلب مهم است
interface مرز تست‌پذیری و جایگزینی پیاده‌سازی است. اگر همه‌جا concrete بگیرید، mock و تعویض Email/SMS سخت می‌شود.

## مفهوم اصلی
Interface فقط مجموعهٔ متدهاست. هر نوعی که همان متدها را داشته باشد، بدون `implements` صریح، آن interface را ارضا می‌کند.

```go
type Notifier interface {
	Send(message string) error
}
```

Consumer باید `Notifier` بگیرد؛ Producer نوع واقعی (`EmailNotifier`) را هنگام ساخت تزریق می‌کند.

### typed nil
متغیر concrete از نوع pointer که `nil` است، وقتی داخل interface گذاشته شود، خودِ interface لزوماً `== nil` نیست. قبل از استفاده چک نوع/ساخت درست مهم است.

## اشتباهات رایج
1. **interfaceهای خدا با ده‌ها متد** — کوچک نگه دارید (ایدهٔ ISP).
2. **برگرداندن concrete از سازنده وقتی فقط interface لازم است** — انعطاف کم می‌شود.
3. **چک `if err != nil` روی interface حاوی typed nil** و گیج شدن

## مثال آموزشی قدم‌به‌قدم

```bash
cd session-08-interfaces/examples
go run .
```

```go
package main

import "fmt"

type Notifier interface {
	Send(message string) error
}

type EmailNotifier struct{}

func (EmailNotifier) Send(message string) error {
	fmt.Println("email:", message)
	return nil
}

type OrderService struct {
	notifier Notifier
}

func NewOrderService(n Notifier) *OrderService {
	return &OrderService{notifier: n}
}

func (s *OrderService) Place(id string) error {
	return s.notifier.Send("order " + id + " ثبت شد")
}

func main() {
	_ = NewOrderService(EmailNotifier{}).Place("A-42")
}
```

## نسخهٔ بهتر / الگوی درست
«Accept interfaces, return structs» را با احتیاط به‌کار ببرید: ورودی تابع‌ها interface کوچک؛ خروجی سازنده‌ها اغلب concrete مفید است تا API واضح بماند.

## تمرین عملی

### آسان
`SmsNotifier` با همان `Send` بسازید و به `NewOrderService` بدهید.

### چالش
`MemoryNotifier` که پیام‌ها را در slice داخل struct ذخیره کند؛ بعد از `Place` طول slice را چاپ کنید.

## راهنمای راه‌حل (چالش)
فیلد `Messages []string`؛ در `Send` با `append` ذخیره کنید؛ receiver اشاره‌گر باشد تا mutate بماند.

## خودآزمایی
- [ ] ارضای ضمنی را بلدم
- [ ] تزریق از طریق سازنده بلدم
- [ ] interface کوچک می‌سازم
- [ ] typed nil را شنیده‌ام و مراقبم
- [ ] concrete را بی‌دلیل در همه جا hard-code نمی‌کنم
- [ ] می‌توانم پیاده‌سازی دوم جایگزین کنم

## جمع‌بندی + پل به جلسه بعد
حالا polymorphic تمیز دارید. جلسهٔ بعد مدیریت خطا به سبک Go: wrapping، sentinel و تصمیم‌گیری.
