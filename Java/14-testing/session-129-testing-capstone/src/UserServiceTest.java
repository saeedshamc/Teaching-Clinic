// UserServiceTest.java — unit tests با Mockito
// نیازمند: junit-jupiter + mockito-core jars برای کامپایل
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(
            new User(1L, "Ali", "ali@mail.com", 25)));

        List<User> users = userService.getAllUsers();

        assertEquals(1, users.size());
        verify(userRepository).findAll();
    }

    @Test
    void testCreateUserValid() {
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User user = userService.createUser("Ali", "ali@mail.com", 25);

        assertEquals("Ali", user.getName());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testCreateUserBlankName() {
        assertThrows(IllegalArgumentException.class,
            () -> userService.createUser("", "ali@mail.com", 25));
    }

    @Test
    void testCreateUserUnderage() {
        assertThrows(IllegalArgumentException.class,
            () -> userService.createUser("Ali", "ali@mail.com", 15));
    }
}
