package com.example.springBootStudent.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootStudent.Model.User;
import com.example.springBootStudent.Service.MyUserDetailService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final MyUserDetailService userService;

    public UserController(MyUserDetailService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public  List<User> getAllUser() {
        List<User> users = userService.getAllUser();
        return users;
    }

    
}
