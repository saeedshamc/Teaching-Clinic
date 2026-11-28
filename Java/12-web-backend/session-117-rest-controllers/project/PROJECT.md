# پروژهٔ جلسه ۱۱۷ — REST API کامل با Spring Boot

یک REST API کامل بسازید که:
- `UserApiController` با `@RestController` برای CRUD
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`
- `@RequestBody` برای JSON input
- `@PathVariable` برای path params
- `@RequestParam` برای query params
- `ResponseEntity` برای HTTP status codes
- `GlobalExceptionHandler` برای error handling

نمونه:
```
GET /api/users → 200 [users]
GET /api/users/1 → 200 {user}
POST /api/users → 201 {user}
PUT /api/users/1 → 200 {user}
DELETE /api/users/1 → 204
GET /api/users/search?name=Ali → 200 [matching users]
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
