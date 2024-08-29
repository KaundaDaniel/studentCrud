package com.kaunda.gama.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kaunda.gama.model.Student;
import com.kaunda.gama.service.IStudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class StudentController {
    private final IStudentService iStudentService;

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(iStudentService.getStudentList(), HttpStatus.FOUND);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return iStudentService.addStudent(student);
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable Long id) {
        return iStudentService.updateStudent(student, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id) {
        iStudentService.deleteStudent(id);
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return iStudentService.getStudentById(id);
    }

}
