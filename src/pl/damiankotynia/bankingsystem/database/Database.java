package pl.damiankotynia.bankingsystem.database;

import pl.damiankotynia.bankingsystem.model.User;

import java.util.List;
import java.util.Set;


public interface Database {
    void loadDatabase();
    User findUserById(long id);
    Set<User> getAllUsers();
    void addUser(User user);
    void saveDatabase();
    void createDatabase();
    void removeUser(Long pesel);

}
