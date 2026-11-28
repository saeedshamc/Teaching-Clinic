# جلسه ۱۵۰ — REST Backend Capstone: پروژهٔ نهایی بک‌اند

## اهداف یادگیری
- همهٔ مباحث Backend را در یک پروژهٔ واقعی نهایی ترکیب کنید
- Spring Boot + JPA + Security + WebSocket + Validation + AOP + Actuator را با هم استفاده کنید
- لایه‌بندی صحیح و best practices را در نظر بگیرید

## پیش‌نیازها
- جلسات ۱۱۱–۱۴۹

## مدت زمان پیشنهادی
۱۵۰ دقیقه

## درس

### پروژه: Complete E-Commerce Backend

یک بک‌اند فروشگاه کامل با همهٔ features بسازید:

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
- AOP برای logging و performance

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
                                    │
                            JWT Security
                                    │
                            Actuator (monitoring)
```

## کد مهم

`src/Product.java` — JPA entity.
`src/ProductRepository.java` — Spring Data repository.
`src/ProductService.java` — service layer.
`src/ProductController.java` — REST controller.
`src/SecurityConfig.java` — JWT security.
`src/JwtUtil.java` — JWT utilities.
`src/WebSocketConfig.java` — WebSocket config.
`src/LoggingAspect.java` — AOP logging.

## جمع‌بندی فاز ۱۰ — Security + DevOps + Capstone

| جلسه | موضوع |
|---|---|
| ۱۴۰ | Hashing & Passwords |
| ۱۴۱ | Encryption |
| ۱۴۲ | JWT |
| ۱۴۳ | Security Capstone |
| ۱۴۴ | Docker for Java |
| ۱۴۵ | CI/CD |
| ۱۴۶ | Message Queues |
| ۱۴۷ | Microservices Intro |
| ۱۴۸ | Desktop App Capstone |
| ۱۴۹ | Android App Capstone |
| ۱۵۰ | REST Backend Capstone |

**فاز ۱۰ کامل شد — ۱۱ جلسه ✅**
**دورهٔ ۱۵۰ جلسه‌ای کامل شد! 🎉**
