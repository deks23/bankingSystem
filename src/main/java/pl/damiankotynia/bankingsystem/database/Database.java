package pl.damiankotynia.bankingsystem.database;

import pl.damiankotynia.bankingsystem.model.User;

import java.util.List;
import java.util.Set;


public interface Database {
    User findUserById(long id);
    Set<User> findUserByName(String name);
    Set<User> findUserBySurname(String surname);
    User findUserByPesel(long pesel);
    Set<User> getAllUsers();
    Set<User> findByAddress(String address);
    boolean addUser(User user);
    boolean removeUser(Long id);
    boolean dropDatabase();
    boolean saveUserData(User user);


 /*   boolean createDatabase();
    void saveDatabase();
    void loadDatabase();*/
}
