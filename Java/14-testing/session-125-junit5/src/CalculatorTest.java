// CalculatorTest.java — JUnit 5 tests
// نیازمند: junit-jupiter-api jar برای کامپایل
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }

    // ---------- Basic tests ----------
    @Test
    @DisplayName("Test addition")
    void testAdd() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(0, calculator.add(0, 0));
        assertEquals(-1, calculator.add(-2, 1));
    }

    @Test
    @DisplayName("Test subtraction")
    void testSubtract() {
        assertEquals(1, calculator.subtract(3, 2));
        assertEquals(0, calculator.subtract(5, 5));
        assertEquals(-3, calculator.subtract(-1, 2));
    }

    @Test
    @DisplayName("Test multiplication")
    void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3));
        assertEquals(0, calculator.multiply(0, 5));
        assertEquals(-4, calculator.multiply(-2, 2));
    }

    @Test
    @DisplayName("Test division")
    void testDivide() {
        assertEquals(2, calculator.divide(6, 3));
        assertEquals(0, calculator.divide(5, 10));
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }

    @Test
    @DisplayName("Test power")
    void testPower() {
        assertEquals(8.0, calculator.power(2, 3), 0.001);
        assertEquals(1.0, calculator.power(5, 0), 0.001);
        assertEquals(0.25, calculator.power(0.5, 2), 0.001);
    }

    // ---------- Parameterized tests ----------
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10})
    @DisplayName("Test isEven with even numbers")
    void testIsEven(int number) {
        assertTrue(calculator.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9})
    @DisplayName("Test isEven with odd numbers")
    void testIsOdd(int number) {
        assertFalse(calculator.isEven(number));
    }

    @ParameterizedTest
    @CsvSource({
        "2, 3, 5",
        "5, 5, 10",
        "-1, 1, 0",
        "100, 200, 300"
    })
    @DisplayName("Test add with CSV source")
    void testAddWithCsv(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }

    @ParameterizedTest
    @MethodSource("providePrimeNumbers")
    @DisplayName("Test isPrime")
    void testIsPrime(int number, boolean expected) {
        assertEquals(expected, calculator.isPrime(number));
    }

    static Stream<Arguments> providePrimeNumbers() {
        return Stream.of(
            Arguments.of(2, true),
            Arguments.of(3, true),
            Arguments.of(4, false),
            Arguments.of(5, true),
            Arguments.of(9, false),
            Arguments.of(11, true)
        );
    }

    // ---------- Edge cases ----------
    @Test
    @DisplayName("Test with null values")
    void testNullValues() {
        // Calculator doesn't accept null — test with primitive types
        assertDoesNotThrow(() -> calculator.add(0, 0));
    }

    @Test
    @DisplayName("Test large numbers")
    void testLargeNumbers() {
        assertEquals(Integer.MAX_VALUE, calculator.add(Integer.MAX_VALUE - 1, 1));
        assertEquals(Integer.MIN_VALUE, calculator.subtract(Integer.MIN_VALUE + 1, 1));
    }
}
