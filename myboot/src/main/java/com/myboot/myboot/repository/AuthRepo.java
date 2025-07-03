package com.myboot.myboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myboot.myboot.entity.AuthEntity;

@Repository
public interface AuthRepo extends JpaRepository<AuthEntity,Integer> {
  
    AuthEntity getByUsername(String username);
}
