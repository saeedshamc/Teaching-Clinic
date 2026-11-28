# پاسخ تمرین‌های جلسه ۱۲۵

## آسان

**۱ و ۲.**
```java
public class Calculator {
    public int add(int a, int b) { return a + b; }
    public int subtract(int a, int b) { return a - b; }
}

class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void setUp() { calculator = new Calculator(); }

    @Test
    void testAdd() { assertEquals(5, calculator.add(2, 3)); }
    @Test
    void testSubtract() { assertEquals(1, calculator.subtract(3, 2)); }
}
```

## متوسط

**۳ و ۴.**
```java
@BeforeEach
void setUp() { calculator = new Calculator(); }

@AfterEach
void tearDown() { calculator = null; }

@Test
void testDivideByZero() {
    assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
}
```

## سخت

**۵ و ۶.**
```java
@ParameterizedTest
@CsvSource({"1, 2, 3", "5, 5, 10", "-1, 1, 0"})
void testAdd(int a, int b, int expected) {
    assertEquals(expected, calculator.add(a, b));
}

@ParameterizedTest
@MethodSource("provideData")
void testWithMethod(int a, int b, int expected) { ... }

static Stream<Arguments> provideData() {
    return Stream.of(Arguments.of(1, 2, 3), Arguments.of(5, 5, 10));
}
```
