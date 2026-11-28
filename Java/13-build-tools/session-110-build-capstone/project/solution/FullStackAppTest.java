// FullStackAppTest.java — پاسخ کامل capstone
import org.junit.Test;
import static org.junit.Assert.*;

public class FullStackAppTest {

    @Test
    public void testUserModel() {
        User user = new User(1, "Ali", "ali@mail.com", 25);
        assertEquals("Ali", user.getName());
        assertEquals(25, user.getAge());
    }

    @Test
    public void testUserService() {
        UserService service = new UserService();
        User user = service.create("Sara", "sara@mail.com", 30);
        assertNotNull(user.getId());
        assertEquals(1, user.getId());

        assertTrue(service.findById(1).isPresent());
        assertEquals(1, service.findAll().size());

        service.delete(1);
        assertTrue(service.findAll().isEmpty());
    }
}
