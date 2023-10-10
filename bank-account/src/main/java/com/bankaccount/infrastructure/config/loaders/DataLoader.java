package com.bankaccount.infrastructure.config.loaders;

import com.bankaccount.domaine.BankAccount;
import com.bankaccount.infrastructure.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Lazy
@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final BankAccountRepository bankAccountRepository;

    @Bean
    ApplicationRunner runner() {
        return args -> {
            if (bankAccountRepository.findAll().isEmpty()) {
                bankAccountRepository.saveAll(initAccounts());
            }
        };
    }

    private List<BankAccount> initAccounts(){
        return  List.of(
                BankAccount.builder().lastName("Bakadir").firstName("Mohamed").balance(100.0).build(),
                BankAccount.builder().lastName("John").firstName("Nor").balance(200.0).build(),
                BankAccount.builder().lastName("Alex").firstName("murder").balance(300.0).build()
        );
    }
}
