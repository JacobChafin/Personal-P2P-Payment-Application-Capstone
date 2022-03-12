package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {
    private JdbcTemplate jdbcTemplate;


    //    @Override
    public Transfer getTransfersById(int transferId) {
        Transfer transfer = new Transfer();
        String sql = "SELECT * " +
                "FROM transfer" +
                "WHERE transfer_id = ?";

        SqlRowSet transferList = jdbcTemplate.queryForRowSet(sql, transferId);
        if (transferList.next()) {
//            transfer = mapRowToTransfer(transferList);

        }
        return transfer;
    }

    //    @Override
    public List<Transfer> getListOfAllTransfers(int userId) {
        List<Transfer> listOfTransfers = new ArrayList<>();

        String sql = "SELECT *" +
                "FROM transfer" +
                "JOIN account ON account.account_id = transfer.account_from" +
                "WHERE account.user_id = ?";
        SqlRowSet fullTransferList = jdbcTemplate.queryForRowSet(sql, userId);
        while (fullTransferList.next()) {
            Transfer transfers = mapRowToTransfer(fullTransferList);
            listOfTransfers.add(transfers);
        }
        return listOfTransfers;
        // TODO Map ROW Set for transfer is in USER
    }

    private Transfer mapRowToTransfer(SqlRowSet stupidDumbSqlRowSetResults) {


        Transfer transfer = new Transfer();
        transfer.setAccountFrom(stupidDumbSqlRowSetResults.getInt("account_from"));
        transfer.setAccountTo(stupidDumbSqlRowSetResults.getInt("account_to"));
        transfer.setTransferId(stupidDumbSqlRowSetResults.getInt("transfer_id"));
        transfer.setTransferStatusId(stupidDumbSqlRowSetResults.getInt("transfer_status_id"));
        transfer.setTransferTypeId(stupidDumbSqlRowSetResults.getInt("transfer_type_id"));
        transfer.setTransferAmount(stupidDumbSqlRowSetResults.getInt("amount"));

        transfer.setTransferType(stupidDumbSqlRowSetResults.getString("transfer_type_desc"));
        transfer.setTransferStatus(stupidDumbSqlRowSetResults.getString("transfer_status_desc"));
//        transfer.setUserFrom(stupidDumbSqlRowSetResults.getString("user_from"));
//        transfer.setUserTo(stupidDumbSqlRowSetResults.getString("user_to"));
        return transfer;


    }

}
