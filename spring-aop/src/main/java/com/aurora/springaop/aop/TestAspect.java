package com.aurora.springaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAspect {

    /**
     * 컨트롤 포인트 컷
     */
    @Pointcut("execution(* com.aurora..controller.AopTestController.*(..))")
    public void pointCutTargetController() {
    }

    @Before("pointCutTargetController()")
    public void BeforeAop(JoinPoint joinPoint) {
        System.out.println("=========================");
        System.out.println("BeforeAop signature : " + joinPoint.getSignature());
        System.out.println("=========================");
    }

    @After("pointCutTargetController()")
    public void AfterAop(JoinPoint joinPoint) {
        System.out.println("=========================");
        System.out.println("AfterAop signature : " + joinPoint.getSignature());
        System.out.println("=========================");
    }
}
