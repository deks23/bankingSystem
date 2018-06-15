package pl.damiankotynia.bankingsystem;

import pl.damiankotynia.bankingsystem.database.Database;
import pl.damiankotynia.bankingsystem.database.DatabaseImpl;

import pl.damiankotynia.bankingsystem.service.InputService;
import pl.damiankotynia.bankingsystem.service.SearchService;
import pl.damiankotynia.bankingsystem.service.TransactionService;
import pl.damiankotynia.bankingsystem.service.UserService;
import pl.damiankotynia.bankingsystem.view.PrintLayout;


public class Main {

    public static void main(String[] args) {
        Database database = new DatabaseImpl();
        UserService userService = new UserService(database);
        InputService inputService = new InputService();
        SearchService searchService = new SearchService(database);
        TransactionService transactionService = new TransactionService(database);
        boolean exit = false;
        //database.loadDatabase();

        while(!exit) {
            PrintLayout.mainMenu();
            int option = inputService.getMainMenuInput();
            switch (option) {
                case 1:
                    System.out.println("");
                    PrintLayout.showUsers(userService.getUsers());
                    break;
                case 2:
                    System.out.println("");
                    userService.addUser();
                    break;
                case 3:
                    System.out.println("");
                    userService.removeUser();
                    break;
                case 4:
                    transactionService.transferMoney();
                    break;
                case 5:
                    System.out.println("Usuwasz baze danych");
                    if(inputService.getAreYouSure())
                        database.dropDatabase();
                    break;
                case 6:
                    System.out.println("Tworzysz nowa baze danych");
                    if(inputService.getAreYouSure())
                        //database.createDatabase();
                    break;
                case 7:
                    transactionService.depositMoney();
                    break;
                case 8:
                    transactionService.withdrawMoney();
                    break;
                case 9:
                    searchService.search();
                    break;
                case 0:
                    System.out.println("Opuszczasz program");
                    if(inputService.getAreYouSure())
                        return;
                    break;
            }
        }
        //database.saveDatabase();

    }
}
