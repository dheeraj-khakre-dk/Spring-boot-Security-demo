package com.example.springBootStudent.Controller;

import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootStudent.Model.Student;
import com.example.springBootStudent.Service.StudentService;

import jakarta.servlet.http.HttpServletRequest;






@RestController
@RequestMapping(path="/api/students")
public class StudentController {
    private final StudentService studentService;
    
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getMethodName() {
        // return   List.of(new Student(1l, "dheeraj", "dk@gmail.com", "12/03/2002", 20));
        return studentService.getAllStudent();
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student) {

        return studentService.CreateStudent(student);
    }
    @DeleteMapping(path="{studentId}")
    public Student deleStudent(@PathVariable("studentId") Long Id) {
        return this.studentService.delelStudent(Id);
    }
    
    @PutMapping
    public Student putMethodName(@RequestBody Student entity) {
        
        
        return this.studentService.updateStudent(entity);
    }
    @GetMapping("/csrf-token")
    public CsrfToken getMethodName(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
    
    

}
