# جلسه ۱۲۸ — Integration Tests: تست یکپارچه

## اهداف یادگیری
- `Integration Test` را برای testing multiple components with each other بشناسید
- `@SpringBootTest` برای Spring Boot integration tests استفاده کنید
- `@DataJpaTest`, `@WebMvcTest` برای slice testing استفاده کنید
- `TestRestTemplate`, `MockMvc` برای HTTP testing استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱۲۷ (TDD)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Unit Test vs Integration Test

| Unit Test | Integration Test |
|---|---|
| Single class | Multiple components |
| Mocked dependencies | Real dependencies |
| Fast | Slower |
| Isolated | Full context |

### @SpringBootTest — full context

```java
@SpringBootTest
class UserIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateAndGetUser() {
        // Create user via API
        User user = new User("Ali", "ali@mail.com", 25);
        ResponseEntity<User> response = restTemplate.postForEntity(
            "/api/users", user, User.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Verify in database
        List<User> users = userRepository.findAll();
        assertEquals(1, users.size());
    }
}
```

### @DataJpaTest — JPA slice

```java
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFind() {
        User user = new User("Ali", "ali@mail.com", 25);
        userRepository.save(user);

        List<User> users = userRepository.findAll();
        assertEquals(1, users.size());
        assertEquals("Ali", users.get(0).getName());
    }
}
```

### @WebMvcTest — MVC slice

```java
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(
            new User(1L, "Ali", "ali@mail.com", 25)));

        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Ali"));
    }
}
```

### TestRestTemplate — HTTP testing

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetUser() {
        ResponseEntity<User> response = restTemplate.getForEntity(
            "/api/users/1", User.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
```

## کد مهم

`src/UserIntegrationTest.java` — full integration test.
`src/UserRepositoryTest.java` — JPA slice test.
`src/UserControllerTest.java` — MVC slice test.

## خطاهای رایج
- `@SpringBootTest` را بدون `webEnvironment` استفاده کردن → MOCK environment.
- `@MockBean` را بدون `@WebMvcTest` استفاده کردن → not needed.
- `TestRestTemplate` را بدون `RANDOM_PORT` استفاده کردن → connection refused.
- `@DataJpaTest` را بدون `@AutoConfigureTestDatabase` استفاده کردن → real DB.

## جمع‌بندی
- Integration tests برای testing multiple components.
- `@SpringBootTest` برای full context، `@DataJpaTest`/`@WebMvcTest` برای slices.
- `TestRestTemplate` برای HTTP، `MockMvc` برای MVC.
- `@MockBean` برای mocking in slice tests.

**جلسهٔ بعد:** Testing Capstone.
