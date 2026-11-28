# جلسه ۵۴ — Text Blocks و Switch Expressions

## اهداف یادگیری
- `text block` (`"""`) را برای multi-line string بفهمید
- `formatted()` و `stripIndent()` را بشناسید
- `switch expression` (`->`) را به‌کار ببرید
- `yield` و `case` با `->` را بفهمید

## پیش‌نیازها
- جلسهٔ ۵۳ (Pattern Matching)

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### Text Blocks — Java 15+

```java
// قبل — string concatenation
String json = "{\n" +
    "  \"name\": \"Ali\",\n" +
    "  \"age\": 20\n" +
    "}";

// text block — تمیزتر
String json = """
    {
      "name": "Ali",
      "age": 20
    }
    """;
```

### Text block features

```java
// interpolation با formatted()
String name = "Ali";
int age = 20;
String msg = """
    Hello %s, you are %d years old
    """.formatted(name, age);

// stripIndent — حذف indentation
String code = """
        public class Main {
            public static void main(String[] args) {
                System.out.println("Hello");
            }
        }
        """.stripIndent();

// translateEscapes — برای escape sequences
String path = """
    C:\\Users\\Ali\\file.txt
    """.translateEscapes();
```

### Switch Expressions — Java 14+

```java
// switch statement قدیمی
String result;
switch (day) {
    case "SATURDAY":
    case "SUNDAY":
        result = "Weekend";
        break;
    default:
        result = "Weekday";
}

// switch expression — Java 14+
String result = switch (day) {
    case "SATURDAY", "SUNDAY" -> "Weekend";
    default -> "Weekday";
};
```

### yield — برای complex expressions

```java
String result = switch (num) {
    case 1 -> "One";
    case 2 -> "Two";
    case 3 -> {
        // block body — باید yield
        String msg = "Three";
        yield msg + " (odd)";
    }
    default -> "Many";
};
```

## کد مهم

`src/TextBlocksSwitchDemo.java` — text blocks، formatted، stripIndent، switch expressions، yield.

## خطاهای رایج
- `"""` را در string معمولی استفاده کردن → syntax error.
- text block بدون newline بعد از `"""` → compile error.
- switch expression بدون `->` → باید arrow یا `yield` داشته باشد.
- `break` در switch expression → استفاده نمی‌شود — `yield` یا `->`.

## جمع‌بندی
- Text blocks برای multi-line string — Java 15+.
- Switch expressions برای concise switch — Java 14+.
- `yield` برای complex cases، `->` برای simple.

**جلسهٔ بعد:** Modules (JPMS).
