package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class JdbcTransferDao implements TransferDao{
    private JdbcTemplate jdbcTemplate;


//    @Override
//    public Transfer getTransfersById (int transferId) {
//        Transfer transfer = new Transfer();
//        String sql = "SELECT * " +
//                "FROM transfer" +
//                "WHERE transfer_id = ?";
//
//        SqlRowSet transferList = jdbcTemplate.queryForRowSet(sql, transferId);
//        if (transferList.next()) {
////            transfer = mapRowToTransfer(transferList);
//
//        }
//        return transfer;
//    }
//
//    @Override
//    public List<Transfer> getListOfAllTransfers(int userId) {
//        List<Transfer> listOfTransfers = new ArrayList<>();
//
//        String sql = "SELECT *" +
//                "FROM transfer" +
//                "JOIN account ON account.account_id = transfer.account_from" +
//                "WHERE account.account_id = ?";
//
//            // TODO Map ROW Set for transfer is in USER
//        }








}
