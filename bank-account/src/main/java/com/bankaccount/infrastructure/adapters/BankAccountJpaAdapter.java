package com.bankaccount.infrastructure.adapters;

import com.bankaccount.domaine.BankAccount;
import com.bankaccount.domaine.Transaction;
import com.bankaccount.domaine.TransactionType;
import com.bankaccount.domaine.ports.repositories.BankAccountPersistencePort;
import com.bankaccount.infrastructure.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class BankAccountJpaAdapter implements BankAccountPersistencePort {

    private final BankAccountRepository repository;

    @Override
    public BankAccount createBankAccount(BankAccount account) {
        return repository.save(BankAccount.builder()
                .balance(account.getBalance())
                .lastName(account.getLastName())
                .firstName(account.getFirstName())
                .build());
    }

    @Override
    public Double getBalanceById(Long id) {
        return repository.findBalanceById(id);
    }

    @Override
    public String depositMoney(Long id, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        repository.findById(id).ifPresent(bankAccount -> {
            bankAccount.depositMoney(amount);
            bankAccount.addTransaction(bankAccount, Transaction.builder()
                    .bankAccount(bankAccount)
                    .accountId(Math.toIntExact(id))
                    .amount(amount)
                    .transactionType(TransactionType.DEPOSIT)
                    .transactionDate(new Date()).build());
            repository.save(bankAccount);
        });
        return "Vous avez effectué un dépôt d'un montant de : "+amount;
    }

    @Override
    public String withdrawMoney(Long id, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        repository.findById(id).ifPresent(bankAccount -> {
            if (amount > bankAccount.getBalance()) {
                throw new IllegalStateException("Insufficient balance.");
            }
            bankAccount.withdrawMoney(amount);
            bankAccount.addTransaction(bankAccount,Transaction.builder()
                    .bankAccount(bankAccount)
                    .accountId(Math.toIntExact(id))
                    .amount(amount)
                    .transactionType(TransactionType.WITHDRAWAL)
                    .transactionDate(new Date()).build());
            repository.save(bankAccount);
        });
        return "Vous avez effectué un retrait d'un montant de : "+amount;
    }

}
