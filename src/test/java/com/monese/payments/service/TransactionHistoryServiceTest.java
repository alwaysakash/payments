package com.monese.payments.service;

import com.monese.payments.PaymentsApplication;
import com.monese.payments.exception.AccountException;
import com.monese.payments.model.Transaction;
import com.monese.payments.repositories.AccountsRepository;
import com.monese.payments.repositories.TransactionRepository;
import com.monese.payments.utils.TestDataProvider;
import com.monese.payments.validators.AccountValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = PaymentsApplication.class)
public class TransactionHistoryServiceTest {

    @InjectMocks
    TransactionHistoryService transactionHistoryService;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountsRepository accountsRepository;
    @Mock
    private AccountValidator accountValidator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTransactionHistory_SuccessScenario() {

        try {
            Pageable pageable = new PageRequest(0, 10);
            Mockito.when(transactionRepository.findByFromAccountOrToAccount(Mockito.any(Pageable.class), Mockito.anyString(), Mockito.anyString())).thenReturn(TestDataProvider.getPageTransactions());
            Page<Transaction> transactions = transactionHistoryService.getTransactionHistory(pageable, "9638527411");
            Assert.assertEquals(transactions.getTotalElements(), TestDataProvider.getPageTransactions().getTotalElements());
        } catch (AccountException e) {
            e.printStackTrace();
        }
    }
}
