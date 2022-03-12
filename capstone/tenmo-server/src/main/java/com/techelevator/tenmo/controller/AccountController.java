package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
<<<<<<< HEAD
=======
import com.techelevator.tenmo.dao.JdbcUserDao;
>>>>>>> e4d86f6895b7e5f2347280412d8dc3a715fc37bd
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")

public class AccountController {
private UserDao userDao;
private JdbcAccountDao accountDao;
private UserDao userDao;

    public AccountController(JdbcAccountDao account, JdbcUserDao user) {
        this.accountDao = account;
        this.userDao = user;
    }


    @RequestMapping(path = "balance/{userid}", method = RequestMethod.GET)
    public BigDecimal getBalance(@Valid @PathVariable int userid) {
        BigDecimal balance = accountDao.getBalance(userid);

        return balance;
    }

    @RequestMapping(path = "users/", method = RequestMethod.GET)
    public List<User> getUsers(){
        return userDao.findAll();
    }




}
