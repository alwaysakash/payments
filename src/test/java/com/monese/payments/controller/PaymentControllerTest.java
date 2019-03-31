package com.monese.payments.controller;

import com.monese.payments.exception.AccountException;
import com.monese.payments.model.Transaction;
import com.monese.payments.model.request.TransactionRequest;
import com.monese.payments.model.response.AccountResponse;
import com.monese.payments.model.response.TransactionResponse;
import com.monese.payments.service.TransactionHistoryService;
import com.monese.payments.service.TransactionServiceHandler;
import com.monese.payments.utils.TestDataProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@WebMvcTest(value = PaymentsController.class, secure = false)
public class PaymentControllerTest {

    @Mock
    private TransactionServiceHandler transactionServiceHandler;

    @Mock
    private TransactionHistoryService transactionHistoryService;

    @InjectMocks
    private PaymentsController paymentsController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testPing() {
        String result = paymentsController.ping();
        Assert.assertEquals(result, "I am up");
    }

    @Test
    public void testTransferFund_Success_Scenario() {
        Mockito.when(transactionServiceHandler.fundTransfer(Mockito.any(TransactionRequest.class))).thenReturn(TestDataProvider.getSuccessTransactionResponse());
        ResponseEntity<TransactionResponse> transactionResponse = paymentsController.transferFund(Mockito.any(TransactionRequest.class));
        Assert.assertEquals(transactionResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(transactionResponse.getBody().getMessage(), TestDataProvider.getSuccessTransactionResponse().getMessage());
    }

    @Test
    public void testTransferFund_Failure_Scenario() {
        Mockito.when(transactionServiceHandler.fundTransfer(Mockito.any(TransactionRequest.class))).thenReturn(TestDataProvider.getErrorTransactionResponse());
        ResponseEntity<TransactionResponse> transactionResponse = paymentsController.transferFund(Mockito.any(TransactionRequest.class));
        Assert.assertEquals(transactionResponse.getStatusCode(), HttpStatus.BAD_REQUEST);
        Assert.assertEquals(transactionResponse.getBody().getMessage(), TestDataProvider.getErrorTransactionResponse().getMessage());
    }

    @Test
    public void testTransactionHistory_Success_Scenario() {
        try {
            Mockito.when(transactionHistoryService.getTransactionHistory(Mockito.any(Pageable.class), Mockito.anyString())).thenReturn(TestDataProvider.getPageTransactions());
            ResponseEntity<Page<Transaction>> responsePage = paymentsController.transactionHistory(Mockito.any(Pageable.class), Mockito.anyString());
            Assert.assertEquals(responsePage.getBody().getTotalElements(), TestDataProvider.getPageTransactions().getTotalElements());
        } catch (AccountException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTransactionHistory_Failure_Scenario() {
        try {
            Mockito.when(transactionHistoryService.getTransactionHistory(Mockito.any(Pageable.class), Mockito.anyString())).thenThrow(new AccountException("Account doesn't exist"));
            ResponseEntity<Page<Transaction>> responsePage = paymentsController.transactionHistory(Mockito.any(Pageable.class), Mockito.anyString());
            Assert.assertEquals(responsePage.getBody().getTotalElements(), 0);
        } catch (AccountException e) {

        }
    }

    @Test
    public void testBalance(){
        try {
            Mockito.when(transactionServiceHandler.getAccountBalance(Mockito.anyString())).thenReturn(TestDataProvider.getAccountResponse());
            ResponseEntity<AccountResponse> response=paymentsController.balance(Mockito.anyString());
            Assert.assertEquals(response.getBody().getAccountNumber(),TestDataProvider.getAccountResponse().getAccountNumber());
        } catch (AccountException e) {
            Assert.fail();
            e.printStackTrace();
        }

    }

}
