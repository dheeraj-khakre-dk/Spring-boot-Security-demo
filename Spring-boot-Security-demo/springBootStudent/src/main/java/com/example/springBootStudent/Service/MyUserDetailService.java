package com.example.springBootStudent.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springBootStudent.Model.User;
import com.example.springBootStudent.Model.UserPrincipal;
import com.example.springBootStudent.Repo.MyUserRepo;

@Service
public class MyUserDetailService implements  UserDetailsService {

    private final MyUserRepo myUserRepo;

    public MyUserDetailService(MyUserRepo myUserRepo) {
        this.myUserRepo = myUserRepo;
    }

   

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = myUserRepo.findUserByUserName(username).get();
        if (user == null) {
            System.out.println("User not   found ");
            throw new UsernameNotFoundException("User not foond 'loadUserByUsername'");
        }
        return new UserPrincipal(user);
    }
}
