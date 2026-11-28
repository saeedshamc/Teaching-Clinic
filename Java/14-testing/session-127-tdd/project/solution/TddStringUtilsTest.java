// TddStringUtilsTest.java — پاسخ کامل پروژهٔ جلسه ۱۲۷
// نیازمند: junit-jupiter-api jar برای کامپایل
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TddStringUtilsTest {

    @Test
    void testReverse() {
        assertEquals("cba", TddStringUtils.reverse("abc"));
        assertEquals("", TddStringUtils.reverse(""));
        assertNull(TddStringUtils.reverse(null));
    }

    @Test
    void testCapitalize() {
        assertEquals("Hello", TddStringUtils.capitalize("hello"));
        assertEquals("", TddStringUtils.capitalize(""));
        assertNull(TddStringUtils.capitalize(null));
    }

    @Test
    void testIsPalindrome() {
        assertTrue(TddStringUtils.isPalindrome("racecar"));
        assertTrue(TddStringUtils.isPalindrome("A man, a plan, a canal: Panama"));
        assertFalse(TddStringUtils.isPalindrome("hello"));
        assertFalse(TddStringUtils.isPalindrome(null));
    }

    @Test
    void testCountVowels() {
        assertEquals(3, TddStringUtils.countVowels("hello"));
        assertEquals(0, TddStringUtils.countVowels("xyz"));
        assertEquals(0, TddStringUtils.countVowels(null));
    }

    @Test
    void testRemoveDuplicates() {
        assertEquals("abc", TddStringUtils.removeDuplicates("aabbcc"));
        assertEquals("", TddStringUtils.removeDuplicates(""));
        assertNull(TddStringUtils.removeDuplicates(null));
    }
}
