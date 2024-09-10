package com.spring.spring_security.controller;

import com.spring.spring_security.entity.Student;
import com.spring.spring_security.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepo studentRepo;

    @GetMapping()
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @GetMapping("/{firstName}/{lastName}")
    public Student getStudentByName(@PathVariable String firstName, @PathVariable String lastName) {
        return studentRepo.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        if (student == null) {
            throw new RuntimeException("Null student object");
        } else studentRepo.save(student);
        return student;
    }

    @DeleteMapping
    public String deleteStudent(@RequestBody String firstName, @RequestBody String lastName) {
        Student student = studentRepo.findByFirstNameAndLastName(firstName, lastName).orElseThrow(() -> new RuntimeException("Student not found"));
        studentRepo.delete(student);
        return "Student deleted successfully";
    }


}
