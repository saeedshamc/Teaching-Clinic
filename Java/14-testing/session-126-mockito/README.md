# جلسه ۱۲۶ — Mockito: Mocking Dependencies

## اهداف یادگیری
- `Mockito` را برای mocking dependencies بشناسید
- `@Mock`, `@InjectMocks`, `when`, `verify` را بفهمید
- `doReturn`, `doThrow`, `ArgumentMatchers` را به‌کار ببرید
- Spy و ArgumentCaptor را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۱۲۵ (JUnit 5)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Mockito چیست؟

`Mockito` — mocking framework برای unit testing. Dependencies را mock می‌کند تا unit را isolated تست کنید.

### Basic Mocking

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void testGetUserById() {
        // Arrange
        User user = new User(1L, "Ali", "ali@mail.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.getUserById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Ali", result.get().getName());
        verify(userRepository).findById(1L);
    }
}
```

### when().thenReturn() — stub behavior

```java
when(userRepository.findById(1L)).thenReturn(Optional.of(user));
when(userRepository.findById(999L)).thenReturn(Optional.empty());
when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
    User u = invocation.getArgument(0);
    u.setId(1L);
    return u;
});
```

### verify() — verify interactions

```java
verify(userRepository).findById(1L);                    // called once
verify(userRepository, times(2)).save(any());           // called twice
verify(userRepository, never()).deleteById(any());      // never called
verify(userRepository, atLeastOnce()).findAll();        // at least once
```

### doReturn / doThrow — for void methods

```java
doReturn(user).when(userRepository).findById(1L);
doThrow(new RuntimeException("DB error")).when(userRepository).save(any());
doNothing().when(userRepository).deleteById(1L);
```

### ArgumentMatchers

```java
when(userRepository.findByName(anyString())).thenReturn(users);
when(userRepository.findByAgeBetween(anyInt(), anyInt())).thenReturn(users);
when(userRepository.findByEmail(argThat(email -> email.contains("@")))).thenReturn(user);
```

### Spy — partial mocking

```java
@Spy
UserService userService = new UserService(userRepository);

@Test
void testRealMethod() {
    // calls real method
    userService.getAllUsers();
}

@Test
void testStubbedMethod() {
    doReturn(mockedList).when(userService).getAllUsers();
    // now getAllUsers() returns mockedList
}
```

## کد مهم

`src/UserRepository.java` — interface to mock.
`src/UserService.java` — class under test.
`src/UserServiceTest.java` — Mockito tests.

## خطاهای رایج
- `when()` را برای void methods استفاده کردن → `UnfinishedStubbingException` — از `doReturn` استفاده کنید.
- `any()` را بدون type specification استفاده کردن → `ClassCastException`.
- `verify()` را بدون `times()` برای multiple calls استفاده کردن → `TooManyActualInvocations`.
- `@InjectMocks` را بدون `@Mock` برای dependencies استفاده کردن → NPE.

## جمع‌بندی
- Mockito برای mocking dependencies — `@Mock`, `@InjectMocks`.
- `when().thenReturn()` برای stub، `verify()` برای interactions.
- `doReturn`/`doThrow` برای void methods.
- `ArgumentMatchers` برای flexible matching.

**جلسهٔ بعد:** TDD — Test-Driven Development.
