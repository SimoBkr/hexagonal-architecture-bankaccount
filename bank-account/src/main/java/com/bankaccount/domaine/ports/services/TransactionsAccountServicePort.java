package com.bankaccount.domaine.ports.services;

import com.bankaccount.domaine.Transaction;

import java.util.List;

public interface TransactionsAccountServicePort {

    List<Transaction>  getAccountTransactions(Long accountId);

}
