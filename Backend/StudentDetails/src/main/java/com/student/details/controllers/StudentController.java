package com.student.details.controllers;

import com.student.details.entities.Student;
import com.student.details.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //    Create
    @PostMapping
    public ResponseEntity<Student> create(@Validated @RequestBody Student student) {
        Student created = studentService.createStudent(student);
        System.out.println(created);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    //  Update
    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        List<Student> allStudents = studentService.getAllStudents();
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }

    //  Student By Id
    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable String studentId) {
        Student student = studentService.getStudentById(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //  Delete
    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> delete(@PathVariable String studentId) {
        this.studentService.deleteStudent(studentId);
        return new ResponseEntity<>("Student Deleted SuccessFully..!!", HttpStatus.OK);
    }

    // Update
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> update(@Validated @RequestBody Student student, @PathVariable String studentId) {
        Student updatedStudent = this.studentService.updateStudent(student, studentId);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }


}
