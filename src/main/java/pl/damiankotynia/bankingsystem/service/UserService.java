package pl.damiankotynia.bankingsystem.service;


import pl.damiankotynia.bankingsystem.database.Database;
import pl.damiankotynia.bankingsystem.database.DatabaseImpl;
import pl.damiankotynia.bankingsystem.model.User;

import java.util.Random;
import java.util.Set;

public class UserService {
    private Database database;

    public  UserService(){
        database = new DatabaseImpl();
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

