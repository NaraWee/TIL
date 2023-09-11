package com.aurora.springaop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Component
@Aspect
public class TimeLoggingAspect {

    Logger logger = LoggerFactory.getLogger(TimeLoggingAspect.class);

    @Around("@annotation(com.aurora.springaop.annotation.TimeLog)")
    public Object TimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

        Object time = joinPoint.proceed();

        logger.info(formatter.format(dateTime));

        return time;

    }
}
