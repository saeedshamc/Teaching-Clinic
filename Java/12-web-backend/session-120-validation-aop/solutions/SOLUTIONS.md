# پاسخ تمرین‌های جلسه ۱۲۰

## آسان

**۱ و ۲.**
```java
public class User {
    @NotNull @Size(min=2, max=50)
    private String name;

    @Email @NotNull
    private String email;
}

@PostMapping
public ResponseEntity<User> create(@Valid @RequestBody User user) { ... }
```

## متوسط

**۳ و ۴.**
```java
@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.example.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before: " + joinPoint.getSignature().getName());
    }

    @Around("execution(* com.example.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        System.out.println("Took " + (System.currentTimeMillis() - start) + "ms");
        return result;
    }
}
```

## سخت

**۵ و ۶.**
```java
@AfterThrowing(pointcut = "execution(* com.example.service.*.*(..))", throwing = "ex")
public void logException(JoinPoint joinPoint, Exception ex) {
    System.out.println("Exception: " + ex.getMessage());
}

// Custom validation
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface ValidAge { String message() default "Invalid age"; }
```
