package com.example.controller;

import com.example.common.Result;
import com.example.entities.Employee;
import com.example.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @GetMapping("/selectAll")
    public Result selectAll(@RequestBody Employee employee) {
        List<Employee> list = employeeService.selectAll(employee);
        return Result.success(list);
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Employee employee = employeeService.selectById(id);
        return Result.success(employee);
    }

    @PostMapping("/selectByPage")
    public Result selectById(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10")Integer pageSize,
                             @RequestBody Employee employee) {
        PageInfo<Employee> list = employeeService.selectByPage(pageNum, pageSize, employee);
        return Result.success(list);
    }

    @PostMapping("/add")
    public Result insert(@RequestBody Employee employee) {
        employeeService.insert(employee);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Employee employee) {
        employeeService.update(employee);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        employeeService.deleteById(id);
        return Result.success();
    }

}
