package com.student.details.services;

import com.student.details.entities.Student;
import com.student.details.exceptions.ResourceNotFoundException;
import com.student.details.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // CREATE
    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // UPDATE
    @Override
    public Student updateStudent(Student student, String id) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id: " + id)
                );

        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setDateOfBirth(student.getDateOfBirth());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPhoneNumber(student.getPhoneNumber());
        existingStudent.setAddress(student.getAddress());
        existingStudent.setEnrollmentDate(student.getEnrollmentDate());

        return studentRepository.save(existingStudent);
    }

    // DELETE
    @Override
    public void deleteStudent(String id) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id: " + id)
                );

        studentRepository.delete(existingStudent);
    }

    // GET ALL
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // GET BY ID
    @Override
    public Student getStudentById(String id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id: " + id)
                );
    }
}