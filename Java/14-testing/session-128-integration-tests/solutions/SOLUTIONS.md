# پاسخ تمرین‌های جلسه ۱۲۸

## آسان

**۱ و ۲.**
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateUser() {
        ResponseEntity<User> response = restTemplate.postForEntity(
            "/api/users", user, User.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
```

## متوسط

**۳ و ۴.**
```java
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testSave() {
        userRepository.save(new User("Ali", "ali@mail.com", 25));
        assertEquals(1, userRepository.count());
    }
}

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
}
```

## سخت

**۵ و ۶.**
```java
mockMvc.perform(get("/api/users"))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$[0].name").value("Ali"));

@MockBean
private UserService userService;
```
