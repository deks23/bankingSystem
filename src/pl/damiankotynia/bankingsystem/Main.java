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
        //database.createDatabase();

        database.loadDatabase();

        UserService userService = new UserService(database);

        //userService.addUser();

        database.getAllUsers();
        //PrintLayout.mainMenu();
       /*   User u1 = new User();
        u1.setName("qwe");
        User u2 = new User();
        u2.setName("asd");

        database.getAllUsers().add(u1);
        database.getAllUsers().add(u2);*/

        PrintLayout.showUsers(database.getAllUsers());

        //PrintLayout.showUsers(database.getAllUsers());

        database.saveDatabase();

    }
}
