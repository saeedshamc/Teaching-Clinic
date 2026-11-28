// LoggingAspect.java — AOP aspect برای logging
// نیازمند: spring-aop + aspectjweaver jars برای کامپایل
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // ---------- Before ----------
    @Before("execution(* com.example.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[BEFORE] " + joinPoint.getSignature().getName());
    }

    // ---------- After ----------
    @After("execution(* com.example.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[AFTER] " + joinPoint.getSignature().getName());
    }

    // ---------- Around — measure execution time ----------
    @Around("execution(* com.example.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;
            System.out.println("[AROUND] " + joinPoint.getSignature().getName() +
                " took " + duration + "ms");
            return result;
        } catch (Throwable t) {
            long duration = System.currentTimeMillis() - start;
            System.out.println("[AROUND] " + joinPoint.getSignature().getName() +
                " failed after " + duration + "ms: " + t.getMessage());
            throw t;
        }
    }

    // ---------- After Throwing ----------
    @AfterThrowing(
        pointcut = "execution(* com.example.service.*.*(..))",
        throwing = "ex"
    )
    public void logException(JoinPoint joinPoint, Exception ex) {
        System.out.println("[EXCEPTION] " + joinPoint.getSignature().getName() +
            ": " + ex.getMessage());
    }
}
