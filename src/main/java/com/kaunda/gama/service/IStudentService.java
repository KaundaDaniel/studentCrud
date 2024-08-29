package com.kaunda.gama.service;

import java.util.List;

import com.kaunda.gama.model.Student;

public interface IStudentService {
    Student addStudent(Student student);
    List<Student>getStudentList();
    Student updateStudent(Student student, Long id);
    Student getStudentById(Long id);
    void deleteStudent(Long id);


}
