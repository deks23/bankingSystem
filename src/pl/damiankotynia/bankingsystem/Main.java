package pl.damiankotynia.bankingsystem;

import pl.damiankotynia.bankingsystem.database.Database;
import pl.damiankotynia.bankingsystem.database.FileDatabase;
import pl.damiankotynia.bankingsystem.model.User;
import pl.damiankotynia.bankingsystem.service.InputService;
import pl.damiankotynia.bankingsystem.service.TransactionService;
import pl.damiankotynia.bankingsystem.service.UserService;
import pl.damiankotynia.bankingsystem.view.PrintLayout;

import java.io.File;


public class Main {

    public static void main(String[] args) {
        Database database = new FileDatabase();
        UserService userService = new UserService(database);
        InputService inputService = new InputService();
        TransactionService transactionService = new TransactionService(database);
        boolean exit = false;
        database.loadDatabase();


       /* User u1 = new User();
        u1.setName("qwe");
        u1.setCash(123123.1231);
        u1.setId(3L);
        User u2 = new User();
        u2.setName("asd");
        u2.setCash(12.3);
        u2.setId(2L);


        database.addUser(u1);
        database.addUser(u2);*/
        while(!exit) {
            PrintLayout.mainMenu();
            int option = inputService.getMainMenuInput();
            switch (option) {
                case 1:
                    System.out.println("");
                    userService.showUsers();
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
                        database.createDatabase();
                    break;
                case 7:
                    transactionService.depositMoney();
                    break;
                case 8:
                    transactionService.withdrawMoney();
                    break;
                case 9:
                    System.out.println("Opuszczasz program");
                    if(inputService.getAreYouSure())
                        exit=true;
                    break;

            }
        }
        //userService.addUser();
        //PrintLayout.mainMenu();
       /*   User u1 = new User();
        u1.setName("qwe");
        User u2 = new User();
        u2.setName("asd");

        database.getAllUsers().add(u1);
        database.getAllUsers().add(u2);*/

       // PrintLayout.showUsers(database.getAllUsers());
        //userService.removeUser();
        //PrintLayout.showUsers(database.getAllUsers());

        database.saveDatabase();

    }
}
