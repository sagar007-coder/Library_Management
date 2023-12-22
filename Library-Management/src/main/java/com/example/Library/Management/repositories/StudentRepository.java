package com.example.Library.Management.repositories;

import com.example.Library.Management.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
