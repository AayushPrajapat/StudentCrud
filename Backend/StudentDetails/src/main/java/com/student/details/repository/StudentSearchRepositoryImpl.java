package com.student.details.repository;

import com.student.details.entities.Student;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.regex.Pattern;

public class StudentSearchRepositoryImpl implements StudentSearchRepository {

    private final MongoTemplate mongoTemplate;

    public StudentSearchRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Student> searchStudents(String firstName, String lastName, String email) {
        Query query = new Query();
        addPartialMatch(query, "firstName", firstName);
        addPartialMatch(query, "lastName", lastName);
        addPartialMatch(query, "email", email);
        return mongoTemplate.find(query, Student.class);
    }

    private void addPartialMatch(Query query, String field, String value) {
        if (value != null && !value.isBlank()) {
            query.addCriteria(Criteria.where(field).regex(Pattern.quote(value.trim()), "i"));
        }
    }
}
