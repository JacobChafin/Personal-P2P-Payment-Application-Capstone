package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface TransferDao {   private Transfer mapRowToTransfer(SqlRowSet stupidDumbSqlRowSetResults) {


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
