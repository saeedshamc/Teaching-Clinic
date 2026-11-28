// UserControllerTest.java — MVC slice test
// نیازمند: spring-boot-starter-test + spring-boot-starter-web jars
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("Get all users")
    void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(
            new User(1L, "Ali", "ali@mail.com", 25)));

        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Ali"))
            .andExpect(jsonPath("$[0].email").value("ali@mail.com"));
    }

    @Test
    @DisplayName("Get user by ID")
    void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(
            Optional.of(new User(1L, "Ali", "ali@mail.com", 25)));

        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Ali"));
    }

    @Test
    @DisplayName("Create user")
    void testCreateUser() throws Exception {
        User user = new User("Sara", "sara@mail.com", 30);
        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content("{\"name\":\"Sara\",\"email\":\"sara@mail.com\",\"age\":30}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("Sara"));
    }
}
