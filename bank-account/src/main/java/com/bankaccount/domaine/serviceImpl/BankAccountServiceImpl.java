package com.bankaccount.domaine.serviceImpl;

import com.bankaccount.domaine.ports.repositories.BankAccountPersistencePort;
import com.bankaccount.domaine.ports.services.BankAccountServicePort;
import com.bankaccount.domaine.BankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountServicePort {

    private final BankAccountPersistencePort bankAccountPersistencePort;

    @Override
    public BankAccount createBankAccount(BankAccount account) {
        return bankAccountPersistencePort.createBankAccount(account);
    }

    @Override
    public Double getBalanceById(Long id) {
        return bankAccountPersistencePort.getBalanceById(id);
    }

    @Override
    public String depositMoney(Long id, double amount) {
        return bankAccountPersistencePort.depositMoney(id,amount);
    }

    @Override
    public String withdrawMoney(Long id, double amount) {
        return bankAccountPersistencePort.withdrawMoney(id,amount);
    }
}
