package com.myboot.myboot.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myboot.myboot.entity.UserEntity;
import com.myboot.myboot.exception.UserNotFoundException;
import com.myboot.myboot.models.UserModel;
import com.myboot.myboot.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/user")
public class UserController {
    
    @GetMapping("simple")
    public String getUsers(){
        return "<h1 style='color:red;'> Hello User!</h1>";
    }
    
    @GetMapping("model")
    public List<UserModel> getUserDataModel(){
        return Arrays
        .asList(
            new UserModel(1L,"name",20),
            new UserModel(2L, "name2", 10)
        );
    }

    // user repo for db operations
    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public List<UserEntity> getUserDataList(){
        return userRepo.findAll();
    }

    // sesssion id route
    @GetMapping("session")
    public String getSessionId(HttpServletRequest request){
        return request.getSession().getId();
    }

    // csrf token
    @GetMapping("csrf")
    public CsrfToken getCsrfToken( HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
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

    // custom mehthod by property
    @GetMapping("/age/{age}")
    public UserEntity[] getUserByAge(@PathVariable int age){
        return userRepo.findAllByAge(age);
    }

    // custom sql query
    @PostMapping("/filter")
    public ResponseEntity<UserEntity[]> getByNameAndAge(@Param("name") String name , @Param("age") int age){
        return new ResponseEntity<>(userRepo.findByNameAndAge(name,age),HttpStatus.OK);
    }
    
    
}
