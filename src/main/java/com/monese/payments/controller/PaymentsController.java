package com.monese.payments.controller;

import com.monese.payments.exception.AccountException;
import com.monese.payments.model.Transaction;
import com.monese.payments.model.request.TransactionRequest;
import com.monese.payments.model.response.AccountResponse;
import com.monese.payments.model.response.TransactionResponse;
import com.monese.payments.service.TransactionHistoryService;
import com.monese.payments.service.TransactionServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.ArrayList;

@RestController
@RequestMapping("payments/v1/")
public class PaymentsController {
    @Autowired
    TransactionServiceHandler transactionServiceHandler;

    @Autowired
    TransactionHistoryService transactionHistoryService;

    private Logger logger = LoggerFactory.getLogger(PaymentsController.class);

    @RequestMapping(value = "/ping",method = RequestMethod.GET)
    public String ping() {
        return "I am up";
    }


    @RequestMapping(value = "/transactions/transferFund", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<TransactionResponse> transferFund(@RequestBody TransactionRequest transferRequest) {

        logger.debug("Received funds transfer request {} ", transferRequest);
        TransactionResponse response = transactionServiceHandler.fundTransfer(transferRequest);
        if (response.getCode() != 200) {
            logger.debug("Error in funds transfer process {} ", transferRequest);
            return new ResponseEntity<TransactionResponse>(response, HttpStatus.BAD_REQUEST);
        } else {
            logger.debug("Successfully completed funds transfer process {} ", transferRequest);
            return new ResponseEntity<TransactionResponse>(response, HttpStatus.OK);

        }

    }

    @RequestMapping(value = "/transactions/history", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Page<Transaction>> transactionHistory(@NotNull Pageable pageable, @RequestParam("accountNumber") String accountNumber) {

        logger.debug("Received transaction history request {} ", accountNumber);
        Page<Transaction> transactionPages;
        try {
            transactionPages = transactionHistoryService.getTransactionHistory(pageable, accountNumber);
            return new ResponseEntity<Page<Transaction>>(transactionPages,HttpStatus.OK);

        } catch (AccountException e) {
            transactionPages=new PageImpl<Transaction>(new ArrayList<Transaction>());
            return new ResponseEntity<Page<Transaction>>(transactionPages,HttpStatus.BAD_REQUEST);

        }
    }
    @RequestMapping(value = "/transactions/balance", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<AccountResponse> balance(@RequestParam("accountNumber") String accountNumber) {

        logger.debug("Received account balance request {} ", accountNumber);
        AccountResponse response = null;
        try {
            response = transactionServiceHandler.getAccountBalance(accountNumber);
            logger.debug("Successfully completed account balance request {} ", accountNumber);
            return  new ResponseEntity<AccountResponse>(response,HttpStatus.OK);

        } catch (AccountException e) {
            logger.debug("Error in account balance request {} ", accountNumber);
            return new ResponseEntity<AccountResponse>(response,HttpStatus.BAD_REQUEST);
        }


    }
}
