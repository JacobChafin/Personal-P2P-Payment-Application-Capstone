package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    //    @Override
    public Transfer getTransfersById(int transferId) {
        Transfer transfer = new Transfer();
        String sql = "SELECT * " +
                "FROM transfer" +
                "WHERE transfer_id = ?";

        SqlRowSet transferList = jdbcTemplate.queryForRowSet(sql, transferId);
        if (transferList.next()) {
            transfer = mapRowToTransfer(transferList);

        }
        return transfer;
    }

    //    @Override
    public List<Transfer> getListOfAllTransfers(int userId) {
        List<Transfer> listOfTransfers = new ArrayList<>();

        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "FROM transfer " +
                "JOIN account ON account.account_id = transfer.account_from " +
                "WHERE account.user_id = ?";
        SqlRowSet fullTransferList = jdbcTemplate.queryForRowSet(sql, userId);
        while (fullTransferList.next()) {
//            Transfer transfers = mapRowToTransfer(fullTransferList);
            listOfTransfers.add(mapRowToTransfer(fullTransferList));
        }
<<<<<<< HEAD
    return listOfTransfers;

=======
        return listOfTransfers;
        // TODO Map ROW Set for transfer is in USER
>>>>>>> 3083aafc4d75a8c0d810b73235c5060953625e00
    }

    private Transfer mapRowToTransfer(SqlRowSet stupidDumbSqlRowSetResults) {


        Transfer transfer = new Transfer();
        transfer.setTransferId(stupidDumbSqlRowSetResults.getInt("transfer_id"));
        transfer.setTransferTypeId(stupidDumbSqlRowSetResults.getInt("transfer_type_id"));
        transfer.setTransferStatusId(stupidDumbSqlRowSetResults.getInt("transfer_status_id"));
        transfer.setAccountFrom(stupidDumbSqlRowSetResults.getInt("account_from"));
        transfer.setAccountTo(stupidDumbSqlRowSetResults.getInt("account_to"));
        transfer.setAmount(stupidDumbSqlRowSetResults.getBigDecimal("amount"));



//        transfer.setTransferType(stupidDumbSqlRowSetResults.getString("transfer_type_desc"));
//        transfer.setTransferStatus(stupidDumbSqlRowSetResults.getString("transfer_status_desc"));
//        transfer.setUserFrom(stupidDumbSqlRowSetResults.getString("user_from"));
//        transfer.setUserTo(stupidDumbSqlRowSetResults.getString("user_to"));
        return transfer;


    }

}
