package com.student.details.repository;

import com.student.details.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student,String> {



}
