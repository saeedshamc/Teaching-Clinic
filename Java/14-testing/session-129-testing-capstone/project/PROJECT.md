# پروژهٔ جلسه ۱۲۹ — پروژهٔ تست کامل برای User Management

یک پروژهٔ تست کامل بسازید:
- [ ] `UserService` با business logic + validation
- [ ] `UserServiceTest` با Mockito (unit tests)
- [ ] `UserControllerTest` با `@WebMvcTest` (MVC slice)
- [ ] `UserRepositoryTest` با `@DataJpaTest` (JPA slice)
- [ ] `UserIntegrationTest` با `@SpringBootTest` (full stack)
- [ ] `TestRestTemplate` برای HTTP testing
- [ ] `MockMvc` برای MVC testing
- [ ] `assertThrows` برای exception testing

نمونه:
```
UserServiceTest > testGetAllUsers PASSED
UserServiceTest > testCreateUserValid PASSED
UserControllerTest > testGetAllUsers PASSED
UserRepositoryTest > testSaveAndFind PASSED
UserIntegrationTest > testCreateUser PASSED
Tests run: 20, Failures: 0, Errors: 0
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
