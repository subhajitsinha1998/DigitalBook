package com.subhajit2251447.digitalbook.user.dto;

import com.subhajit2251447.digitalbook.user.model.User;

public class JwtResponse {
    
    private UserDto user;
    private String jwtToken;

    public JwtResponse(User user, String jwtToken) {
        this.user = new UserDto(user);
        this.jwtToken = jwtToken;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    
}
