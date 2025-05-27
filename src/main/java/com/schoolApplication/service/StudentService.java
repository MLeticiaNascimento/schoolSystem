package com.schoolApplication.service;

import com.schoolApplication.repository.StudentDAO;
import com.schoolApplication.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


  
@Service
public class StudentService {

    
    @Autowired
    private StudentDAO studentDAO;

    public Student searchStudentByRa(String ra){
        Student student = studentDAO.findByRa(ra);
        if(student == null){
            throw new RuntimeException("Aluno não encontrado."+ ra);
        }
        return student;
    }

    public Student searchStudentByName(String name){
        Student student = studentDAO.findByName(name);
        if(student == null){
            throw new RuntimeException("Aluno não encontrado."+ name);
        }
            return student;
    }
    
    public void update(Student student){
        studentDAO.update(student);
    }

    public void deleteStudentData(String ra){
        Student student = searchStudentByRa(ra);
        studentDAO.delete(student);
    }
}
  