package com.aurora.springaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogExceptionAspect {

    Logger logger = LoggerFactory.getLogger(LogExceptionAspect.class);

    /**
     * 컨트롤 포인트 컷
     */
    @Pointcut("execution(* com.aurora..controller.*Controller.*(..))")
    public void pointCutTargetController() {
    }


    @AfterReturning(pointcut = "pointCutTargetController()")
    public void afterReturning(JoinPoint joinPoint) {
        String arguments = Arrays.toString(joinPoint.getArgs());
        logger.info("afterReturning signature:" + joinPoint.getSignature().toString());
        logger.info("afterReturning arguments:" + arguments);
    }


    @AfterThrowing(pointcut = "pointCutTargetController()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        String arguments = Arrays.toString(joinPoint.getArgs());
        logger.info("afterThrowing Exception signature:" + joinPoint.getSignature().toString());
        logger.info("afterThrowing Exception arguments:" + arguments);
        logger.info("afterThrowing Exception Exception:", e);
    }
}
