# پروژهٔ جلسه ۱۱۵ — سیستم مدیریت کاربران با Spring MVC

یک سیستم مدیریت کاربران با Spring MVC بسازید که:
- `UserController` با `@Controller` برای views
- `UserApiController` با `@RestController` برای REST API
- `users.html` و `user-form.html` با Thymeleaf
- `@GetMapping`, `@PostMapping`, `@PathVariable`, `@ModelAttribute`
- `Model` برای view data
- `redirect:` برای navigation

نمونه:
```
GET /users → users.html
GET /users/new → user-form.html
POST /users → create → redirect to /users
GET /api/users → JSON list
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
