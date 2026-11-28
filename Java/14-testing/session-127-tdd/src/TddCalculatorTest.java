// TddCalculatorTest.java — TDD tests (written first, then implementation)
// نیازمند: junit-jupiter-api jar برای کامپایل
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TddCalculatorTest {

    private TddCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new TddCalculator();
    }

    // ---------- RED → GREEN → REFACTOR ----------

    @Test
    @DisplayName("Add two positive numbers")
    void testAddPositive() {
        // Arrange
        int a = 2, b = 3;

        // Act
        int result = calculator.add(a, b);

        // Assert
        assertEquals(5, result);
    }

    @Test
    @DisplayName("Add two negative numbers")
    void testAddNegative() {
        assertEquals(-5, calculator.add(-2, -3));
    }

    @Test
    @DisplayName("Subtract two numbers")
    void testSubtract() {
        assertEquals(1, calculator.subtract(3, 2));
        assertEquals(-5, calculator.subtract(-2, -7));
    }

    @Test
    @DisplayName("Multiply two numbers")
    void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3));
        assertEquals(0, calculator.multiply(0, 5));
    }

    @Test
    @DisplayName("Divide two numbers")
    void testDivide() {
        assertEquals(2, calculator.divide(6, 3));
        assertEquals(0, calculator.divide(5, 10));
    }

    @Test
    @DisplayName("Divide by zero throws exception")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }

    // ---------- Edge cases ----------

    @Test
    @DisplayName("Add zero")
    void testAddZero() {
        assertEquals(5, calculator.add(5, 0));
        assertEquals(0, calculator.add(0, 0));
    }

    @Test
    @DisplayName("Multiply by one")
    void testMultiplyByOne() {
        assertEquals(5, calculator.multiply(5, 1));
    }
}
