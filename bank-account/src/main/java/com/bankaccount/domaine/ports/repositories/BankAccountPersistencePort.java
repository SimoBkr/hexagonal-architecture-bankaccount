package com.bankaccount.domaine.ports.repositories;

import com.bankaccount.domaine.BankAccount;

public interface BankAccountPersistencePort {

    BankAccount createBankAccount(BankAccount account);

    Double getBalanceById(Long id);

    String depositMoney(Long id, double amount);

    String withdrawMoney(Long id, double amount);
}
