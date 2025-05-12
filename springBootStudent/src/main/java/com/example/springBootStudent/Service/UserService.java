package com.example.springBootStudent.Service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springBootStudent.Model.User;
import com.example.springBootStudent.Repo.MyUserRepo;
@Service
public class UserService {
    
    private final MyUserRepo userRepo;
    private final PasswordEncoder encoder;
    public UserService(PasswordEncoder encoder, MyUserRepo userRepo) {
        this.encoder = encoder;
        this.userRepo = userRepo;
    }

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }
    
    public List<User> getAllUser() {
        List<User> users = userRepo.findAll();
        return users;
    }
    
    
}
