package com.myboot.myboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myboot.myboot.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    // custom method using property
   UserEntity[] findAllByAge(int age);

   // custom query
   @Query(value = "SELECT * FROM USERS WHERE name =:name AND age =:age", nativeQuery = true)
   UserEntity[] findByNameAndAge(String name , int age);
}
