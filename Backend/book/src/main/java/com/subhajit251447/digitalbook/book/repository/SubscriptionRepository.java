package com.subhajit251447.digitalbook.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subhajit251447.digitalbook.book.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    public List<Subscription> findByUserId(Integer userId);
    public Boolean existsByIdAndUserId(Integer id, Integer userId);
    public Subscription findByIdAndUserId(Integer id, Integer userId);
    
}
