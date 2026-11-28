// GradleAppTest.java — پاسخ کامل پروژهٔ جلسه ۱۰۹
import org.junit.Test;
import static org.junit.Assert.*;

public class GradleAppTest {

    @Test
    public void testMultiply() {
        assertEquals(20, GradleApp.multiply(4, 5));
        assertEquals(0, GradleApp.multiply(0, 5));
        assertEquals(-6, GradleApp.multiply(-2, 3));
    }

    @Test
    public void testGreet() {
        assertEquals("Hello, World", GradleApp.greet("World"));
        assertEquals("Hello, ", GradleApp.greet(""));
    }
}
