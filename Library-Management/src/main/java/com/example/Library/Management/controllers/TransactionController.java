package com.example.Library.Management.controllers;


import com.example.Library.Management.dtos.InitiateTransactionRequest;
import com.example.Library.Management.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;



    @PostMapping("/trxn")
    public String initiateTxn(@RequestBody @Valid InitiateTransactionRequest initiateTransactionRequest) throws Exception {
        return transactionService.initiateTxn(initiateTransactionRequest);
    }


    @PostMapping("/transaction/payment")
    public void makePayment(@RequestParam("amount") Integer amount,
                            @RequestParam("studentId") Integer studentId,
                            @RequestParam("transactionId") String txnID) throws Exception {

        transactionService.payFine(amount,studentId,txnID);
    }
}

