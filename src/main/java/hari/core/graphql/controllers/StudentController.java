package hari.core.graphql.controllers;

import hari.core.graphql.models.Student;
import hari.core.graphql.service.StudentService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class StudentController {


    final private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @QueryMapping
    public Optional<Student> getStudentById(@Argument String id){
        Optional<Student> data = studentService.getStudentById(id);
        return data;
    }

    @MutationMapping
    public Student addStudent(@Argument String name){
        Student student = studentService.addStudent(name);
        return student;
    }

    @QueryMapping
    public List<Student> getAllStudents(@ContextValue Map<String, List<String>> headers){
        return studentService.getAllStudents(headers);
    }
}
