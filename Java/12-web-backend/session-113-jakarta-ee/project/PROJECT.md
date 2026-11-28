# پروژهٔ جلسه ۱۱۳ — REST API با Jakarta EE

یک REST API با Jakarta EE بسازید که:
- `UserResource` با `@Path("/users")` برای CRUD
- `UserService` با `@ApplicationScoped` برای business logic
- `@Inject` برای CDI
- `@GET`, `@POST`, `@PUT`, `@DELETE` برای endpoints
- `@PathParam` برای path parameters
- `Response` برای HTTP status codes

نمونه:
```
GET /users → 200 OK [users]
POST /users → 201 Created {user}
GET /users/1 → 200 OK {user}
DELETE /users/1 → 204 No Content
```

## صورت پروژه
پاسخ در `solution/` — UserResource.java + UserService.java + pom.xml.
