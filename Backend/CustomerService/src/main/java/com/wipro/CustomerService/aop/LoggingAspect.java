package com.wipro.CustomerService.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    // Log before every method in CustomerServiceImpl
    @Before("execution(* com.wipro.CustomerService.services.CustomerServiceImpl.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("➡️ Entering method: {} with arguments: {}",
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    // Log after successful method execution
    @AfterReturning(pointcut = "execution(* com.wipro.CustomerService.services.CustomerServiceImpl.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("✅ Method executed: {} | Returned: {}", 
                joinPoint.getSignature().toShortString(), 
                result);
    }

    // Log exceptions
    @AfterThrowing(pointcut = "execution(* com.wipro.CustomerService.services.CustomerServiceImpl.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("❌ Exception in method: {} | Message: {}", 
                joinPoint.getSignature().toShortString(), 
                ex.getMessage(), ex);
    }
}
