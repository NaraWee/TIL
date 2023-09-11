package com.aurora.springaop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopTestController {

    @GetMapping("/aop/test")
    public void aopTest() {
        System.out.println("=== AOP Test Method ===");
    }
}
