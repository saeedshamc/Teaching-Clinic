# پروژهٔ جلسه ۱۲۸ — تست یکپارچه برای User API

یک پروژهٔ integration test بسازید:
- `@SpringBootTest` با `RANDOM_PORT` برای full context
- `TestRestTemplate` برای HTTP testing
- `UserRepository` برای database verification
- `UserIntegrationTest` با CRUD tests
- `@DataJpaTest` برای repository slice
- `@WebMvcTest` برای controller slice
- `MockMvc` برای MVC testing

نمونه:
```
UserIntegrationTest > testCreateUser PASSED
UserIntegrationTest > testGetAllUsers PASSED
UserRepositoryTest > testSaveAndFind PASSED
UserControllerTest > testGetAllUsers PASSED
Tests run: 10, Failures: 0, Errors: 0
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
