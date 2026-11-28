# پروژهٔ جلسه ۱۲۵ — تست واحد برای Calculator

یک پروژهٔ تست واحد با JUnit 5 بسازید:
- `Calculator` با `add`, `subtract`, `multiply`, `divide`, `power`, `isEven`, `isPrime`
- `CalculatorTest` با `@Test`, `@ParameterizedTest`, `@BeforeEach`, `@AfterEach`
- `assertEquals`, `assertTrue`, `assertFalse`, `assertThrows`
- `@CsvSource`, `@MethodSource`, `@ValueSource`
- `@DisplayName` برای readable test names

نمونه:
```
CalculatorTest > testAdd PASSED
CalculatorTest > testAddWithCsv(1, 2, 3) PASSED
CalculatorTest > testIsPrime(5, true) PASSED
Tests run: 15, Failures: 0, Errors: 0
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
