package com.subhajit2251447.digitalbook.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.subhajit2251447.digitalbook.user.Exception.CustomException;
import com.subhajit2251447.digitalbook.user.dto.JwtRequest;
import com.subhajit2251447.digitalbook.user.dto.JwtResponse;
import com.subhajit2251447.digitalbook.user.dto.UserDto;
import com.subhajit2251447.digitalbook.user.model.User;
import com.subhajit2251447.digitalbook.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public JwtResponse createUser(User user) throws CustomException {
        if (this.checkEmail(user.getEmail())) {
            throw new CustomException("username already exists");
        }
        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setEmail(user.getEmail());
        jwtRequest.setPassword(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return jwtService.createToken(jwtRequest);
    }

    @Override
    public void userExists(Integer id) {
        if (!userRepository.existsById(id)) throw new UsernameNotFoundException("User Not Registered");
    }

    @Override
    public UserDto getUser(int id) {
        userExists(id);
        return new UserDto(userRepository.getReferenceById(id));
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
}
