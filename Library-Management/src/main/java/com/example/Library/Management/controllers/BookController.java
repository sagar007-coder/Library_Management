package com.example.Library.Management.controllers;


import com.example.Library.Management.dtos.CreateBookRequest;
import com.example.Library.Management.dtos.SearchBookRequest;
import com.example.Library.Management.models.Book;
import com.example.Library.Management.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public void createBook(@RequestBody @Valid CreateBookRequest BookRequest){
        // author --> book
        // book --> author --> map book to the author

        bookService.createOrUpdate(BookRequest.to());

    }

   /* @GetMapping("/book") //commenting because its throwing error , error of @requestBody
    public List<Book> getBooks(@RequestBody @Valid SearchBookRequest searchBookRequest) throws Exception {

        return bookService.find(
                searchBookRequest.getSearchKey(),
                searchBookRequest.getSearchValue()
        );

    }*/


    @GetMapping("/books")
    public List<Book> getBooks(@RequestParam("key") String key,
                               @RequestParam("value") String value) throws Exception {

        return bookService.find(key, value);

    }

    // localhost:8080/book?key=author_name&value=Peter
    // localhost:8080/book?key=genre&value=FICTIONAL

}