# پروژهٔ جلسه ۱۰۴ — کلاینت REST API

یک کلاینت REST API بسازید که:
- `HttpClient` برای HTTP requests
- `getUsers`, `getUser`, `createUser`, `updateUser`, `deleteUser`
- `HttpRequest` با headers و body
- `sendAsync` برای async operations
- `JsonParser` برای JSON parsing (ساده)

نمونه:
```
=== REST Client ===
GET /users → 200 OK
POST /users → 201 Created
GET /users/1 → 200 OK
DELETE /users/1 → 204 No Content
```

## صورت پروژه
پاسخ در `solution/` — RestClient.java + ApiModels.java.
