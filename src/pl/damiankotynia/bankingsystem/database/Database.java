package pl.damiankotynia.bankingsystem.database;

import pl.damiankotynia.bankingsystem.model.User;

import java.util.List;
import java.util.Set;


public interface Database {
    void loadDatabase();
    User findUserById(long id);
    Set<User> findUserByName(String name);
    Set<User> findUserBySurname(String surname);
    User findUserByPesel(long pesel);
    Set<User> getAllUsers();
    Set<User> findByAddress(String address);
    boolean addUser(User user);
    void saveDatabase();
    boolean createDatabase();
    boolean removeUser(Long id);
    boolean dropDatabase();
}
