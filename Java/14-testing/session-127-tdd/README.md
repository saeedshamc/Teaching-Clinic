# جلسه ۱۲۷ — TDD: Test-Driven Development

## اهداف یادگیری
- `TDD` cycle (Red-Green-Refactor) را بفهمید
- `failing test` را قبل از implementation بنویسید
- `minimal implementation` برای pass کردن test بنویسید
- `refactor` برای improve code بدون تغییر behavior استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱۲۶ (Mockito)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### TDD Cycle — Red, Green, Refactor

```
۱. RED    → Write a failing test
۲. GREEN  → Write minimal code to pass the test
۳. REFACTOR → Improve code without changing behavior
۴. Repeat
```

### Step 1 — RED: Failing Test

```java
@Test
void testAddTwoNumbers() {
    Calculator calculator = new Calculator();
    assertEquals(5, calculator.add(2, 3));   // FAILS — add() doesn't exist yet
}
```

### Step 2 — GREEN: Minimal Implementation

```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;   // minimal code to pass test
    }
}
```

### Step 3 — REFACTOR: Improve

```java
public class Calculator {
    // Refactor — add more methods, improve readability
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }
}
```

### TDD Example — StringUtils

```java
// Step 1: RED — write test
@Test
void testReverse() {
    assertEquals("cba", StringUtils.reverse("abc"));
}

// Step 2: GREEN — minimal implementation
public class StringUtils {
    public static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}

// Step 3: REFACTOR — add more methods
public class StringUtils {
    public static String reverse(String input) {
        if (input == null) return null;
        return new StringBuilder(input).reverse().toString();
    }

    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
```

### TDD Best Practices

- **Test behavior, not implementation** — test what the code does, not how.
- **One test at a time** — write one test, make it pass, then next.
- **Small steps** — minimal code to pass, then refactor.
- **Descriptive names** — `testAddTwoNumbers`, `testDivideByZero`.
- **Arrange-Act-Assert** — structure tests clearly.

## کد مهم

`src/TddCalculator.java` — TDD example.
`src/TddCalculatorTest.java` — tests first, then implementation.

## خطاهای رایج
- Implementation را قبل از test نوشتن → not TDD.
- Test را بدون `assert` استفاده کردن → meaningless test.
- `minimal implementation` را skip کردن → over-engineering.
- `refactor` را بدون test coverage استفاده کردن → breaking changes.

## جمع‌بندی
- TDD برای quality code — Red, Green, Refactor.
- Test را قبل از implementation بنویسید.
- Minimal code برای pass کردن، سپس refactor.
- Arrange-Act-Assert برای test structure.

**جلسهٔ بعد:** Integration Tests.
