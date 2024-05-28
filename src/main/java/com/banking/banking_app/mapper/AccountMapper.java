package com.banking.banking_app.mapper;

import com.banking.banking_app.dto.AccountDTO;
import com.banking.banking_app.entity.Account;

public class AccountMapper {

    public  static Account mapToAccount(AccountDTO accountDTO){

        Account acc= new Account(
                accountDTO.getId(),
                accountDTO.getAccountHolderName(),
                accountDTO.getBalance()
        );

        return  acc;
    }

    public  static AccountDTO mapToAccountDTO(Account account){

        AccountDTO dto = new AccountDTO(account.getId(), account.getAccountHolderName(), account.getBalance());
        return  dto;
    }
}
