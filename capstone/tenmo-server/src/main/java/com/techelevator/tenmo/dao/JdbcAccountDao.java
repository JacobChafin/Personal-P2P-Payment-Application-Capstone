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
}
