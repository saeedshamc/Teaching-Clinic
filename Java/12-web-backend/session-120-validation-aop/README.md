# جلسه ۱۲۰ — Validation & AOP

## اهداف یادگیری
- Bean Validation (`@Valid`, `@NotNull`, `@Size`) را بفهمید
- `@Validated` برای method-level validation استفاده کنید
- `AspectJ` و `@Aspect` برای AOP را یاد بگیرید
- `@Before`, `@After`, `@Around`, `@AfterThrowing` advice را به‌کار ببرید

## پیش‌نیازها
- جلسهٔ ۱۱۹ (Spring Security)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Bean Validation — @Valid

```java
public class User {
    @NotNull(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be 2-50 chars")
    private String name;

    @Email(message = "Invalid email")
    @NotNull
    private String email;

    @Min(value = 18, message = "Age must be 18+")
    @Max(value = 120)
    private int age;
}
```

```java
@PostMapping("/users")
public ResponseEntity<User> create(@Valid @RequestBody User user) {
    // validation automatic — throws MethodArgumentNotValidException if invalid
    return ResponseEntity.status(201).body(userService.create(user));
}
```

### Custom Validation

```java
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface ValidAge {
    String message() default "Invalid age";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

public class AgeValidator implements ConstraintValidator<ValidAge, Integer> {
    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext context) {
        return age != null && age >= 18 && age <= 120;
    }
}
```

### AOP — Aspect-Oriented Programming

```java
@Aspect
@Component
public class LoggingAspect {

    // Before method execution
    @Before("execution(* com.example.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before: " + joinPoint.getSignature().getName());
    }

    // After method execution
    @After("execution(* com.example.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After: " + joinPoint.getSignature().getName());
    }

    // Around method execution — can modify result
    @Around("execution(* com.example.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature().getName() + " took " + duration + "ms");
        return result;
    }

    // After throwing exception
    @AfterThrowing(pointcut = "execution(* com.example.service.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        System.out.println("Exception in " + joinPoint.getSignature().getName() + ": " + ex.getMessage());
    }
}
```

### Pointcut Expressions

```java
// By package
execution(* com.example.service.*.*(..))

// By method name
execution(* *.*(..))

// By annotation
@annotation(org.springframework.web.bind.annotation.GetMapping)

// By bean name
execution(* userService.*(..))
```

## کد مهم

`src/User.java` — validation annotations.
`src/LoggingAspect.java` — AOP aspect.
`src/UserController.java` — controller with validation.

## خطاهای رایج
- `@Valid` را بدون `@RequestBody` استفاده کردن → validation not triggered.
- `@Aspect` را بدون `@Component` استفاده کردن → aspect not detected.
- `proceed()` را بدون try-catch استفاده کردن → exceptions propagate.
- Pointcut را بدون `execution` استفاده کردن → `AspectJException`.

## جمع‌بندی
- Bean Validation برای input validation — `@Valid`, `@NotNull`, `@Size`.
- AOP برای cross-cutting concerns — logging, security, transactions.
- `@Aspect` + `@Before`/`@After`/`@Around`/`@AfterThrowing`.
- Pointcut expressions برای method selection.

**جلسهٔ بعد:** Actuator & Profiles.
