// UserServiceTest.java — Mockito tests
// نیازمند: junit-jupiter + mockito-core jars برای کامپایل
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User(1L, "Ali", "ali@mail.com", 25);
    }

    // ---------- Basic mocking ----------
    @Test
    @DisplayName("Get user by ID")
    void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        Optional<User> result = userService.getUserById(1L);

        assertTrue(result.isPresent());
        assertEquals("Ali", result.get().getName());
        verify(userRepository).findById(1L);
    }

    @Test
    @DisplayName("Get user by ID — not found")
    void testGetUserByIdNotFound() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(999L);

        assertTrue(result.isEmpty());
        verify(userRepository).findById(999L);
    }

    // ---------- Create user ----------
    @Test
    @DisplayName("Create user")
    void testCreateUser() {
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setId(1L);
            return u;
        });

        User result = userService.createUser("Sara", "sara@mail.com", 30);

        assertNotNull(result.getId());
        assertEquals("Sara", result.getName());
        verify(userRepository).save(any(User.class));
    }

    // ---------- Delete user ----------
    @Test
    @DisplayName("Delete user")
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository).deleteById(1L);
    }

    // ---------- Search ----------
    @Test
    @DisplayName("Search users by name")
    void testSearchUsers() {
        List<User> users = List.of(testUser, new User(2L, "Ali Reza", "reza@mail.com", 30));
        when(userRepository.findByName("Ali")).thenReturn(users);

        List<User> result = userService.searchUsers("Ali");

        assertEquals(2, result.size());
        verify(userRepository).findByName("Ali");
    }

    // ---------- Argument matchers ----------
    @Test
    @DisplayName("Get adults with argument matcher")
    void testGetAdults() {
        List<User> adults = List.of(testUser);
        when(userRepository.findByAgeGreaterThan(anyInt())).thenReturn(adults);

        List<User> result = userService.getAdults(18);

        assertEquals(1, result.size());
        verify(userRepository).findByAgeGreaterThan(18);
    }

    // ---------- Verify interactions ----------
    @Test
    @DisplayName("Verify multiple calls")
    void testVerifyMultipleCalls() {
        when(userRepository.findAll()).thenReturn(List.of(testUser));

        userService.getAllUsers();
        userService.getAllUsers();

        verify(userRepository, times(2)).findAll();
    }

    // ---------- Exception handling ----------
    @Test
    @DisplayName("Handle repository exception")
    void testRepositoryException() {
        when(userRepository.findById(anyLong()))
            .thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> userService.getUserById(1L));
    }
}
