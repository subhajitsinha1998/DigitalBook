package com.subhajit2251447.digitalbook.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.subhajit2251447.digitalbook.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    Boolean existsByEmail(String email);
    User findByEmail(String email);
    
}
