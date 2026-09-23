package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentRequest;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(
            StudentRepository repository) {

        this.repository = repository;
    }

    // ========================================
    // ADD STUDENT
    // ========================================

    public Student addStudent(
            StudentRequest request) {

        Student student = new Student();

        student.setName(
                request.getName().trim()
        );

        student.setEmail(
                request.getEmail().trim()
        );

        student.setDepartment(
                request.getDepartment().trim()
        );

        student.setAge(
                request.getAge()
        );

        return repository.save(student);
    }

    // ========================================
    // GET ALL STUDENTS
    // ========================================

    public List<Student> getAllStudents() {

        return repository.findAll();
    }

    // ========================================
    // GET STUDENT BY ID
    // ========================================

    public Student getStudentById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student with ID "
                                        + id
                                        + " not found"
                        )
                );
    }

    // ========================================
    // UPDATE STUDENT
    // ========================================

    public Student updateStudent(
            Long id,
            StudentRequest request) {

        Student existingStudent =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student with ID "
                                                + id
                                                + " not found"
                                )
                        );

        existingStudent.setName(
                request.getName().trim()
        );

        existingStudent.setEmail(
                request.getEmail().trim()
        );

        existingStudent.setDepartment(
                request.getDepartment().trim()
        );

        existingStudent.setAge(
                request.getAge()
        );

        return repository.save(
                existingStudent
        );
    }

    // ========================================
    // DELETE STUDENT
    // ========================================

    public void deleteStudent(Long id) {

        if (!repository.existsById(id)) {

            throw new ResourceNotFoundException(
                    "Student with ID "
                            + id
                            + " not found"
            );
        }

        repository.deleteById(id);
    }
}