package com.student.details.services;

import com.student.details.entities.Student;
import java.util.List;

public interface StudentService {

    //    Create
    Student createStudent(Student student);
    //    Update
    Student updateStudent(Student student,String id);
    //    Delete Students
    void deleteStudent(String id);
    //   Get All Students
    List<Student> getAllStudents();
    // Search students by the supplied fields
    List<Student> searchStudents(String firstName, String lastName, String email);
    //  Get Students by Id
    Student getStudentById(String id);


}
