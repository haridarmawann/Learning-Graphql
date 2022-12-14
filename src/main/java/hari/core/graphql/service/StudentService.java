package hari.core.graphql.service;

import hari.core.graphql.models.Student;
import hari.core.graphql.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    final private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<Student> getStudentById(String id){
        if (studentRepository.findById(id).isPresent()){
            return studentRepository.findById(id);
        }
        return null;
    }

    public Student addStudent(String name){
        return studentRepository.save(new Student(null,name));
    }

    public List<Student> getAllStudents(Map<String,List<String>> headers){
        return studentRepository.findAll();
    }
}
