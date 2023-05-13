package com.subhajit2251447.digitalbook.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.subhajit2251447.digitalbook.user.config.CustomUserDetails;
import com.subhajit2251447.digitalbook.user.dto.JwtRequest;
import com.subhajit2251447.digitalbook.user.dto.JwtResponse;
import com.subhajit2251447.digitalbook.user.model.User;
import com.subhajit2251447.digitalbook.user.repository.UserRepository;
import com.subhajit2251447.digitalbook.user.util.JwtUtil;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createToken(JwtRequest jwtRequest) {
        String username = jwtRequest.getEmail();
        String password = jwtRequest.getPassword();
        authenticate(username, password);
        final UserDetails userDetails = loadUserByUsername(username);
        String newToken = jwtUtil.generateToken(userDetails);
        User user = userRepository.findByEmail(username);
        return new JwtResponse(user, newToken);
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(!userRepository.existsByEmail(email)){
            throw new UsernameNotFoundException("User Not Registered");
        }
        return new CustomUserDetails(userRepository.findByEmail(email));
    }
    
    
}
