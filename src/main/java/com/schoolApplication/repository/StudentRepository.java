package com.schoolApplication.repository;

import com.schoolApplication.model.Student;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository <Student, Long>{
    Optional<Student>findByRa(String ra);
    Optional<Student> findByName(String name);
    
}
