// AdvancedCalculatorTest.java — پاسخ کامل پروژهٔ جلسه ۱۲۵
// نیازمند: junit-jupiter-api jar برای کامپایل
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class AdvancedCalculatorTest {

    private AdvancedCalculator calculator;

    @BeforeEach
    void setUp() { calculator = new AdvancedCalculator(); }

    @AfterEach
    void tearDown() { calculator = null; }

    @Test
    void testAdd() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(0, calculator.add(0, 0));
        assertEquals(-1, calculator.add(-2, 1));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }

    @ParameterizedTest
    @CsvSource({"1, 2, 3", "5, 5, 10", "-1, 1, 0"})
    void testAddWithCsv(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10})
    void testIsEven(int number) {
        assertTrue(calculator.isEven(number));
    }

    @ParameterizedTest
    @MethodSource("provideFactorialData")
    void testFactorial(int n, int expected) {
        assertEquals(expected, calculator.factorial(n));
    }

    static Stream<Arguments> provideFactorialData() {
        return Stream.of(
            Arguments.of(0, 1), Arguments.of(1, 1),
            Arguments.of(5, 120), Arguments.of(10, 3628800)
        );
    }

    @Test
    void testGcd() {
        assertEquals(6, calculator.gcd(12, 18));
        assertEquals(1, calculator.gcd(7, 13));
        assertEquals(5, calculator.gcd(25, 15));
    }
}
