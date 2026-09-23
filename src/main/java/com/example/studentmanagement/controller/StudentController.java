package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.StudentRequest;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(
            StudentService service) {

        this.service = service;
    }

    // ========================================
    // CREATE STUDENT
    // ========================================

    @PostMapping
    public ResponseEntity<Student> addStudent(
            @Valid @RequestBody
            StudentRequest request) {

        Student student =
                service.addStudent(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(student);
    }

    // ========================================
    // GET ALL STUDENTS
    // ========================================

    @GetMapping
    public ResponseEntity<List<Student>>
    getAllStudents() {

        return ResponseEntity.ok(
                service.getAllStudents()
        );
    }

    // ========================================
    // GET STUDENT BY ID
    // ========================================

    @GetMapping("/{id}")
    public ResponseEntity<Student>
    getStudentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getStudentById(id)
        );
    }

    // ========================================
    // UPDATE STUDENT
    // ========================================

    @PutMapping("/{id}")
    public ResponseEntity<Student>
    updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody
            StudentRequest request) {

        Student student =
                service.updateStudent(
                        id,
                        request
                );

        return ResponseEntity.ok(student);
    }

    // ========================================
    // DELETE STUDENT
    // ========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    deleteStudent(
            @PathVariable Long id) {

        service.deleteStudent(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}