package com.myboot.myboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myboot.myboot.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
   
}
