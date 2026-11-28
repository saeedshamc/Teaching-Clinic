# REST Store Backend — پروژهٔ واقعی بک‌اند فروشگاه

بک‌اند کامل فروشگاه با **Spring Boot 3 + JPA + JWT + WebSocket + Docker**.
این یک پروژهٔ Maven واقعی است — با `mvn spring-boot:run` اجرا می‌شود.

## اجرا

```bash
mvn clean package
java -jar target/store-backend-1.0.0.jar
# یا
mvn spring-boot:run
```

Docker:
```bash
docker build -t store-backend .
docker run -p 8080:8080 store-backend
```

## ساختار

```
pom.xml                          # Maven — Spring Boot 3, JPA, JWT, WebSocket
src/main/java/com/store/
├── StoreApplication.java        # نقطهٔ ورود
├── config/                      # SecurityConfig, WebSocketConfig, JpaConfig
├── entity/                      # Product, User, Order, OrderItem
├── repository/                  # Spring Data JPA repositories
├── service/                     # ProductService, OrderService, UserService
├── controller/                  # REST controllers
├── security/                    # JwtUtil, JwtFilter
├── dto/                         # request/response records
└── websocket/                   # order notifications
src/main/resources/
├── application.yml              # تنظیمات + H2 در حافظه
└── data.sql                     # دادهٔ نمونه
```

## APIها

| Method | Path | توضیح |
|---|---|---|
| POST | `/api/auth/register` | ثبت‌نام |
| POST | `/api/auth/login` | ورود → JWT |
| GET | `/api/products` | لیست محصولات (paging) |
| GET | `/api/products/{id}` | جزئیات محصول |
| POST | `/api/orders` | ثبت سفارش (نیازمند JWT) |
| GET | `/api/orders/my` | سفارش‌های من |
| WS | `/ws/orders` | نوتیفیکیشن real-time |

## تمرین‌ها و پروژه
`exercises/`, `solutions/`, `project/` — طبق ساختار استاندارد.
