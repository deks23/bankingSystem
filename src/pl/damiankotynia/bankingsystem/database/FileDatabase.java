package pl.damiankotynia.bankingsystem.database;

import pl.damiankotynia.bankingsystem.model.User;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;


public class FileDatabase implements Database {
    private Set<User> users;
    private FileInputStream fileIn = null;
    private ObjectInputStream inputStream = null;
    private FileOutputStream fileOut = null;
    private ObjectOutputStream out = null;

    @Override
    public void loadDatabase() {
        if(users==null){
            users = new LinkedHashSet<>();
            try {
                fileIn = new FileInputStream("users.ser");
                inputStream = new ObjectInputStream(fileIn);
                try {
                    Object object = inputStream.readObject();
                    if(object!=null)
                        users = (LinkedHashSet<User>)object;

                    System.out.println("Baza danych zostala zaladowana");

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException i) {
                i.printStackTrace();
            }finally {
                try {
                    fileIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else
            System.out.println("Baza zaostala wczesniej zaladowana");
    }

    @Override
    public User findUserById(long id) {
        for(User user : users){
            if(user.getId()==id)
                return user;
        }
        return null;
    }

    @Override
    public Set<User> getAllUsers() {
        return users;
    }

    @Override
    public void saveDatabase() {
        try {

            fileOut = new FileOutputStream("users.ser");
            out = new ObjectOutputStream(fileOut);
            out.flush();
            out.writeObject(users);

        } catch (IOException i) {
            i.printStackTrace();
        }finally {
            try {
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
        saveDatabase();
    }

    @Override
    public void createDatabase() {
        try {
            new File("users.ser").createNewFile();
            saveDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(Long pesel){
        User userToDelete = new User();
        for(User user : users){
            if(user.getPesel()==pesel){
                userToDelete = user;
                break;
            }
        }
        users.remove(userToDelete);
        saveDatabase();
    }
}
