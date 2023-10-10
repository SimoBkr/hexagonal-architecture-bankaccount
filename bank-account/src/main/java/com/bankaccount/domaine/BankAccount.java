package com.bankaccount.domaine;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private double balance;

    @OneToMany(
            mappedBy = "bankAccount",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @Builder.Default
    private List<Transaction> transactions = new ArrayList<>();


    public void depositMoney(double balance) {
        this.balance+=balance;
    }


    public void withdrawMoney(double balance) {
        this.balance-=balance;
    }

    public void addTransaction(BankAccount account,Transaction transaction) {
        account.getTransactions().add(transaction);
    }

}
