package com.banking.banking_app.service.impl;

import com.banking.banking_app.dto.AccountDTO;
import com.banking.banking_app.entity.Account;
import com.banking.banking_app.mapper.AccountMapper;
import com.banking.banking_app.repository.AccountRepository;
import com.banking.banking_app.service.Account_Service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImplementation implements Account_Service {

    private  AccountRepository accountRepository;

   public AccountServiceImplementation(AccountRepository repository){
        this.accountRepository = repository;
    }
    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {

       Account actualAccount = AccountMapper.mapToAccount(accountDTO);
       return AccountMapper.mapToAccountDTO(accountRepository.save(actualAccount))  ;
    }

    @Override
    public AccountDTO getAccountById(Long id) {

      Account value= accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not found"));
        return AccountMapper.mapToAccountDTO(value);
    }

    @Override
    public AccountDTO depositAmount(Long id, double amount) {

        Optional<Account> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            account.setBalance(account.getBalance()+amount);
            return AccountMapper.mapToAccountDTO(accountRepository.save(account));
        }
        return null;
    }

    @Override
    public AccountDTO withdrawAmount(Long id, double amount) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            account.setBalance(account.getBalance()-amount);
            return AccountMapper.mapToAccountDTO(accountRepository.save(account));
        }
        return null;
    }

    @Override
    public List<AccountDTO> getAllAccounts() {

       List<AccountDTO> listOfAccounts =accountRepository.findAll().stream().map(v->AccountMapper.mapToAccountDTO(v)).collect(Collectors.toList());;
        return listOfAccounts;
    }

    @Override
    public void deleteByid(Long id) {
       accountRepository.deleteById(id);
    }
}
