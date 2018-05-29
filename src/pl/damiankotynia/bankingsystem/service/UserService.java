package pl.damiankotynia.bankingsystem.service;


import pl.damiankotynia.bankingsystem.database.Database;
import pl.damiankotynia.bankingsystem.model.User;
import pl.damiankotynia.bankingsystem.view.PrintLayout;

import java.util.Random;

public class UserService {
    private InputService inputService;
    private Database database;

    public UserService(Database database){
        inputService = new InputService();
        this.database = database;
    }

    public void addUser(){
        User user = new User();
        user.setId(generateID());
        System.out.println("Podaj imie:\n");
        user.setName(inputService.getString());
        System.out.println("Podaj nazwisko:\n");
        user.setSurname(inputService.getString());
        System.out.println("Podaj PESEL:\n");
        user.setPesel(inputService.getPesel());
        System.out.println("Podaj adres:\n");
        user.setAddress(inputService.getString());
        System.out.println("Podaj stan konta:\n");
        user.setCash(inputService.getDouble());

        if(database.addUser(user))
            System.out.println("Użytkownik zaostał dodany poprawnie");
        else
            System.out.println("Błąd przy dodawaniu użytkownika");

    }

    public void removeUser(){
        System.out.println("Podaj numer konta ktore chcesz usunac");
        if(database.removeUser(inputService.getLong()))
            System.out.println("\nUsunieto użytkownika");
        else
            System.out.println("\nOperacja nie powiodla sie, sproboj ponownie");
    }

    public void showUsers(){
        PrintLayout.showUsers(database.getAllUsers());
    }

    private long generateID(){
        long id;
        User user;
        do {
            user = null;
            id = (long)(Math.random() * 200 + 10);
            user = database.findUserById(id);
        }while(user!=null);
        return id;
    }
}

