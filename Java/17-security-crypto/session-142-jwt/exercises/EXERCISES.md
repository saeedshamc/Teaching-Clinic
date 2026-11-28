# تمرین‌های جلسه ۱۴۲ — JWT

## آسان
1. `JwtUtil` با `generateToken`, `extractUsername`, `validateToken` بسازید.
2. `JwtToken` با `isExpired` برای token model بسازید.

## متوسط
3. `AuthController` با `login` و `getCurrentUser` برای authentication بسازید.
4. `Bearer` token در `Authorization` header استفاده کنید.

## سخت
5. `Claims` برای custom data (role, userId) استفاده کنید.
6. `JwtAuthenticationFilter` برای request filtering استفاده کنید.
