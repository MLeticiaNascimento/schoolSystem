package com.schoolApplication.controller;

import com.schoolApplication.service.StudentService;
import com.schoolApplication.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
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

    @GetMapping   
    public ResponseEntity<List<Student>>getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
    }

    @DeleteMapping("/{ra}")
    public ResponseEntity<Void> deleteStudent(@PathVariable ("ra")String ra){
        studentService.deleteStudent(ra);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{ra}")
    public ResponseEntity<Student> updateStudent(@PathVariable String ra, @RequestBody Student updatedStudent) {
        Student student = studentService.updateStudent(ra, updatedStudent);
        return ResponseEntity.ok(student);
}


    @PostMapping("/{ra}/mediacao")
    public ResponseEntity<?> solicitarMediacao(@PathVariable String ra, @RequestBody Map<String, String> body) {
            String matricula = body.get("matricula");
            return ResponseEntity.ok("Mediação solicitada com sucesso!");
    }
}