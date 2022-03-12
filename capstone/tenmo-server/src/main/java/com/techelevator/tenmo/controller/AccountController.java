package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@PreAuthorize("isAuthenticated()")

public class AccountController {

private JdbcAccountDao accountDao;

    public AccountController(JdbcAccountDao account) {
        this.accountDao = account;
    }


    @RequestMapping(path = "balance/{userid}", method = RequestMethod.GET)
    public BigDecimal getBalance(@Valid @PathVariable int userid) {
        BigDecimal balance = accountDao.getBalance(userid);

        return balance;
    }




}
