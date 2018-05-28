package pl.damiankotynia.bankingsystem.service;


import pl.damiankotynia.bankingsystem.database.Database;
import pl.damiankotynia.bankingsystem.model.User;
import pl.damiankotynia.bankingsystem.view.PrintLayout;

public class UserService {
    private InputService inputService;
    private Database database;

    public UserService(Database database){
        inputService = new InputService();
        this.database = database;
    }

    public void addUser(){
        User user = new User();
        System.out.println("Podaj imie:\n");
        user.setName(inputService.getString());
        System.out.println("Podaj nazwisko:\n");
        user.setSurname(inputService.getString());
        System.out.println("Podaj PESEL:\n");
        user.setPesel(inputService.getLong());
        System.out.println("Podaj adres:\n");
        user.setAddress(inputService.getString());
        System.out.println("Podaj stan konta:\n");
        user.setCash(inputService.getLong());

        database.addUser(user);

    }

    public void removeUser(){
        System.out.println("Podaj PESEL urzytkownika ktorego chcesz usunac");
        database.removeUser(inputService.getLong());
    }

    public void showUsers(){
        PrintLayout.showUsers(database.getAllUsers());
    }

}

