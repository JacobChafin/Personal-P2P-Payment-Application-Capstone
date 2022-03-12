package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;

public class JdbcAccountDao implements AccountDao {
    UserDao userDao;
    private JdbcTemplate jdbcTemplate;

    @Override
    public BigDecimal getBalance(int userid) {
//        Integer balance;
        String sql = "SELECT balance FROM account WHERE user_id = ?";
//        balance = jdbcTemplate.queryForObject(sql, Integer.class, userid);

        SqlRowSet results = null;
        BigDecimal balance = null;
        results = jdbcTemplate.queryForRowSet(sql, userid);
        if (results.next()) {
            balance = results.getBigDecimal("balance");
        }
        return balance;
    }
}
