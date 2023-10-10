package com.bankaccount.infrastructure.repository;

import com.bankaccount.domaine.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Query("SELECT b.balance FROM BankAccount b WHERE b.id = :id")
    Double findBalanceById(Long id);
}
