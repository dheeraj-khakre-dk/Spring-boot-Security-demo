package com.example.springBootStudent.Config;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springBootStudent.Model.Student;
import com.example.springBootStudent.Model.User;
import com.example.springBootStudent.Repo.MyUserRepo;
import com.example.springBootStudent.Repo.StudentRepository;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MyUserRepo userRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            List<Student> students = List.of(
                    new Student(null, "dheeraj khakre", "dheeraj@example.com", LocalDate.of(2000, 1, 1), null),
                    new Student(null, "reena khakre", "reena@example.com", LocalDate.of(2001, 2, 2), null),
                    new Student(null, "alkesh khakre", "alkesh@example.com", LocalDate.of(2002, 3, 3), null),
                    new Student(null, "amit", "amit@example.com", LocalDate.of(2003, 4, 4), null),
                    new Student(null, "gopal solunki", "gopal@example.com", LocalDate.of(2004, 5, 5), null),
                    new Student(null, "dev solanki", "dev@example.com", LocalDate.of(2005, 6, 6), null),
                    new Student(null, "payal", "payal@example.com", LocalDate.of(2006, 7, 7), null),
                    new Student(null, "sakshi jain", "sakshi@example.com", LocalDate.of(2007, 8, 8), null),
                    new Student(null, "somati khakre", "somati@example.com", LocalDate.of(2008, 9, 9), null),
                    new Student(null, "akki", "akki@example.com", LocalDate.of(2009, 10, 10), null));

            studentRepository.saveAll(students);

            List<User> users = List.of(
                    new User(1, "dheeraj khakre", "{noop}dheeraj@123"),
                    new User(2, "reena khakre", "{noop}reena@123"),
                    new User(3, "alkesh khakre", "{noop}alkesh@123"),
                    new User(4, "amit", "{noop}amit@123"),
                    new User(5, "gopal solunki", "{noop}gopal@123"),
                    new User(6, "dev solanki", "{noop}dev@123"),
                    new User(7, "payal", "{noop}payal@123"),
                    new User(8, "sakshi jain", "{noop}sakshi@123"),
                    new User(9, "somati khakre", "{noop}somati@123"),
                    new User(10, "akki", "{noop}akki@123"));

            userRepository.saveAll(users);

            // Log the saved students and users
            studentRepository.findAll().forEach(student -> log.info("Preloaded Student: " + student));
            userRepository.findAll().forEach(user -> log.info("Preloaded User: " + user));
        };
    }
}
