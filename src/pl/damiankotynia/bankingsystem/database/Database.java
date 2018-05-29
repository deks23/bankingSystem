package pl.damiankotynia.bankingsystem.database;

import pl.damiankotynia.bankingsystem.model.User;

import java.util.List;
import java.util.Set;


public interface Database {
    void loadDatabase();
    User findUserById(long id);
    Set<User> getAllUsers();
    boolean addUser(User user);
    void saveDatabase();
    boolean createDatabase();
    boolean removeUser(Long id);
    boolean dropDatabase();
}
