package com.example.Library.Management.services;

import com.example.Library.Management.dtos.InitiateTransactionRequest;
import com.example.Library.Management.models.*;
import com.example.Library.Management.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    AdminService adminService;

    @Autowired
    BookService bookService;

    @Value("${student.allowed.max.books}")
    Integer maxBooksAllowed;

    @Value("${student.allowed.duration}")
    Integer duration;



    public String initiateTxn(InitiateTransactionRequest request) throws Exception {
        return request.getTransactionType() == TransactionType.ISSUE ? issuance(request):returnBook(request);
    }


    /**
     * Issuance
     * 1. If the book is available or not and student is valid or not
     * 2. entry in the Txn
     * 3. we need to check if student has reached the maximum limit of issuance
     * 4. book to be assigned to a student ==> update in book table
     *
     */
    private String issuance(InitiateTransactionRequest request) throws Exception {
        Student student = studentService.find(request.getStudentId());
        Admin admin = adminService.find(request.getAdminId());
        List<Book> bookList = bookService.find("id", String.valueOf(request.getBookId()));

        Book book = bookList != null && bookList.size() > 0 ? bookList.get(0): null;

        if(student == null ||
                admin == null ||
                book == null ||
                book.getStudent() != null ||
                student.getBookList().size() >= maxBooksAllowed ){

            System.out.print(student.getName());
            System.out.println(admin.getName());
            System.out.println(bookList);
            throw new Exception("Invalid request  in issuance in TS ");
        }
        Transaction transaction = null;
        try {
             transaction = Transaction.builder()
                    .txnID(UUID.randomUUID().toString())
                    .student(student)
                    .book(book)
                    .admin(admin)
                    .transactionType(request.getTransactionType())
                    .transactionStatus(TransactionStatus.PENDING)

                    .build();

            transactionRepository.save(transaction);
            book.setStudent(student);

            bookService.createOrUpdate(book);

            transaction.setTransactionStatus(TransactionStatus.SUCCESS);


        }
        catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
        }
        finally {
            transactionRepository.save(transaction);
        }
        return transaction.getTxnID();
    }


    /**
     * Return
     * 1. If the book is valid or not and student is valid or not
     * 2. entry in the Txn table
     * 3. due date check and fine calculation
     * 4. if there is no fine, then de-allocate the book from student's name ==> book table
     */
    private String returnBook(InitiateTransactionRequest request) throws Exception {

        Student student = studentService.find(request.getStudentId());
        Admin admin = adminService.find(request.getAdminId());
        List<Book> bookList = bookService.find("id", String.valueOf(request.getBookId()));

        Book book = bookList != null && bookList.size() > 0 ? bookList.get(0): null;

        if(student == null ||
                admin == null ||
                book == null ||
                book.getStudent() == null ||
                book.getStudent().getId() != student.getId()
                 ){
            throw new Exception("Invalid request");
        }
        // Getting the corresponding issuance txn
        Transaction issuanceTxn = transactionRepository.findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(
                student, book, TransactionType.ISSUE);
        if(issuanceTxn == null){
            throw new Exception("Invalid request");
        }

        Transaction transaction = null;
        try {
            Integer fine = calculateFine(issuanceTxn.getCreatedOn());
            transaction = Transaction.builder()
                    .txnID(UUID.randomUUID().toString())
                    .student(student)
                    .book(book)
                    .admin(admin)
                    .transactionType(request.getTransactionType())
                    .transactionStatus(TransactionStatus.PENDING)
                    .fine(fine)
                    .build();

            transactionRepository.save(transaction);

            if (fine == 0) {
                book.setStudent(null);
                bookService.createOrUpdate(book);
                transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            }
        }catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
        }finally {
            transactionRepository.save(transaction);
        }

        return transaction.getTxnID();
    }

    // S1 --> B1 = D1
    // S1 --> B1 = D2

    private Integer calculateFine(Date issuanceTime){

        long issueTimeInMillis = issuanceTime.getTime();
        long currentTime = System.currentTimeMillis();

        long diff = currentTime - issueTimeInMillis;

        long daysPassed = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if(daysPassed > duration){
            return (int)(daysPassed - duration);
        }

        return 0;
    }

    public void payFine(Integer amount, Integer studentId, String txnID) throws Exception {

        Transaction returnTxn = transactionRepository.findByTxnID(txnID);

        Book book = returnTxn.getBook();

        if(returnTxn.getFine() == amount && book.getStudent() != null && book.getStudent().getId() == studentId){
            returnTxn.setTransactionStatus(TransactionStatus.SUCCESS);
            book.setStudent(null);
            bookService.createOrUpdate(book);
            transactionRepository.save(returnTxn);
        }else{
            throw new Exception("invalid request");
        }

    }
    }

