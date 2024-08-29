package com.kaunda.gama.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kaunda.gama.exception.StudentAlreadyExistsException;
import com.kaunda.gama.exception.StudentNotFoundException;
import com.kaunda.gama.model.Student;
import com.kaunda.gama.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        if (studentAlreadyExists(student.getEmail())) {
            throw new StudentAlreadyExistsException(student.getEmail() + " estudante Ja existe");
        }
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(st -> {
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());
            return studentRepository.save(st);
        }).orElseThrow(() -> new StudentNotFoundException("Estudante Não encontrado."));
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Estudante Não encotrado com esse Id" + id));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Estudante não encontrado");
        }
        studentRepository.deleteById(id);

    }

    private boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }

}
