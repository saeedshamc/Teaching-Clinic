# تمرین‌های پروژهٔ REST Store Backend

پیش‌نیاز اجرا: `mvn clean package && java -jar target/store-backend-1.0.0.jar`
سپس: `curl` یا Postman.

## آسان
1. برنامه را اجرا کنید و H2 console را در `http://localhost:8080/h2-console` باز کنید.
2. با `POST /api/auth/register` کاربر بسازید و با `/login` توکن بگیرید.
3. `GET /api/products` را با header `Authorization: Bearer <token>` صدا بزنید.

## متوسط
4. با `POST /api/orders` سفارش بسازید — موجودی محصول باید کم شود.
5. با `/api/orders/my` سفارش‌های کاربر را ببینید.
6. یک کاربر دوم بسازید و بررسی کنید سفارش‌های کاربر اول را نبیند.

## سخت
7. یک کاربر ADMIN بسازید (در `UserService.register` نقش را ADMIN کنید) و CRUD محصولات را تست کنید.
8. `PUT /api/orders/{id}/status` را با ADMIN صدا بزنید و وضعیت سفارش را عوض کنید.
9. یک endpoint `/api/admin/stats` اضافه کنید که تعداد سفارش‌ها و درآمد کل را برگرداند.
