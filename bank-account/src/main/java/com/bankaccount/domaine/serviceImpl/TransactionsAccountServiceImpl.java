package com.bankaccount.domaine.serviceImpl;

import com.bankaccount.domaine.ports.services.TransactionsAccountServicePort;
import com.bankaccount.domaine.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsAccountServiceImpl implements TransactionsAccountServicePort {

    private final TransactionsAccountServicePort transactionsAccountServicePort;

    @Override
    public List<Transaction> getAccountTransactions(Long accountId) {
        return transactionsAccountServicePort.getAccountTransactions(accountId);
    }
}
