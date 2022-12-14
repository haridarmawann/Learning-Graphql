package hari.core.graphql.service;

import hari.core.graphql.models.Lecturer;
import hari.core.graphql.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class LecturerService {
    @Autowired
    LecturerRepository lecturerRepository;


    public List<Lecturer> findAll() {

        return lecturerRepository.findByIsDeletedFalseOrderByCreatedAtDesc();
    }
    public Lecturer save(Lecturer lecturer) {

        return lecturerRepository.save(lecturer);
    }
//    public Lecturer save1(Lecturer lecturer) {
//        if(!lecturerRepository.findByUserId(lecturer.getUserId()).isEmpty() && lecturerRepository.findByLanguage(lecturer.getLanguage()).isEmpty()) {
//            throw new RecordExistException("User Id :" + lecturer.getUserId() + " already exists");
//        }
//        return lecturerRepository.save(lecturer);
//    }
    public Optional<Lecturer> findBySlugAndLanguage(String slug, String lang) {
        return lecturerRepository.findBySlugAndLanguageAndIsDeletedFalse(slug, lang);
    }

    public Optional<Lecturer> findBySlugAndLanguageSelected(String slug, String lang) {
        return lecturerRepository.findBySlugAndLanguageAndIsDeletedFalseAndIsSelectedIsTrue(slug, lang);
    }

    public Optional<Lecturer> findById(String id) {
        return lecturerRepository.findByIdAndIsDeletedFalse(id);
    }

    public List<Lecturer> findByLanguage(String id) {
        return lecturerRepository.findByLanguageAndIsDeletedFalseOrderByCreatedAtDesc(id);
    }
    public Page<Lecturer> findAllWithPaging(Pageable pageable) {
        return lecturerRepository.findByIsDeletedFalseOrderByCreatedAtDesc(pageable);
    }
    public Page<Lecturer> findByLanguageWithPaging(String id, Pageable pageable) {
        return lecturerRepository.findByLanguageAndIsDeletedFalseOrderByCreatedAtDesc(id,pageable);
    }

    public Optional<Lecturer> findByUserIdAndLanguage(String userId, String lang) {
        return lecturerRepository.findByUserIdAndLanguageAndIsDeletedFalse(userId, lang);
    }

    public Optional<Lecturer> findByUserIdAndLanguageSelected(String userId, String lang) {
        return lecturerRepository.findByUserIdAndLanguageAndIsDeletedFalseAndIsSelectedIsTrue(userId, lang);
    }

    public List<Lecturer> findByUserIdInAndLanguage(List<String> userId, String lang) {
        return lecturerRepository.findByUserIdInAndLanguageAndIsDeletedFalse(userId, lang);
    }

    public List<Lecturer> saveAll(List<Lecturer> lecturers) {
        return lecturerRepository.saveAll(lecturers);
    }

    public List<Lecturer> findByUserIdIn(List<String> userId) {
        return lecturerRepository.findByUserIdInAndIsDeletedFalse(userId);
    }
}
