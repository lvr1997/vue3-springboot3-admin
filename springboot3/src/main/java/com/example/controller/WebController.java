package com.example.controller;

import com.example.common.Result;
import com.example.entities.Employee;
import com.example.entities.request.LoginData;
import com.example.exception.CustomException;
import com.example.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Resource
    private EmployeeService employeeService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/count")
    public String count() {
        throw new CustomException("400", "错误！禁止请求");
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginData loginData) {
        Employee employee = employeeService.selectByUsername(loginData.getUsername());
        if(employee == null) {
            throw new CustomException("400", "用户不存在");
        }
        String password = employee.getPassword();
        if(!password.equals(loginData.getPassword())) {
            throw new CustomException("400", "账号或密码错误");
        }
        return Result.success(employee);
    }

}
