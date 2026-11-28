# پاسخ تمرین‌های جلسه ۱۲۷

## آسان

**۱ و ۲.**
```java
// Step 1: RED — write test first
@Test
void testAdd() {
    assertEquals(5, calculator.add(2, 3));
}

// Step 2: GREEN — minimal implementation
public int add(int a, int b) { return a + b; }
```

## متوسط

**۳ و ۴.**
```java
@Test
void testDivideByZero() {
    assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
}

public int divide(int a, int b) {
    if (b == 0) throw new ArithmeticException("Division by zero");
    return a / b;
}
```

## سخت

**۵ و ۶.**
```java
// TDD cycle for StringUtils
@Test
void testReverse() { assertEquals("cba", StringUtils.reverse("abc")); }

public static String reverse(String input) {
    if (input == null) return null;
    return new StringBuilder(input).reverse().toString();
}
```
