// UserRepositoryTest.java — JPA slice test
// نیازمند: spring-boot-starter-test + spring-boot-starter-data-jpa jars
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Save and find user")
    void testSaveAndFind() {
        User user = new User("Ali", "ali@mail.com", 25);
        userRepository.save(user);

        List<User> users = userRepository.findAll();
        assertEquals(1, users.size());
        assertEquals("Ali", users.get(0).getName());
    }

    @Test
    @DisplayName("Find by email")
    void testFindByEmail() {
        User user = new User("Sara", "sara@mail.com", 30);
        userRepository.save(user);

        User found = userRepository.findByEmail("sara@mail.com").orElse(null);
        assertNotNull(found);
        assertEquals("Sara", found.getName());
    }

    @Test
    @DisplayName("Find adults")
    void testFindAdults() {
        userRepository.save(new User("Ali", "ali@mail.com", 25));
        userRepository.save(new User("Child", "child@mail.com", 10));

        List<User> adults = userRepository.findByAgeGreaterThan(18);
        assertEquals(1, adults.size());
        assertEquals("Ali", adults.get(0).getName());
    }
}
