package com.example.springBootStudent.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootStudent.Model.User;
import com.example.springBootStudent.Service.MyUserDetailService;
import com.example.springBootStudent.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public  List<User> getAllUser() {
        List<User> users = userService.getAllUser();
        return users;
    }
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        
     return    userService.register(user);
    }
    
}
