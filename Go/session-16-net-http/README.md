# جلسه ۱۶: net/http — کلاینت و سرور مینیمال (۱۲۰ دقیقه)

**محیط فرض:** Go 1.22+ (ServeMux الگوهای جدید متد/مسیر در 1.22)

## پیش‌نیاز
- جلسات ۰۱ تا ۱۵

## اهداف یادگیری
پس از این جلسه می‌توانید:
1. یک سرور HTTP مینیمال با `http.ListenAndServe` بالا بیاورید
2. handler با امضای `ServeHTTP` / `HandlerFunc` بنویسید
3. با `http.Get` یک درخواست ساده بزنید
4. وضعیت و هدر را درست تنظیم کنید

## چرا این مطلب مهم است
بسیاری از سرویس‌های Go در نهایت HTTP هستند. الگوی استاندارد بدون فریم‌ورک سنگین، پایهٔ همهٔ فریم‌ورک‌هاست.

## مفهوم اصلی

```go
http.HandleFunc("GET /health", func(w http.ResponseWriter, r *http.Request) {
	w.WriteHeader(http.StatusOK)
	_, _ = w.Write([]byte("ok"))
})
log.Fatal(http.ListenAndServe(":8080", nil))
```

در Go 1.22 می‌توانید متد را در الگوی مسیر بگذارید. برای سازگاری آموزشی گسترده‌تر، چک متد دستی هم رایج است.

## اشتباهات رایج
1. **نادیده گرفتن خطای `ListenAndServe`**
2. **ننوشتن Content-Type برای JSON**
3. **بلاک کردن handler با کار سنگین بدون context**

## مثال آموزشی قدم‌به‌قدم

```bash
cd session-16-net-http/examples
go run .
# در ترمینال دیگر:
# curl http://127.0.0.1:8080/health
```

سرور را با Ctrl+C متوقف کنید.

## نسخهٔ بهتر / الگوی درست
`http.Server` با `ReadHeaderTimeout` بسازید؛ در تولید از mux و middleware استفاده کنید (جلسه API).

## تمرین عملی

### آسان
مسیر `/ping` که `pong` برمی‌گرداند اضافه کنید.

### چالش
مسیر `/hello?name=Sara` که JSON `{"message":"سلام، Sara"}` برمی‌گرداند.

## راهنمای راه‌حل (چالش)
`r.URL.Query().Get("name")`؛ اگر خالی بود پیش‌فرض؛ `json.NewEncoder(w).Encode(...)` و هدر `application/json`.

## خودآزمایی
- [ ] HandleFunc بلدم
- [ ] WriteHeader بلدم
- [ ] Query را می‌خوانم
- [ ] ListenAndServe را می‌فهمم
- [ ] کلاینت Get ساده بلدم
- [ ] Content-Type JSON را می‌گذارم

## جمع‌بندی + پل به جلسه بعد
HTTP خام را دیدید. جلسهٔ بعد یک **HTTP API آموزشی کوچک** با چند endpoint منسجم.
