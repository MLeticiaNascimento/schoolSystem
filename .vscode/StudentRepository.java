package com.schoolApplication.repository;

import com.schoolApplication.model.Student;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository <Student, String>{
    Optional<Student>findByRa(String ra);
    Optional<Student> findByName(String name);
    
}
