package com.bankaccount.domaine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int accountId;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private double amount;

    private Date transactionDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

}
