package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;


public interface TransferDao {
    public Transfer getTransfersById(int transferId);

    List<Transfer> getListOfAllTransfersByUserId(int userId);

    public List<Transfer> getListOfAllTransfers();

    boolean sendTEBucks(int userFrom, int userTo, Transfer transfer);
}
