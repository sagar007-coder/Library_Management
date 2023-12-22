package com.example.Library.Management.models;


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

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name ;

    @Column(unique = true , nullable = false )
    private String email;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @OneToMany(mappedBy = "my_author")
    private List<Book> bookList; // yeh ek back refrence jese h
    // maano agr , ek author table ussme kuch column h
    // author_Id, author_name , author_email..
    // ab iss table m humlog ek aur column add krege jo uss authour k books id store krega ek list m
    // tho agr koi query chlega -- Select * from author_tbl where author_id = 1;
    //tho uska O/P hoga - 1 , XYZ , XYZ@gmail.com , <LIST>//jo books dekga usske (tho basically humko List lene k liye
    // vo frgn_key ko use krna hoga jo book table m use hua (yaha pe my_author) ab yeh key waah se details fetch krega,
    // so basically do query run honge 1) Select * from author_tbl where author_id = 1; 2) Select * from book_tbl where my_author = 1;

}
