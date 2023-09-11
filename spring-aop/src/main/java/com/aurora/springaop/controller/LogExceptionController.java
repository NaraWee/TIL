package com.aurora.springaop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogExceptionController {

    @GetMapping("/afterreturning/test")
    public void afterReturningTest() throws Exception {
        int num1 = 10;
        int num2 = 5;
        int result = 0;

        try {
            result = num1 / num2;
            System.out.println("result = " + result);
        } catch (Exception e) {
            System.out.println("에러발생");
            throw new Exception(e);
        }
    }

    @GetMapping("/afterthrowing/test")
    public void afterThrowingTest() throws Exception {
        int num1 = 10;
        int num2 = 0;
        int result = 0;

        try {
            result = num1 / num2;
            System.out.println("result = " + result);
        } catch (Exception e) {
            System.out.println("에러발생");
            throw new Exception(e);
        }
    }

}
