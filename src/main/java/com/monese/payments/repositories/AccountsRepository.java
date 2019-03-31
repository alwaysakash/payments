package com.monese.payments.repositories;

import com.monese.payments.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountsRepository extends CrudRepository<Account, Long> {
}
