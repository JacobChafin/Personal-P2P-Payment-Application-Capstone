package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao {
    private JdbcTemplate jdbcTemplate;
public JdbcAccountDao(DataSource dataSource) {
    this.jdbcTemplate =new JdbcTemplate(dataSource);
}

    @Override
    public BigDecimal getBalance(int userid) {
//        Integer balance;
        BigDecimal balance = null;
        String sql = "SELECT balance FROM account WHERE user_id = ?";
        balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, userid);

//        SqlRowSet results = null;
//        BigDecimal balance = null;
//        results = jdbcTemplate.queryForRowSet(sql, userid);
        return balance;
    }
//    @Override
//    public BigDecimal depositToBalance(BigDecimal transferAmount, String username) {
//        BigDecimal balance = BigDecimal.valueOf(0);
//        int idForDeposit = findIdByUsername(username);
//        BigDecimal updatedBalance = getBalance(idForDeposit).add(transferAmount);
//        // Display new balance
//        String sql = "UPDATE accounts SET balance = ? WHERE user_id = ?";
//
//        return balance;
//    }
//    @Override
//    public BigDecimal withdrawalFromBalance(BigDecimal transferAmount, String username) {
//        BigDecimal balance = BigDecimal.valueOf(0);
//        int idForWithdrawal = findIdByUsername(username);
//        // TODO Username.getBalance needs to be initialized
//        BigDecimal updatedBalance = getBalance(idForWithdrawal).subtract(transferAmount);
//        // Display new Balance
//        String sql = "Update accounts Set Balance = ? Where user_id =?";
//
//        return balance;
//    }


}




