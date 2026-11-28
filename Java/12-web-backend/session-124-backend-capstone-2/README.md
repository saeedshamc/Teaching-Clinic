# جلسه ۱۲۴ — Backend Capstone 2: پروژهٔ نهایی — سیستم فروشگاه

## اهداف یادگیری
- همهٔ مباحث Backend را در یک پروژهٔ واقعی ترکیب کنید
- REST API + JPA + Security + WebSocket + Validation + AOP را integrate کنید
- لایه‌بندی صحیح و best practices را رعایت کنید

## پیش‌نیازها
- جلسات ۱۱۱–۱۲۳

## مدت زمان پیشنهادی
۱۲۰ دقیقه

## درس

### پروژه: E-Commerce Backend

یک بک‌اند فروشگاه کامل با Spring Boot بسازید:

**Entities:**
- `User` — id, name, email, password, role
- `Product` — id, name, price, stock, category
- `Order` — id, userId, productId, quantity, totalPrice, status
- `Cart` — id, userId, items

**Features:**
- User registration/login با JWT
- Product CRUD با pagination و filtering
- Order management با transaction
- Cart operations (add/remove items)
- Real-time notifications با WebSocket
- Role-based access (USER, ADMIN)
- Validation و error handling
- Actuator برای monitoring

### Architecture

```
UserController ──┐
ProductController ──┼──> Service Layer ──> Repository Layer ──> Database
OrderController ──┤         │
CartController ───┘         ├── UserService
                            ├── ProductService
                            ├── OrderService
                            └── CartService
                                    │
                            WebSocket (notifications)
```

### pom.xml — Full Dependencies

```xml
<dependencies>
    <dependency>spring-boot-starter-web</dependency>
    <dependency>spring-boot-starter-data-jpa</dependency>
    <dependency>spring-boot-starter-security</dependency>
    <dependency>spring-boot-starter-validation</dependency>
    <dependency>spring-boot-starter-actuator</dependency>
    <dependency>spring-boot-starter-websocket</dependency>
    <dependency>com.h2database:h2</dependency>
    <dependency>io.jsonwebtoken:jjwt-api:0.12.3</dependency>
</dependencies>
```

## کد مهم

`src/Product.java` — JPA entity.
`src/ProductRepository.java` — Spring Data repository.
`src/ProductService.java` — service layer.
`src/ProductController.java` — REST controller.
`src/SecurityConfig.java` — JWT security.
`src/JwtUtil.java` — JWT utilities.
`src/WebSocketConfig.java` — WebSocket config.

## جمع‌بندی فاز ۸ — Web Backend

| جلسه | موضوع |
|---|---|
| ۱۱۱ | Servlets |
| ۱۱۲ | JSP |
| ۱۱۳ | Jakarta EE |
| ۱۱۴ | Spring Core |
| ۱۱۵ | Spring MVC |
| ۱۱۶ | Spring Boot |
| ۱۱۷ | REST Controllers |
| ۱۱۸ | Spring Data JPA |
| ۱۱۹ | Spring Security |
| ۱۲۰ | Validation & AOP |
| ۱۲۱ | Actuator & Profiles |
| ۱۲۲ | Backend WebSocket |
| ۱۲۳ | Backend Capstone 1 |
| ۱۲۴ | Backend Capstone 2 |

**فاز ۸ کامل شد — ۱۴ جلسه ✅**
