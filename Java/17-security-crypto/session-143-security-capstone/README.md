# جلسه ۱۴۳ — Security Capstone: پروژهٔ امنیتی کامل

## اهداف یادگیری
- همهٔ مباحث security را در یک پروژهٔ واقعی ترکیب کنید
- Hashing + Encryption + JWT + Authentication را با هم استفاده کنید
- Secure password storage و token-based auth را پیاده کنید

## پیش‌نیازها
- جلسات ۱۴۰–۱۴۲

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### پروژه: Secure User Management System

یک سیستم مدیریت کاربران امن با همهٔ security features بسازید:

**Features:**
- `SecurePasswordStorage` — PBKDF2 password hashing
- `JwtUtil` — JWT token generation/validation
- `AuthService` — authentication service
- `UserService` — user management
- `SecureController` — REST API با JWT auth

### Architecture

```
AuthController (login/logout)
    ↓
AuthService (JWT + password verification)
    ↓
UserService (user management)
    ↓
UserRepository (data access)
    ↓
SecurePasswordStorage (PBKDF2 hashing)
```

## کد مهم

`src/SecureUserService.java` — secure user service.
`src/AuthService.java` — authentication service.
`src/SecureController.java` — REST controller.
`src/JwtUtil.java` — JWT utilities.
`src/SecurePasswordStorage.java` — password hashing.

## جمع‌بندی فاز ۱۰ (بخش Security)
- Hashing, Encryption, JWT, Security Capstone ✅
- **بعدی: Microservices & DevOps**
