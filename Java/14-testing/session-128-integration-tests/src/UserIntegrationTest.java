// UserIntegrationTest.java — integration test برای User API
// نیازمند: spring-boot-starter-test jar برای کامپایل
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Create user via API")
    void testCreateUser() {
        User user = new User("Ali", "ali@mail.com", 25);
        ResponseEntity<User> response = restTemplate.postForEntity(
            "/api/users", user, User.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Ali", response.getBody().getName());
    }

    @Test
    @DisplayName("Get all users")
    void testGetAllUsers() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(
            "/api/users", User[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Get user by ID")
    void testGetUserById() {
        // First create a user
        User user = new User("Sara", "sara@mail.com", 30);
        restTemplate.postForEntity("/api/users", user, User.class);

        // Then get by ID
        ResponseEntity<User> response = restTemplate.getForEntity(
            "/api/users/1", User.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Delete user")
    void testDeleteUser() {
        User user = new User("Reza", "reza@mail.com", 28);
        restTemplate.postForEntity("/api/users", user, User.class);

        restTemplate.delete("/api/users/1");

        ResponseEntity<User> response = restTemplate.getForEntity(
            "/api/users/1", User.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
