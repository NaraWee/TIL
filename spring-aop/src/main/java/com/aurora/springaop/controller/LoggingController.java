package com.aurora.springaop.controller;

import com.aurora.springaop.annotation.TimeLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {

    @GetMapping("/time/logging")
    @TimeLog
    public void timeLogging() {
        System.out.println("TimeLog 호출");
    }

    @GetMapping("/no/time/logging")
    public void noTimeLogging() {
        System.out.println("TimeLog 미호출");
    }
}
