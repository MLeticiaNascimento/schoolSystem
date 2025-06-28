package com.schoolApplication.service;

import com.schoolApplication.model.Student;

import java.util.List;


public interface StudentService {

    Student createStudent(Student student);

    Student searchStudentByName(String name);

    Student searchStudentByRa(String ra);

    Student updateStudent(String ra, Student updatedStudent);

    List<Student> getAllStudents();

    void deleteStudent(String ra);
}
