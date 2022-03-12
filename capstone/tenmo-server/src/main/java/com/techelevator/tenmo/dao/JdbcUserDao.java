package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserDao implements UserDao {

    private static final BigDecimal STARTING_BALANCE = new BigDecimal("1000.00");
    private JdbcTemplate jdbcTemplate;


    public JdbcUserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int findIdByUsername(String username) {
        String sql = "SELECT user_id FROM tenmo_user WHERE username ILIKE ?;";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, username);
        if (id != null) {
            return id;
        } else {
            return -1;
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash FROM tenmo_user;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }
        return users;
    }

    @Override
    public User findBalanceByUsername(String username) {
        String sql = "SELECT balance " +
                "FROM account " +
                "JOIN tenmo_user ON account.user_id = tenmo_user.user_id" +
                "WHERE tenmo_user.user_id = (Select user_id from tenmo_user where username = ?)";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
        return mapRowToUser(rowSet);
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT user_id, username, password_hash FROM tenmo_user WHERE username ILIKE ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
        if (rowSet.next()) {
            return mapRowToUser(rowSet);
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public boolean create(String username, String password) {

        // create user
        String sql = "INSERT INTO tenmo_user (username, password_hash) VALUES (?, ?) RETURNING user_id";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        Integer newUserId;
        try {
            newUserId = jdbcTemplate.queryForObject(sql, Integer.class, username, password_hash);
        } catch (DataAccessException e) {
            return false;
        }

        // create account.
        sql = "INSERT INTO account (user_id, balance) values(?, ?)";
        try {
            jdbcTemplate.update(sql, newUserId, STARTING_BALANCE);
        } catch (DataAccessException e) {
            return false;
        }

        return true;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setActivated(true);
        user.setAuthorities("USER");
        return user;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

//                                    TRANSFER DEPOSIT WITHDRAWAL BALANCE

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<User> findAllUsernamesAndUserIDs() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, FROM tenmo_user;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }
        return users;
    }

    @Override
    public BigDecimal getBalance(int userId) {
        String balanceSql = "SELECT balance FROM accounts WHERE user_id = ?";
        SqlRowSet results = null;
        BigDecimal balance = null;
        results = jdbcTemplate.queryForRowSet(balanceSql, userId);
        balance = results.getBigDecimal("balance");

        return balance;
    }

    @Override
    public BigDecimal depositToBalance(BigDecimal transferAmount, String username) {
        BigDecimal balance = BigDecimal.valueOf(0);
        int idForDeposit = findIdByUsername(username);
        BigDecimal updatedBalance = getBalance(idForDeposit).add(transferAmount);
        // Display new balance
        String sql = "UPDATE accounts SET balance = ? WHERE user_id = ?";

        return balance;
    }

    @Override
    public BigDecimal withdrawalFromBalance(BigDecimal transferAmount, String username) {
        BigDecimal balance = BigDecimal.valueOf(0);
        int idForWithdrawal = findIdByUsername(username);
        // TODO Username.getBalance needs to be initialized
        BigDecimal updatedBalance = getBalance(idForWithdrawal).subtract(transferAmount);
        // Display new Balance
        String sql = "Update accounts Set Balance = ? Where user_id =?";

        return balance;
    }

    // TODO transfer is initially approved
    private String transfer(int sendingFromUserId, int sendingToUserId, BigDecimal transferAmount) {
        if (sendingFromUserId == sendingToUserId) {

            return "Invalid User.  Select Valid User ID";

        }
        if ((transferAmount.compareTo(getBalance(sendingFromUserId))) >= 0) ;
        {
            String sql = "";
            //TODO update SQL and do transfers
            //TODO Display BOTH userId's and the transfer amount
            // Accounts of sender - transferAmount
            // Accounts of receiver + transferAmount

        }
        return "Insufficient Funds";
    }


    // TODO User Id instead of Username?


    // TODO Transfer needs to display sender and receiver User ID's and the amount transferred


    //TODO sent this to Client for display
    // transfer method calls list of potential users and userID's from database
    // needs to be displayed in terminal
    // needs mapping

    // TODO Be able to retrieve all transfer information based on transfer ID


//    private Transfer mapRowToTransfer(SqlRowSet stupidDumbSqlRowSetResults) {
//
//
//        Transfer transfer = new Transfer();
//        transfer.setAccountFrom(stupidDumbSqlRowSetResults.getInt("account_from"));
//        transfer.setAccountTo(stupidDumbSqlRowSetResults.getInt("account_to"));
//        transfer.setTransferId(stupidDumbSqlRowSetResults.getInt("transfer_id"));
//        transfer.setTransferStatusId(stupidDumbSqlRowSetResults.getInt("transfer_status_id"));
//        transfer.setTransferTypeId(stupidDumbSqlRowSetResults.getInt("transfer_type_id"));
//        transfer.setTransferAmount(stupidDumbSqlRowSetResults.getInt("amount"));
//
//        transfer.setTransferType(stupidDumbSqlRowSetResults.getString("transfer_type_desc"));
//        transfer.setTransferStatus(stupidDumbSqlRowSetResults.getString("transfer_status_desc"));
////        transfer.setUserFrom(stupidDumbSqlRowSetResults.getString("user_from"));
////        transfer.setUserTo(stupidDumbSqlRowSetResults.getString("user_to"));
//        return transfer;
//    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//                                    ACCOUNTABILLABUDDY SECTION
    //I can merge Kieta
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private Account mapRowToAccount(SqlRowSet stupidDumbRowStuff){
        Account account = new Account();
        account.setAccountId(stupidDumbRowStuff.getInt("account_id"));
        account.setBalance(stupidDumbRowStuff.getBigDecimal("balance"));
        account.setUserId(stupidDumbRowStuff.getInt("user_id"));

        return account;


    }

}



