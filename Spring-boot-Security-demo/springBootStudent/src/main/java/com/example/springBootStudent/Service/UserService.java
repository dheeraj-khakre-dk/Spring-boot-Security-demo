package com.example.springBootStudent.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springBootStudent.Model.User;
import com.example.springBootStudent.Repo.MyUserRepo;

@Service
public class UserService {

    private final MyUserRepo userRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JWTService jWTService;
    public UserService(AuthenticationManager authManager, PasswordEncoder encoder, MyUserRepo userRepo, 
            JWTService jWTService) {
        this.authManager = authManager;
        this.encoder = encoder;
        this.userRepo = userRepo;
        this.jWTService = jWTService;
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

    public User authenticateUser(User user) {
        Optional<User> userOptional = userRepo.findUserByUserName(user.getUserName());
        if (!userOptional.isPresent()) {
            throw new IllegalStateException("UserName and Password invalid ");
        }

        if (!encoder.matches( user.getPassword(),userOptional.get().getPassword() )) {
            throw new IllegalStateException("Password invalid");
        }

        return userOptional.get();
    }
    
    public String verify(User user) {
        Authentication auth =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        if (auth.isAuthenticated()) {
            return jWTService.generateToken(user.getUserName()) ;
        }
        return "fail";

    }

}
