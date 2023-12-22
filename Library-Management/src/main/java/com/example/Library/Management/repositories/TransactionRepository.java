package com.example.Library.Management.repositories;

import com.example.Library.Management.models.Book;
import com.example.Library.Management.models.Student;
import com.example.Library.Management.models.Transaction;
import com.example.Library.Management.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    //    @Query("select * from transaction where student_id = ?1 and book_id = ?2 and transactionType = ?3 order by id desc limit 1")
    Transaction findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(Student student, Book book, TransactionType transactionType);


    Transaction findByTxnID(String txnID);
    //Transaction findByTxnId(String txnID);

    // S1 --> B1 (Issuance) t1
    // s1 --> b1 (return) t2
    // s1 --> b1 (issue) t3
    // s1 --> b1 (return) t4
}
