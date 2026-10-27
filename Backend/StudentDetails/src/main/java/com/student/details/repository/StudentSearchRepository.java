package com.student.details.repository;

import com.student.details.entities.Student;

import java.util.List;

public interface StudentSearchRepository {

    List<Student> searchStudents(String firstName, String lastName, String email);
}
