package hari.core.graphql.repository;

import hari.core.graphql.enumerate.Language;
import hari.core.graphql.models.Lecturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

    @Repository
    public interface LecturerRepository extends MongoRepository<Lecturer,String> {
        List<Lecturer> findByIsDeletedFalseOrderByCreatedAtDesc();
        Page<Lecturer> findByIsDeletedFalseOrderByCreatedAtDesc(Pageable pageable);
        Optional<Lecturer> findByIdAndIsDeletedFalse(String id);
        Optional<Lecturer>findByUserId(String userId);
        List<Lecturer> findByLanguageAndIsDeletedFalseOrderByCreatedAtDesc (String lang);
        Page<Lecturer> findByLanguageAndIsDeletedFalseOrderByCreatedAtDesc (String lang, Pageable pageable);
        Optional<Lecturer>findByLanguage(Language language);
        Optional<Lecturer> findBySlugAndLanguageAndIsDeletedFalse(String slug, String lang);
        Optional<Lecturer> findBySlugAndLanguageAndIsDeletedFalseAndIsSelectedIsTrue(String slug, String lang);
        Optional<Lecturer> findByUserIdAndLanguageAndIsDeletedFalse(String userId, String lang);
        Optional<Lecturer> findByUserIdAndLanguageAndIsDeletedFalseAndIsSelectedIsTrue(String userId, String lang);
        List<Lecturer> findByUserIdInAndLanguageAndIsDeletedFalse(List<String> userId, String lang);
        List<Lecturer> findByUserIdInAndIsDeletedFalse(List<String> userId);
    }


