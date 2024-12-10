package com.example.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Employee {
    private Integer id;
    private String username;
    private String password;
    private String role;
    private String no;
    private String name;
    private String sex;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String description;
    private Integer departmentId;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNo(){
        return no;
    }

    public void setNo(String no){
        this.no=no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex(){
        return sex;
    }

    public void setSex(String sex){
        this.sex=sex;
    }

    public Date getBirthday(){
        return birthday;
    }

    public void setBirthday(Date birthday){
        this.birthday=birthday;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public Integer getDepartmentId(){
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId){
        this.departmentId=departmentId;
    }
}
