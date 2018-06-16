package pl.damiankotynia.bankingsystem.service;


import pl.damiankotynia.bankingsystem.database.Database;
import pl.damiankotynia.bankingsystem.database.DatabaseImpl;
import pl.damiankotynia.bankingsystem.model.User;
import pl.damiankotynia.bankingsystem.view.PrintLayout;

import java.util.Random;
import java.util.Set;

public class UserService {
    private InputService inputService;
    private Database database;

    public UserService(Database database){
        inputService = new InputService();
        this.database = database;
    }
    public  UserService(){
        database = new DatabaseImpl();
    }

    public void addUser(){
        User user = new User();
        boolean validPesel = false;
        user.setId(generateID());
        System.out.println("Podaj imie:\n");
        user.setName(inputService.getString());
        System.out.println("Podaj nazwisko:\n");
        user.setSurname(inputService.getString());
        System.out.println("Podaj PESEL:\n");
        long pesel = 0;
        do{
             pesel = inputService.getPesel();
             if(database.findUserByPesel(pesel)==null)
                 validPesel=true;
             else
                 System.out.println("Podany pesel istnieje w bazie danych");
        }while (!validPesel);
        user.setPesel(pesel);

        System.out.println("Podaj adres:\n");
        user.setAddress(inputService.getString());
        System.out.println("Podaj stan konta:\n");
        user.setCash(inputService.getDouble());

        if(database.addUser(user))
            System.out.println("Użytkownik został dodany poprawnie");
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


    public Set<User> getUsers(){
        return database.getAllUsers();
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

    public boolean removeUser(Long id){
        return database.removeUser(id);
    }

    public boolean addUser(User user){
        if (database.findUserByPesel(user.getPesel())!=null)
            return false;
        user.setId(generateID());
        return database.addUser(user);
    }
}

