package com.monese.payments.service;

import com.monese.payments.PaymentsApplication;
import com.monese.payments.exception.PaymentsGlobalException;
import com.monese.payments.model.Account;
import com.monese.payments.model.Transaction;
import com.monese.payments.model.response.TransactionResponse;
import com.monese.payments.repositories.AccountsRepository;
import com.monese.payments.repositories.TransactionRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = PaymentsApplication.class)
public class TransactionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountsRepository accountsRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFundTransfer_SuccessScenario() {
        Account fromAccount = new Account();
        fromAccount.setBalance(new Long(5000));
        fromAccount.setAccountNumber(new Long(963852740));
        Account toAccount = new Account();
        toAccount.setBalance(new Long(5000));
        toAccount.setAccountNumber(new Long(963852741));


        Transaction tx = new Transaction(new Long(1), "9638527410", "9638527411", new Long(500), "S", "SUCCESS", new Date());
        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(tx);

        try {
            TransactionResponse transactionResponse = transactionService.fundTransfer(fromAccount, toAccount, new Long(50));
            Assert.assertEquals(transactionResponse.getMessage(), "SUCCESS");
        } catch (PaymentsGlobalException e) {
            Assert.fail();
        }


    }


}
