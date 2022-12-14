package hari.core.graphql.repository;

import hari.core.graphql.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student,String> {


    Optional<Student> findById(String id);
}
