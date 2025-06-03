package com.schoolApplication.service;

import org.springframework.stereotype.Service;
import com.schoolApplication.repository.StudentRepository;
import com.schoolApplication.model.Student;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository){
        this.repository = repository;
    }

    public Student createStudent(Student student){
        return repository.save(student);
    }

    public Student searchStudentByName(String name){
        return repository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado."));
    }

    public Student searchStudentByRa(String ra){
        return repository.findByRa(ra)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado"));
    }

    public List<Student> getAllStudents(){
        return repository.findAll();
    }

    public void deleteStudent(String ra){
        repository.deleteById(ra);
    }

    
}
