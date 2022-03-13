package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {
    BigDecimal getBalance(int userid);
<<<<<<< HEAD

    Account findAccountByUserId(int accountId);

=======
    public BigDecimal depositToBalance(BigDecimal transferAmount, int userId);
    public BigDecimal withdrawalFromBalance(BigDecimal transferAmount, int userId);
    public String completeTransfer(int fromUserId, int toUserId, BigDecimal transferAmount);
>>>>>>> 46e655032fc67b8b43b9e8b2956102ace701925e



}
