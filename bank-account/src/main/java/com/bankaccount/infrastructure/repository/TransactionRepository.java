package com.bankaccount.infrastructure.repository;

import com.bankaccount.domaine.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByBankAccountId(Long accountId);
}
