package com.example.springBootStudent.Repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springBootStudent.Model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.Email = ?1")
    Optional<Student> findByEmail(String Email);
    
}
