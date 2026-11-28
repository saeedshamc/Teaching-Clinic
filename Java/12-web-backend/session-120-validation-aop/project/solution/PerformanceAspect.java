// PerformanceAspect.java — پاسخ کامل پروژهٔ جلسه ۱۲۰
// نیازمند: spring-aop + aspectjweaver jars برای کامپایل
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

    @Around("execution(* com.example.service.*.*(..))")
    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;
            System.out.println("[PERF] " + joinPoint.getSignature().getName() +
                " took " + duration + "ms");
            return result;
        } catch (Throwable t) {
            long duration = System.currentTimeMillis() - start;
            System.out.println("[PERF] " + joinPoint.getSignature().getName() +
                " failed after " + duration + "ms");
            throw t;
        }
    }

    @AfterThrowing(
        pointcut = "execution(* com.example.service.*.*(..))",
        throwing = "ex"
    )
    public void logException(org.aspectj.lang.JoinPoint joinPoint, Exception ex) {
        System.out.println("[ERROR] " + joinPoint.getSignature().getName() +
            ": " + ex.getMessage());
    }
}
