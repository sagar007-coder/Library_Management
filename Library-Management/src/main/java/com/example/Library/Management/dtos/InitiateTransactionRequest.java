package com.example.Library.Management.dtos;


import com.example.Library.Management.models.TransactionType;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InitiateTransactionRequest {

    @NotNull
    private Integer studentId;

    @NotNull
    private Integer bookId;

    @NotNull
    private Integer adminId;

    @NotNull
    private TransactionType transactionType;
}
