package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.model.Account;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@PreAuthorize("isAuthenticated()")

public class AccountController {

//    public AccountController (Account account) {
//
//    }

    @RequestMapping(path = "balance/id", method = RequestMethod.GET)
    public BigDecimal getBalance(@PathVariable int userid) {
        Account account = new Account();
        BigDecimal balance = account.getBalance();

        return balance;
    }




}
