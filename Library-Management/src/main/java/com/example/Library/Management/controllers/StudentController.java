package com.example.Library.Management.controllers;

import com.example.Library.Management.dtos.CreateStudentRequest;
import com.example.Library.Management.models.Student;
import com.example.Library.Management.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class StudentController {

    @Autowired
    StudentService studentService;
    @PostMapping("/student")
    public void createStudent(@RequestBody @Valid CreateStudentRequest studentRequest) {
        studentService.create(studentRequest.to());

    }

    @GetMapping("/student")
    public Student findStudent(@RequestParam("id") int studentId){
      return studentService.find(studentId);
    }



}
