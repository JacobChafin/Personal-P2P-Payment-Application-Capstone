package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    private BigDecimal transferAmount;
    private int transferStatusId;
    private int transferTypeId;
    private int accountTo;
    private int accountFrom;
    private int transferId;
    private String transferType;
    private String userFrom;
    private String userTo;
    private String transferStatus;

    public Transfer(BigDecimal transferAmount, int transferStatusId, int transferTypeId,
                    int accountTo, int accountFrom, int transferId,
                    String transferType, String userFrom, String userTo,
                    String transferStatus) {
        this.transferAmount = transferAmount;
        this.transferStatusId = transferStatusId;
        this.transferTypeId = transferTypeId;
        this.accountTo = accountTo;
        this.accountFrom = accountFrom;
        this.transferId = transferId;
        this.transferType = transferType;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.transferStatus = transferStatus;
    }

    public Transfer(String transferType, String userFrom, String userTo, String transferStatus) {
        this.transferType = transferType;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.transferStatus = transferStatus;
    }

    public BigDecimal getTransferAmount() {
    return transferAmount;}

    public void setTransferAmount(int bigDecimal) {
        transferAmount = transferAmount;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public int getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }

    public int getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }
}
