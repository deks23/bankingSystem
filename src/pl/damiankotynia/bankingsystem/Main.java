package pl.damiankotynia.bankingsystem;

import pl.damiankotynia.bankingsystem.database.Database;
import pl.damiankotynia.bankingsystem.database.FileDatabase;
import pl.damiankotynia.bankingsystem.model.User;
import pl.damiankotynia.bankingsystem.service.InputService;
import pl.damiankotynia.bankingsystem.service.UserService;
import pl.damiankotynia.bankingsystem.view.PrintLayout;

import java.io.File;


public class Main {

    public static void main(String[] args) {
        Database database = new FileDatabase();
        UserService userService = new UserService(database);
        InputService inputService = new InputService();
        boolean exit = false;
        database.loadDatabase();


        while(!exit) {
            PrintLayout.mainMenu();
            int option = inputService.getInt();
            switch (option) {
                case 1:
                    userService.showUsers();
                    break;
                case 2:
                    userService.addUser();
                    break;
                case 3:
                    userService.removeUser();
                    break;
                case 4:
                    System.out.println("dokonaj transakcji");
                    break;
                case 5:
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
