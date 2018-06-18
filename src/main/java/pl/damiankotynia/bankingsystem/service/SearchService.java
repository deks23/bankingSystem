package pl.damiankotynia.bankingsystem.service;


import javafx.scene.control.Alert;
import pl.damiankotynia.bankingsystem.controller.SearchController;
import pl.damiankotynia.bankingsystem.database.Database;
import pl.damiankotynia.bankingsystem.database.DatabaseImpl;
import pl.damiankotynia.bankingsystem.model.User;

import javax.xml.crypto.Data;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.Set;

public class SearchService {
    private Database database;

    public SearchService(){
        database = new DatabaseImpl();
    }

    public Set<User> searchByName(String searchPhrase){
       return database.findUserByName(searchPhrase);
    }

    public Set<User> searchBySurname(String searchPhrase){
        return database.findUserBySurname(searchPhrase);
    }

    public Set<User> searchByAddress(String searchPhrase){
        return database.findByAddress(searchPhrase);
    }

    public Set<User> searchBypesel(Long searchPhrase){
        Set<User> users = new HashSet<>();
        User user = database.findUserByPesel(searchPhrase);
        if (user==null)
            return users;
        else{
            users.add(user);
            return users;
        }
    }

    public Set<User> searchByAccountNumber(Long searchPhrase){
        Set<User> users = new HashSet<>();
        User user = database.findUserById(searchPhrase);
        if (user==null)
            return users;
        else{
            users.add(user);
            return users;
        }
    }

}