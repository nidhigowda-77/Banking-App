package com.banking.banking_app.service;

import com.banking.banking_app.dto.AccountDTO;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface Account_Service {
    AccountDTO createAccount(AccountDTO accountDTO);

    AccountDTO getAccountById(Long id);

    AccountDTO depositAmount(Long id,double amount);

    AccountDTO withdrawAmount(Long id,double amount);

    List<AccountDTO> getAllAccounts();
    void deleteByid(Long id);
}


