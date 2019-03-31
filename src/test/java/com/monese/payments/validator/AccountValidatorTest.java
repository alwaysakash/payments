package com.monese.payments.validator;

import com.monese.payments.PaymentsApplication;
import com.monese.payments.exception.AccountException;
import com.monese.payments.exception.AmountException;
import com.monese.payments.repositories.AccountsRepository;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = PaymentsApplication.class)
public class AccountValidatorTest {
    @Mock
    private AccountsRepository accountsRepository;

    @InjectMocks
    private AccountValidator validator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTransaction() {
        try {
            Mockito.when(accountsRepository.exists(Long.parseLong(TestDataProvider.getTransactionRequest().getFromAccount()))).thenReturn(Boolean.TRUE);
            Mockito.when(accountsRepository.exists(Long.parseLong(TestDataProvider.getTransactionRequest().getToAccount()))).thenReturn(Boolean.TRUE);
            validator.validateTransaction(TestDataProvider.getTransactionRequest());

        } catch (AmountException e) {
            Assert.fail();
        } catch (AccountException e) {
            Assert.fail();
        }

    }
}
