# پاسخ تمرین‌های جلسه ۱۲۶

## آسان

**۱ و ۲.**
```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock UserRepository userRepository;
    @InjectMocks UserService userService;

    @Test
    void testGetUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> result = userService.getUserById(1L);
        assertTrue(result.isPresent());
    }
}
```

## متوسط

**۳ و ۴.**
```java
verify(userRepository).findById(1L);
verify(userRepository, times(2)).save(any());

doReturn(user).when(userRepository).findById(1L);
doThrow(new RuntimeException()).when(userRepository).save(any());
```

## سخت

**۵ و ۶.**
```java
when(userRepository.findByName(anyString())).thenReturn(users);
when(userRepository.findByAgeBetween(anyInt(), anyInt())).thenReturn(users);

@Spy
UserService userService = new UserService(userRepository);
doReturn(mockedList).when(userService).getAllUsers();
```
