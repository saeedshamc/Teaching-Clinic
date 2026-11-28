// UserControllerTest.java — MVC slice test
// نیازمند: spring-boot-starter-test jar برای کامپایل
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.*;
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
    void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(
            new User(1L, "Ali", "ali@mail.com", 25)));

        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Ali"));
    }

    @Test
    void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(
            Optional.of(new User(1L, "Ali", "ali@mail.com", 25)));

        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Ali"));
    }
}
