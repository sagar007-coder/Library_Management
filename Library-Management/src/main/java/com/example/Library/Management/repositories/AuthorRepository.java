package com.example.Library.Management.repositories;

import com.example.Library.Management.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {


    Author findByEmail(String email);

}
