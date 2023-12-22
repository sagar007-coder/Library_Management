package com.example.Library.Management.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String txnID;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    private int fine;
    //every trxn has one book
    //many trxn can have one book or
    // one book can present in many trxn, like  Issue and Return
    // so have to make relation with books
    // tho which one is better creating colum in Trxn id table to store Book Id
    // OR Storing Trxn Id in a book table?


    // creating book id in trxn table;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"transactionList"})
    private Book book;


    //one student can be the part of the multiple trxn
    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"transactionList"})
    private Student student;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"transactionList"})
    private Admin admin;






}
