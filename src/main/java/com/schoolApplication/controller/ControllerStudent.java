package com.schoolApplication.controller;

import com.schoolApplication.model.Student;
import com.schoolApplication.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/students")
public class ControllerStudent {

    @Autowired
    private StudentService studentService;

    @GetMapping("/ra/{ra}")
    public ResponseEntity<Student> searchStudentByRa(@PathVariable String ra) {
        try {
            Student student = studentService.searchStudentByRa(ra);
            return ResponseEntity.ok(student);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<Student> searchStudentByName(@PathVariable String name) {
        try {
            Student student = studentService.searchStudentByName(name);
            return ResponseEntity.ok(student);
            
        } catch (RuntimeException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    
}
