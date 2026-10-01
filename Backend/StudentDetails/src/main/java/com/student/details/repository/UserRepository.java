package com.student.details.repository;

import com.student.details.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    // Optional Class is mainly used to represent that a value may or may not exist,
    // helping avoid accidental NullPointerExceptions.
    Optional<User> findByEmail(String email);
}
