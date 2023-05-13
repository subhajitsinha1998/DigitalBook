package com.subhajit2251447.digitalbook.user.service;

import com.subhajit2251447.digitalbook.user.Exception.CustomException;
import com.subhajit2251447.digitalbook.user.dto.JwtResponse;
import com.subhajit2251447.digitalbook.user.dto.UserDto;
import com.subhajit2251447.digitalbook.user.model.User;

public interface UserService {
    
    public UserDto getUser(int id);
    public void userExists(Integer id);
    public JwtResponse createUser(User user) throws CustomException;
    public boolean checkEmail(String email);

}
