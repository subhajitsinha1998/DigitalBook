package com.subhajit2251447.digitalbook.user.dto;

import com.subhajit2251447.digitalbook.user.model.User;

public class UserDto {
    private Integer userId;
    private String fullName;
    private String email;
    private String role;

    public UserDto(User user) {
        this.userId = user.getId();
        this.fullName = user.getFullname();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
    
}
