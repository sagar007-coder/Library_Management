package com.example.Library.Management.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.swing.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

    @Column(unique = true , nullable = false)
    private String email;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private  Date updatedOn;


    @OneToMany(mappedBy = "student")
    @JsonIgnoreProperties({"student"})
    private List<Book>bookList;

    @OneToMany(mappedBy = "student")
    @JsonIgnoreProperties("student")
    private List<Transaction>transactionsList;



}
