package com.example.controller;

import com.example.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/count")
    public String count() {
        throw new CustomException("400", "错误！禁止请求");
    }

}
