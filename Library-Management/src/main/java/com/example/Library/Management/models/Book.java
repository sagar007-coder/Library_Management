package com.example.Library.Management.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated
    private Genre genre;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("bookList")// yeh frgn_key hai , jo author table se connect kr rha h.
    private Author my_author; //ab book table m usska naam naam hoga "my_author"
    //so its like,  Select * from book_tbl where my_author = .....;


    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"bookList"})
    private Student student;

    @OneToMany(mappedBy = "book")
    private List<Transaction>transactionsList;




}
