package com.techelevator.tenmo;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TransferService;
import com.techelevator.tenmo.services.UserService;

import java.math.BigDecimal;
import java.util.Arrays;

public class App {
    private int balance;

    private static final String API_BASE_URL = "http://localhost:8080/";
    private UserService userService = new UserService();
    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private AuthenticatedUser currentUser;
    TransferService transferService = new TransferService();

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    public AuthenticatedUser getCurrentUser() {
        return currentUser;
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }

    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 6) {
                listUsers();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

    private void viewCurrentBalance() {
//        UserService userService = new UserService();
        BigDecimal balance = userService.getBalance(currentUser);
        System.out.println("--------------------------------------------");
        System.out.println("Your balance is $" + balance);
        System.out.println("--------------------------------------------");


    }

    private void viewTransferHistory() {
//        TransferService transferService = new TransferService();
        Transfer[] transfers = transferService.getTransfersFromUserId(currentUser);
        System.out.println("--------------------------------------------");
        System.out.println("Your transfers are ");
        if (transfers == null) {
            System.out.print("nothing lol");
        } else {
            for (Transfer transfer : transfers) {
                System.out.println(transfer.getAccountFrom());
            }
        }
        System.out.println();
        System.out.println("--------------------------------------------");

    }

    private void viewPendingRequests() {
        Transfer[] transfers = transferService.viewPendingTransfers(currentUser);
        System.out.println("--------------------------------------------");
        System.out.println("Your transfers are " + Arrays.toString(transfers));
        if (transfers == null) {
            System.out.print("nothing lol");
        } else {
            for (Transfer transfer : transfers) {
                System.out.println(transfer.getAccountFrom());
            }
            System.out.println("--------------------------------------------");
        }
    }
        public void sendBucks () {
//        balance = balance - amountToSend;
//        return balance;
//		// TODO Auto-generated method stub
//
        }

        public void requestBucks () {
//        balance = balance + amountToRequest;
//        return balance;
//		// TODO Auto-generated method stub
//
        }

        private void listUsers () {
            User[] users = userService.listUsers(currentUser);
            for (User user : users) {
                System.out.println(user.getUsername());
            }

        }

    }
