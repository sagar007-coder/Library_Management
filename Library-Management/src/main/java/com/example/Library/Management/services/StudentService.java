package com.example.Library.Management.services;


import com.example.Library.Management.models.Student;
import com.example.Library.Management.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void create(Student student) {
        studentRepository.save(student);
    }

    public Student find(int studentId) {
       return studentRepository.findById(studentId).orElse(null);

    }
}
