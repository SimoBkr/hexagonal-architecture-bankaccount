package com.bankaccount.domaine.ports.services;

import com.bankaccount.domaine.BankAccount;

public interface BankAccountServicePort {

    BankAccount createBankAccount(BankAccount account);

    Double getBalanceById(Long id);

    String depositMoney(Long id,double amount);

    String withdrawMoney(Long id,double amount);
}
