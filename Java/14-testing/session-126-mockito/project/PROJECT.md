# پروژهٔ جلسه ۱۲۶ — تست واحد با Mockito

یک پروژهٔ تست واحد با Mockito بسازید:
- `UserRepository` interface برای mocking
- `UserService` class under test
- `UserServiceTest` با `@Mock`, `@InjectMocks`
- `when().thenReturn()` برای stub
- `verify()` برای interactions
- `ArgumentMatchers` برای flexible matching
- `doReturn`/`doThrow` برای void methods

نمونه:
```
UserServiceTest > testGetUserById PASSED
UserServiceTest > testCreateUser PASSED
UserServiceTest > testDeleteUser PASSED
Tests run: 10, Failures: 0, Errors: 0
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
