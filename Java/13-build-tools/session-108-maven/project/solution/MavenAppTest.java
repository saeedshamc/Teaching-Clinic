// MavenAppTest.java — پاسخ کامل پروژهٔ جلسه ۱۰۸
import org.junit.Test;
import static org.junit.Assert.*;

public class MavenAppTest {

    @Test
    public void testAdd() {
        assertEquals(30, MavenApp.add(10, 20));
        assertEquals(0, MavenApp.add(0, 0));
        assertEquals(-5, MavenApp.add(-10, 5));
    }

    @Test
    public void testGreet() {
        assertEquals("Hello, Ali", MavenApp.greet("Ali"));
        assertEquals("Hello, ", MavenApp.greet(""));
    }
}
