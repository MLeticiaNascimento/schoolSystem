
package com.schoolApplication.service;

import com.schoolApplication.model.Student;
import com.schoolApplication.repository.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;


    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student createStudent(Student student) {
        return repository.save(student);
    }

    @Override
    public Student searchStudentByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado."));
    }

    @Override
    public Student searchStudentByRa(String ra) {
        return repository.findByRa(ra)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado."));
    }

    @Override
    public Student updateStudent(String ra, Student updatedStudent) {
        Student existingStudent = repository.findByRa(ra)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado."));

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setCpf(updatedStudent.getCpf());
        existingStudent.setDateBirth(updatedStudent.getDateBirth());
        existingStudent.setCity(updatedStudent.getCity());
        existingStudent.setSerie(updatedStudent.getSerie());
        existingStudent.setTeam(updatedStudent.getTeam());
        existingStudent.setFone(updatedStudent.getFone());

        return repository.save(existingStudent);
    }

    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public void deleteStudent(String ra) {
        repository.deleteById(ra);
    }
}
