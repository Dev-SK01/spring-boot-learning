package com.myboot.myboot.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myboot.myboot.models.UserModel;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/user")
public class UserController {
    
    // @GetMapping
    // public String getUsers(){
    //     return "<h1 style='color:red;'> Hello User!</h1>";
    // }
    
    @GetMapping
    public List<UserModel> getUserDataList(){
        return Arrays
        .asList(
            new UserModel(1L,"name",20),
            new UserModel(2L, "name2", 10)
        );
    }
}
