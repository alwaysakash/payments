package com.monese.payments.repositories;

import com.monese.payments.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

    Page<Transaction> findByFromAccountOrToAccount(Pageable request, String fromAccount, String toAccount);
}
