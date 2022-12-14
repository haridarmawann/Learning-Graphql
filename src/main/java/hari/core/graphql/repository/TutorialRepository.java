package hari.core.graphql.repository;

import hari.core.graphql.models.Tutorial;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends MongoRepository<Tutorial,String> {
}
