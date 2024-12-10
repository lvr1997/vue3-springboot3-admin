package com.example.service;

import cn.hutool.core.util.StrUtil;
import com.example.entities.Employee;
import com.example.exception.CustomException;
import com.example.mapper.EmployeeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;
    public List<Employee> selectAll(Employee employee) {
        return employeeMapper.selectAll(employee);
    }

    public Employee selectById(Integer id) {
        return employeeMapper.selectById(id);
    }

    public PageInfo<Employee> selectByPage(Integer pageNum, Integer pageSize, Employee employee) {
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> list = employeeMapper.selectAll(employee);
        return PageInfo.of(list);
    }

    public void insert(Employee employee) {
        String username = employee.getUsername();
        Employee dbemployee = employeeMapper.selectByUsername(username);
        if (dbemployee != null) {
            throw new CustomException("400", "账户已存在");
        }
        if(StrUtil.isBlank(employee.getPassword())) {
            employee.setPassword("123456");
        }
        employee.setRole("EMP");
        employeeMapper.insert(employee);
    }

    public void update(Employee employee) {
        employeeMapper.update(employee);
    }

    public void deleteById(Integer id) {
        employeeMapper.deleteById(id);
    }

    public Employee selectByUsername(String username) {
        return employeeMapper.selectByUsername(username);
    }
}
