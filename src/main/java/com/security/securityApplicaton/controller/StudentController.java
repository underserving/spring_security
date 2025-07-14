package com.security.securityApplicaton.controller;

import com.security.securityApplicaton.models.StudentModel;
import com.security.securityApplicaton.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private StudentService service;

    public StudentController(StudentService service){
        this.service=service;
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentModel>> getStudents(){
        List<StudentModel> lists=service.getStudents();
     return new ResponseEntity<>(lists, HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<String> addStudent(@RequestBody StudentModel student){
        service.addStudent(student);
        return new ResponseEntity<>("student added"+student.getName(),HttpStatus.OK);
    }
}
