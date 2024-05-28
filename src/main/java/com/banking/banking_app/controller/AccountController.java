package com.banking.banking_app.controller;

import com.banking.banking_app.dto.AccountDTO;
import com.banking.banking_app.service.impl.AccountServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    private AccountServiceImplementation accountService;

    public AccountController(AccountServiceImplementation accountService) {
        this.accountService = accountService;
    }

    //Add Account Rest Api
    @PostMapping
    public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDTO){

        return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
    }

    //Get Account By I'd Rest Api
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {

        return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
    }

    //Deposit Amount Rest Api
    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDTO> depositAmount(@PathVariable Long id, @RequestBody AccountDTO accounts) {

        return new ResponseEntity<>(accountService.depositAmount(id,accounts.getBalance()), HttpStatus.OK);
    }

    @PutMapping("withdraw/{id}")
    public ResponseEntity<AccountDTO> withdrawAmount(@PathVariable Long id, @RequestBody AccountDTO values)
    {
    return new ResponseEntity<>(accountService.withdrawAmount(id, values.getBalance()),HttpStatus.OK);
    }

    @GetMapping("/allAccount")
    public ResponseEntity<List<AccountDTO>> getAllAccounts(){

        return new ResponseEntity<>(accountService.getAllAccounts(),HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){

        accountService.deleteByid(id);
        return ResponseEntity.ok().body("Account Deleted SuccessFUL");
    }

}
