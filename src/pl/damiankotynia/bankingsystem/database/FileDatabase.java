package pl.damiankotynia.bankingsystem.database;

import pl.damiankotynia.bankingsystem.model.User;
import pl.damiankotynia.bankingsystem.service.InputService;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


public class FileDatabase implements Database {
    private Set<User> users;
    private FileInputStream fileIn = null;
    private ObjectInputStream inputStream = null;
    private FileOutputStream fileOut = null;
    private ObjectOutputStream out = null;
    private InputService inputService;
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
                System.out.println("Baza danych nie istnieje.");
                return;
            }finally {
                try {
                    if(fileIn!=null)
                        fileIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if(inputStream!=null)
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
    public boolean addUser(User user) {
        for (User searchUser : users)
            if(user.equals(searchUser))
                return false;
        users.add(user);
        saveDatabase();
        return true;
    }

    @Override
    public boolean createDatabase() {
        if(new File("users.ser").exists()){
            System.out.println("Baza danych juz istnieje");
            return false;
        }
        try {
            new File("users.ser").createNewFile();
            saveDatabase();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean dropDatabase() {
        File file = new File("users.ser");
        if(!file.exists()){
            System.out.println("Baza nie istnieje");
            return false;
        }
        file.delete();
        users.clear();
        return true;
    }

    @Override
    public boolean removeUser(Long id){
        inputService = new InputService();
        boolean found = false;
        User userToDelete = new User();
        for(User user : users){
            if(user.getId()==id){
                userToDelete = user;
                found = true;
                break;
            }
        }
        if(found){
            System.out.println("Usuwasz użytkownika:");
            System.out.println(userToDelete.toString());
            if(inputService.getAreYouSure()) {
                users.remove(userToDelete);
                saveDatabase();
                return true;
            }else {
                System.out.println("Anulowano operację");
                return false;
            }
        }else
            System.out.println("Nie znaleziono użytkownika");
        return false;
    }

    @Override
    public Set<User> findUserByName(String name) {
        Set<User> searchResult = new HashSet<>();
        for(User user : users){
            if(user.getName().toLowerCase().equals(name.toLowerCase()))
                searchResult.add(user);
        }
        return searchResult;
    }

    @Override
    public Set<User> findUserBySurname(String surname) {
        Set<User> searchResult = new HashSet<>();
        for(User user : users){
            if(user.getSurname().toLowerCase().equals(surname.toLowerCase()))
                searchResult.add(user);
        }
        return searchResult;
    }

    @Override
    public User findUserByPesel(long pesel) {
        for(User user : users){
            if(user.getPesel()==pesel)
                return user;
        }
        return null;
    }

    @Override
    public Set<User> findByAddress(String address) {
        Set<User> searchResult = new HashSet<>();
        for(User user : users){
            if(user.getAddress().toLowerCase().equals(address.toLowerCase()))
                searchResult.add(user);
        }
        return searchResult;
    }
}
