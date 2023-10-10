package com.bankaccount.domaine.ports.repositories;

import com.bankaccount.domaine.Transaction;

import java.util.List;

public interface TransactionPersistencePort {

    List<Transaction> findByBankAccountId(Long accountId);
}
