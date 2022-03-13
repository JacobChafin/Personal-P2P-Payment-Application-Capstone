package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {
    BigDecimal getBalance(int userid);
<<<<<<< HEAD
<<<<<<< HEAD

    Account findAccountByUserId(int accountId);

=======
    public BigDecimal depositToBalance(BigDecimal transferAmount, int userId);
    public BigDecimal withdrawalFromBalance(BigDecimal transferAmount, int userId);
    public String completeTransfer(int fromUserId, int toUserId, BigDecimal transferAmount);
>>>>>>> 46e655032fc67b8b43b9e8b2956102ace701925e
=======
    public BigDecimal depositToBalance(BigDecimal transferAmount, int userId);
    public BigDecimal withdrawalFromBalance(BigDecimal transferAmount, int userId);
    public String completeTransfer(int fromUserId, int toUserId, BigDecimal transferAmount);
>>>>>>> 3f4d112f64b012017fefd5bb91d6a581f4af9dc4



}
