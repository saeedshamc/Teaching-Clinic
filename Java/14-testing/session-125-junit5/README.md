# جلسه ۱۲۵ — JUnit 5: Unit Testing

## اهداف یادگیری
- `JUnit 5` را برای unit testing بشناسید
- `@Test`, `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll` را بفهمید
- `Assertions` (`assertEquals`, `assertTrue`, `assertThrows`) را به‌کار ببرید
- `@ParameterizedTest`, `@CsvSource`, `@MethodSource` را برای parameterized tests استفاده کنید

## پیش‌نیازها
- فازهای ۱–۸ دورهٔ جاوا

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### JUnit 5 — مدرن‌ترین framework تست

```java
class CalculatorTest {

    Calculator calculator;

    @BeforeEach   // قبل از هر test
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testAdd() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }

    @AfterEach    // بعد از هر test
    void tearDown() {
        calculator = null;
    }
}
```

### Annotations

| Annotation | Usage |
|---|---|
| `@Test` | Test method |
| `@BeforeEach` | Before each test |
| `@AfterEach` | After each test |
| `@BeforeAll` | Once before all tests (static) |
| `@AfterAll` | Once after all tests (static) |
| `@Disabled` | Skip test |
| `@DisplayName` | Custom test name |

### Assertions

```java
assertEquals(expected, actual);
assertEquals(expected, actual, "message");
assertTrue(condition);
assertFalse(condition);
assertNull(object);
assertNotNull(object);
assertSame(expected, actual);
assertThrows(Exception.class, () -> code());
assertDoesNotThrow(() -> code());
assertArrayEquals(expected, actual);
assertIterableEquals(expected, actual);
```

### Parameterized Tests

```java
@ParameterizedTest
@ValueSource(ints = {1, 2, 3, 4, 5})
void testIsPositive(int number) {
    assertTrue(number > 0);
}

@ParameterizedTest
@CsvSource({"1, 2, 3", "5, 5, 10", "-1, 1, 0"})
void testAdd(int a, int b, int expected) {
    assertEquals(expected, calculator.add(a, b));
}

@ParameterizedTest
@MethodSource("provideTestData")
void testWithMethodSource(int a, int b, int expected) {
    assertEquals(expected, calculator.add(a, b));
}

static Stream<Arguments> provideTestData() {
    return Stream.of(
        Arguments.of(1, 2, 3),
        Arguments.of(5, 5, 10),
        Arguments.of(-1, 1, 0)
    );
}
```

## کد مهم

`src/Calculator.java` — class under test.
`src/CalculatorTest.java` — JUnit 5 tests.

## خطاهای رایج
- `@BeforeAll` را بدون `static` استفاده کردن → `TestInstancePostProcessorException`.
- `assertEquals` را با wrong argument order استفاده کردن → confusing failures.
- `@ParameterizedTest` را بدون `@ValueSource`/`@CsvSource` استفاده کردن → no parameters.
- `assertThrows` را بدون lambda استفاده کردن → no exception thrown.

## جمع‌بندی
- JUnit 5 برای unit testing — `@Test`, `@BeforeEach`, `@AfterEach`.
- `Assertions` برای verification — `assertEquals`, `assertTrue`, `assertThrows`.
- `@ParameterizedTest` برای multiple inputs.
- `@DisplayName` برای readable test names.

**جلسهٔ بعد:** Mockito — mocking dependencies.
