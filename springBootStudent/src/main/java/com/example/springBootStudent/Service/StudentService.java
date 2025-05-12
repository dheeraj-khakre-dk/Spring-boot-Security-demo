package com.example.springBootStudent.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springBootStudent.Model.Student;
import com.example.springBootStudent.Repo.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Student CreateStudent(Student student) {
        Optional<Student> studentOptional = this.studentRepository.findByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("student email already exsist");
        }
        Student resulStudent = this.studentRepository.save(student);
        return resulStudent;
    }

    public Student delelStudent(Long Id) {
        Optional<Student> studentOptional = this.studentRepository.findById(Id);
        if (studentOptional.isPresent()) {
            this.studentRepository.deleteById(Id);
        } else {
            throw new IllegalStateException("student Id " + Id + "not  exsist");
        }
        return studentOptional.get();
    }
    @Transactional
    public Student updateStudent(Student entity) {
        Student student = this.studentRepository.findById(entity.getId()).get();
        if (entity.getName() != null && !entity.getName().equals(student.getName())) {
            student.setName(entity.getName());
        }
        if (entity.getEmail() != null && !entity.getEmail().equals(student.getEmail())) {
            if (!this.studentRepository.findByEmail(entity.getEmail()).isPresent()) {
                student.setEmail(entity.getEmail());
            } else {
                throw new IllegalStateException("student email already exsist");
            }
        }
        if (entity.getDOB() != null && entity.getDOB() != student.getDOB()) {
            student.setDOB(entity.getDOB());
        }
        return student;
    }
 


}
