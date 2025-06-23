package com.myboot.myboot.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myboot.myboot.entity.UserEntity;
import com.myboot.myboot.exception.UserNotFoundException;
import com.myboot.myboot.models.UserModel;
import com.myboot.myboot.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/user")
public class UserController {
    
    // @GetMapping
    // public String getUsers(){
    //     return "<h1 style='color:red;'> Hello User!</h1>";
    // }
    
    // @GetMapping
    // public List<UserModel> getUserDataList(){
    //     return Arrays
    //     .asList(
    //         new UserModel(1L,"name",20),
    //         new UserModel(2L, "name2", 10)
    //     );
    // }

    // user repo for db operations
    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public List<UserEntity> getUserDataList(){
        return userRepo.findAll();
    }

    // post data
    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user){
          return userRepo.save(user);
    }

    // get user by id
    @GetMapping("/{id}")
    public UserEntity getUserbyId(@PathVariable Long id){
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found for id: "+id));
    }

    // update user
    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user){
        UserEntity userData = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found for id: "+id));
        userData.setName(user.getName());
        userData.setAge(user.getAge());
        return userRepo.save(userData);
    }

    // delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
         UserEntity userData = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found for id: "+id));
         userRepo.delete(userData);
         return ResponseEntity.ok().build();
    }
}
