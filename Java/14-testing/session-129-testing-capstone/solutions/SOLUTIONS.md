# پاسخ تمرین‌های جلسه ۱۲۹

## آسان

**۱ و ۲.**
```java
@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }
    public List<User> getAllUsers() { return userRepository.findAll(); }
}

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock UserRepository userRepository;
    @InjectMocks UserService userService;

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        assertEquals(1, userService.getAllUsers().size());
    }
}
```

## متوسط

**۳ و ۴.**
```java
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired MockMvc mockMvc;
    @MockBean UserService userService;

    @Test
    void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(user));
        mockMvc.perform(get("/api/users")).andExpect(status().isOk());
    }
}

@DataJpaTest
class UserRepositoryTest {
    @Autowired UserRepository userRepository;

    @Test
    void testSave() { userRepository.save(user); assertEquals(1, userRepository.count()); }
}
```

## سخت

**۵ و ۶.**
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserIntegrationTest {
    @Autowired TestRestTemplate restTemplate;

    @Test
    void testCreateUser() {
        ResponseEntity<User> response = restTemplate.postForEntity(
            "/api/users", user, User.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
```
