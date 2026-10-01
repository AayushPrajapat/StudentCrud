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
    //  Get Students by Id
    Student getStudentById(String id);


}
