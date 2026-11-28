# پروژهٔ توسعه — نسخهٔ کامل Store Backend

پروژهٔ پایه را به این موارد گسترش دهید:

## الزامات
- [ ] **سبد خرید (Cart):** entity + endpoints — `POST /api/cart/items`، `GET /api/cart`، `DELETE /api/cart/{itemId}`، و `POST /api/cart/checkout` که سفارش می‌سازد
- [ ] **آدرس کاربر:** entity `Address` با رابطهٔ OneToMany به User
- [ ] **کوپن تخفیف:** entity `Coupon` با درصد/مبلغ ثابت + اعتبارسنجی در checkout
- [ ] **صفحه‌بندی محصولات با فیلتر:** `?category=&minPrice=&maxPrice=&sort=`
- [ ] **Rate limiting ساده:** AOP aspect که تعداد درخواست هر IP را محدود کند
- [ ] **لاگ‌گیری:** `logging.level.com.store=DEBUG` + aspect برای زمان هر request
- [ ] **docker-compose.yml:** app + Postgres واقعی (به‌جای H2)

## معیار پذیرش
- همهٔ endpointها در README مستند شده باشند
- تست‌های `@WebMvcTest` یا `@SpringBootTest` برای Auth و Product حداقل
- `mvn test` بدون خطا

## صورت پروژه
پاسخ در `solution/` — پیاده‌سازی کامل این الزامات.
