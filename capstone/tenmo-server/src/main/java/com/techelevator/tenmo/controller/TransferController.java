package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {
    private UserDao userDao;
    private Transfer transfer;
    private TransferDao transferDao;

    public TransferController(TransferDao transfer) {
        this.transferDao = transfer;
    }





// authentication
    @RequestMapping(path = "transfers/{id}", method = RequestMethod.GET)
    public Transfer getTransfer(@PathVariable int userid) {
//        Transfer transfer = transfer.getTransferId(userid);
        return transfer;
    }

    @RequestMapping(path = "account/transfers/{userid}", method = RequestMethod.GET)
    public List<Transfer> listOfTransfers (@PathVariable int userid) {
        List<Transfer> response = transferDao.getListOfAllTransfers(userid);
        return response;
    }



}
