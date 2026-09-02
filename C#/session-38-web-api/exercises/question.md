# تمرین‌های جلسه ۳۸ — Web API

## کلاسی (۳۰ دقیقه)

1. `dotnet new webapi -n ProductApi`
2. CRUD endpoints برای Product
3. DTO برای Create و Response

## خانه (۴۵ دقیقه)

4. Controller-based API با `ProductsController`
5. Swagger UI — test همه endpoints
6. DI: `IProductService` + register در Program.cs
7. curl یا Postman test

## Endpoints

```
GET    /api/products
GET    /api/products/{id}
POST   /api/products
PUT    /api/products/{id}
DELETE /api/products/{id}
```

## Test

```powershell
curl http://localhost:5000/api/products
curl -X POST http://localhost:5000/api/products -H "Content-Type: application/json" -d "{\"name\":\"Test\",\"price\":100}"
```

پاسخ نمونه: [exercise_01.cs](./exercise_01.cs)
