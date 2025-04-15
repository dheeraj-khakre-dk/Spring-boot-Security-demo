package com.example.springBootStudent.Config;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.springBootStudent.Model.Student;
import com.example.springBootStudent.Model.User;
import com.example.springBootStudent.Repo.MyUserRepo;
import com.example.springBootStudent.Repo.StudentRepository;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    private final StudentRepository studentRepository;
    private  final MyUserRepo userRepository;
    private final PasswordEncoder encoder;

    public LoadDatabase(PasswordEncoder encoder, StudentRepository studentRepository, MyUserRepo userRepository) {
        this.encoder = encoder;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }
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
                    new User(null, "dheeraj", encoder.encode("dheeraj@123")),
                    new User(null, "reena", encoder.encode("reena@123")),
                    new User(null, "alkesh", encoder.encode("alkesh@123")),
                    new User(null, "amit", encoder.encode("amit@123")),
                    new User(null, "akki", encoder.encode("akki@123")));

            userRepository.saveAll(users);
             System.out.println(users);

            // Log the saved students and users
            studentRepository.findAll().forEach(student -> log.info("Preloaded Student: " + student));
            userRepository.findAll().forEach(user -> log.info("Preloaded User: " + user));
        };
    }
}
