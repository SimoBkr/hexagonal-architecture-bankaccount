package com.bankaccount;

import com.bankaccount.domaine.BankAccount;
import com.bankaccount.domaine.Transaction;
import com.bankaccount.domaine.TransactionType;
import com.bankaccount.domaine.ports.services.TransactionsAccountServicePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankAccountApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    private BankAccount expectedBank;

    @MockBean
    private TransactionsAccountServicePort transactionsAccountService;

    @BeforeEach
    public void setUp() {
        expectedBank  = BankAccount.builder()
                                    .id(1L)
                                    .firstName("Mohamed")
                                    .lastName("Bakadir")
                                    .balance(100.0)
                                    .transactions(new ArrayList<>())
                                    .build();;
    }

    @Test
    void whenPostRequestThenBankAccountCreated() {
        webTestClient
                .post()
                .uri("/bank-accounts")
                .bodyValue(expectedBank)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void testGetAccountBalance() {
        webTestClient
                .get()
                .uri("/bank-accounts/" + expectedBank.getId() + "/balance")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response -> {
                    assert(response.equals("Votre solde actuel est de : "+expectedBank.getBalance()));
                });
    }

    @Test
    void testGetAccountTransactions() {
        List<Transaction> expectedTransactions = new ArrayList<>();
        Transaction t1 = Transaction.builder().id(1l).transactionType(TransactionType.DEPOSIT).amount(100.0).build();
        expectedTransactions.add(t1);
        Mockito.when(transactionsAccountService.getAccountTransactions(expectedBank.getId())).thenReturn(expectedTransactions);

        webTestClient.get()
                .uri("/bank-accounts/" + expectedBank.getId() + "/transactions")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Transaction.class)
                .value(transactions -> {
                    assert(transactions.equals(expectedTransactions));
                });
    }

    @Test
    void testDepositMoney() {
        expectedBank.depositMoney(expectedBank.getBalance());
        Assertions.assertEquals(200.0, expectedBank.getBalance());
    }

    @Test
    void testWithdrawMoney() {
        expectedBank.withdrawMoney(expectedBank.getBalance());
        Assertions.assertEquals(0.0, expectedBank.getBalance());
    }

    @Test
    void testWithdrawMoneySufficientBalance() {
        expectedBank.depositMoney(expectedBank.getBalance());
        expectedBank.withdrawMoney(50.0);
        Assertions.assertEquals(150.0, expectedBank.getBalance());
    }

}
