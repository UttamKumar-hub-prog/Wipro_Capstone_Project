package com.wipro.AccountService.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class AccountLoggingAspect {

    // Before executing any AccountServiceImpl method
    @Before("execution(* com.wipro.AccountService.services.AccountServiceImpl.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("➡️ Entering AccountService method: {} with arguments: {}",
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    // After successful execution
    @AfterReturning(pointcut = "execution(* com.wipro.AccountService.services.AccountServiceImpl.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("✅ AccountService method executed: {} | Returned: {}",
                joinPoint.getSignature().toShortString(),
                result);
    }

    // If exception occurs
    @AfterThrowing(pointcut = "execution(* com.wipro.AccountService.services.AccountServiceImpl.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("❌ Exception in AccountService method: {} | Message: {}",
                joinPoint.getSignature().toShortString(),
                ex.getMessage(), ex);
    }
}
