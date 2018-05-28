package pl.damiankotynia.bankingsystem.database;

import pl.damiankotynia.bankingsystem.model.User;

import java.util.List;


public interface Database {
    void loadDatabase();
    User findUserById(long id);
    List<User> getAllUsers();
    void addUser(User user);
    void saveDatabase();
    void createDatabase();
}
