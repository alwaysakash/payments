package com.monese.payments.utils;

import com.monese.payments.model.Transaction;
import com.monese.payments.model.request.TransactionRequest;
import com.monese.payments.model.response.TransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestDataProvider {

    public static TransactionResponse getSuccessTransactionResponse() {
        TransactionResponse response = new TransactionResponse("9638527410", "9638527410", 200, "S", "SUCCESS");
        return response;

    }

    public static TransactionResponse getErrorTransactionResponse() {
        TransactionResponse response = new TransactionResponse("9638527410", "9638527410", 500, "F", "FAILURE");
        return response;

    }

    public static Page<Transaction> getPageTransactions() {
        return new PageImpl<Transaction>(getTransactionList());
    }

    public static List<Transaction> getTransactionList() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        Transaction transaction1 = new Transaction(new Long(1), "9638527410", "9638527411", new Long(500), "S", "SUCCESS", new Date());
        Transaction transaction2 = new Transaction(new Long(2), "9638527415", "9638527417", new Long(50), "S", "SUCCESS", new Date());
        transactions.add(transaction1);
        transactions.add(transaction2);
        return transactions;
    }

    public static TransactionRequest getTransactionRequest() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(new Long(50));
        transactionRequest.setFromAccount("963852741");
        transactionRequest.setToAccount("9635287412");
        return transactionRequest;
    }


}
