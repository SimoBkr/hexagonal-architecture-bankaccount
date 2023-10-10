package com.bankaccount.application;

import com.bankaccount.domaine.ports.services.BankAccountServicePort;
import com.bankaccount.domaine.ports.services.TransactionsAccountServicePort;
import com.bankaccount.domaine.BankAccount;
import com.bankaccount.domaine.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank-accounts")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountServicePort bankAccountService;

    private final TransactionsAccountServicePort transactionsAccountService;

    @PostMapping
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount account) {
        BankAccount bankAccount = bankAccountService.createBankAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(bankAccount);
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<String> getAccountBalance(@PathVariable("id") Long id) {
        Double balance = bankAccountService.getBalanceById(id);
        if (!balance.isNaN()) {
            return ResponseEntity.ok("Votre solde actuel est de : "+balance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<String> depositMoney(@PathVariable("id") Long id, @RequestParam("amount") double amount) {
        return ResponseEntity.ok(bankAccountService.depositMoney(id, amount));
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<String> withdrawMoney(@PathVariable("id") Long id, @RequestParam("amount") double amount) {
        return ResponseEntity.ok(bankAccountService.withdrawMoney(id, amount));
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getAccountTransactions(@PathVariable("accountId") Long accountId) {
        List<Transaction> transactions = transactionsAccountService.getAccountTransactions(accountId);
        if (transactions != null && !transactions.isEmpty()) {
            return ResponseEntity.ok(transactions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
