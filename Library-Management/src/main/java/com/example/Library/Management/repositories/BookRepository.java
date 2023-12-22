package com.example.Library.Management.repositories;

import com.example.Library.Management.models.Book;
import com.example.Library.Management.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Integer> {


     List<Book> findByGenre(Genre genre);

     List<Book> findByName(String bookName);

    @Query("select b from Book b, Author a where b.my_author.id = a.id and a.name = ?1")
       List<Book> findByAuthor_Name(String searchValue);
}
