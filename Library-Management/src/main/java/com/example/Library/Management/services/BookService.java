package com.example.Library.Management.services;


import com.example.Library.Management.models.Author;
import com.example.Library.Management.models.Book;
import com.example.Library.Management.models.Genre;
import com.example.Library.Management.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorService authorService;


    public void createOrUpdate(Book book){

        Author bookAuthor = book.getMy_author();
        Author savedAuthor = authorService.getOrCreate (bookAuthor);

        book.setMy_author(savedAuthor);


        bookRepository.save(book);
    }

    public List<Book> find(String searchKey, String searchValue) throws Exception {

        switch (searchKey){
            case "id": {
                Optional<Book> book = bookRepository.findById(Integer.parseInt(searchValue));
                if(book.isPresent()){
                    return Arrays.asList(book.get());
                }else{
                    return new ArrayList<>();
                }
            }
            case "genre":
                return bookRepository.findByGenre(Genre.valueOf(searchValue));
            case "author_name":
                return bookRepository.findByAuthor_Name(searchValue);
            case "name":
                return bookRepository.findByName(searchValue);
            default:
                throw new Exception("Search key not valid " + searchKey);
        }
    }

}
