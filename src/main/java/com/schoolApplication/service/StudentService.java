package com.schoolApplication.service;

import com.schoolApplication.repository.StudentRepository;
import com.schoolApplication.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

  
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student searchStudentByRa(String ra){
        return studentRepository.findByRa(ra)
        .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
    }

    public Student searchStudentByName(String name){
        return studentRepository.findByName(name)
        .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
    }
    
}
  