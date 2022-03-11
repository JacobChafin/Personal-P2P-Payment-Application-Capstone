package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;

public class JdbcAccountDao implements AccountDao {

       UserDao userDao;

    public BigDecimal getBalance (int userid) {
        String sql = "SELECT balance FROM account WHERE user_id = ?";
        SqlRowSet returns = null;
        BigDecimal balance = null;


        return userDao.getBalance(userid);
    }
}
