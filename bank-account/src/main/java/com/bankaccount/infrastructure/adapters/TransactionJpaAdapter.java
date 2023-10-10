package com.bankaccount.infrastructure.adapters;

import com.bankaccount.domaine.Transaction;
import com.bankaccount.domaine.ports.repositories.TransactionPersistencePort;
import com.bankaccount.infrastructure.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionJpaAdapter implements TransactionPersistencePort {

    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findByBankAccountId(Long accountId) {
        return transactionRepository.findByBankAccountId(accountId);
    }
}
