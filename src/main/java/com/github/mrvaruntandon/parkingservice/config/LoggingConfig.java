package com.github.mrvaruntandon.parkingservice.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Configuration
@Slf4j
public class LoggingConfig {

    @Pointcut("execution( * com.github.mrvaruntandon.parkingservice.controller.*.* (..) )")
    public void controllerPointcut() {
    }

    @Pointcut("execution( * com.github.mrvaruntandon.parkingservice.service.*.* (..) )")
    public void servicePointcut() {
    }

    @Pointcut("execution( * com.github.mrvaruntandon.parkingservice.dao.*.* (..) )")
    public void daoPointcut() {
    }

    @Pointcut("execution( * com.github.mrvaruntandon.parkingservice.repository.*.* (..) )")
    public void repositoryPointcut() {
    }

    @Pointcut("within(com.github.mrvaruntandon.parkingservice..*)")
    public void exceptionPointcut() {
    }

    @Around("controllerPointcut() || servicePointcut() || daoPointcut() || repositoryPointcut()")
    public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Signature signature = proceedingJoinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();
        log.debug(String.format("Entered method %s.%s()", className, methodName));
        Instant startTime = Instant.now();
        Object response = proceedingJoinPoint.proceed();
        Instant endTime = Instant.now();
        Long timeElapsed = Duration.between(startTime, endTime).toMillis();
        log.debug(String.format("Exited method %s.%s() and time taken = %s ms", className, methodName, timeElapsed));
        return response;
    }

    @AfterThrowing(pointcut = "exceptionPointcut()", throwing = "throwable")
    public void logExceptions(JoinPoint joinPoint, Throwable throwable) {
        Signature signature = joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();
        log.error(String.format("Exception occurred in method %s.%s. Cause: %s. Message: %s", className, methodName,
                null == throwable.getCause() ? "NULL" : throwable.getCause(), null == throwable.getMessage() ?
                        "NULL" : throwable.getMessage()));
    }

}