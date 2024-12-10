package com.example.mapper;

import com.example.entities.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmployeeMapper {
    List<Employee> selectAll(Employee employee);

    @Select("select * from employee where id = #{id}")
    Employee selectById(Integer id);

    void insert(Employee employee);

    void update(Employee employee);

    @Delete("delete from employee where id = #{id}")
    void deleteById(Integer id);

    @Select("select * from employee where username = #{username}")
    Employee selectByUsername(String username);
}
