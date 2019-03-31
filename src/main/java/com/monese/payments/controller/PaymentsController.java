package com.monese.payments.controller;

import com.monese.payments.exception.AccountException;
import com.monese.payments.model.Transaction;
import com.monese.payments.model.request.TransactionRequest;
import com.monese.payments.model.response.TransactionResponse;
import com.monese.payments.service.TransactionHistoryService;
import com.monese.payments.service.TransactionServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("payments/v1/")
public class PaymentsController {
    @Autowired
    TransactionServiceHandler transactionServiceHandler;

    @Autowired
    TransactionHistoryService transactionHistoryService;

    private Logger logger = LoggerFactory.getLogger(PaymentsController.class);

    @GetMapping("/ping")
    public String ping() {
        return "I am up";
    }


    @RequestMapping(value = "/transactions/transferFund", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<TransactionResponse> transferFund(@RequestBody TransactionRequest transferRequest) {

        logger.debug("Received funds transfer request {} ", transferRequest);
        TransactionResponse response = transactionServiceHandler.fundTransfer(transferRequest);
        if (response.getCode() != 200) {
            return new ResponseEntity<TransactionResponse>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<TransactionResponse>(response, HttpStatus.OK);

        }

    }

    @RequestMapping(value = "/transactions/history", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<Transaction> transactionHistory(@NotNull Pageable pageable, @PathParam("accountNumber") String accountNumber) {

        logger.debug("Received transaction history request {} ", accountNumber);
        Page<Transaction> transactionPages;
        try {
            transactionPages = transactionHistoryService.getTransactionHistory(pageable, accountNumber);
            return transactionPages;

        } catch (AccountException e) {
            return null;
        }
    }
}
