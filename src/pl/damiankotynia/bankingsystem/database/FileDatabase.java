package pl.damiankotynia.bankingsystem.database;

import pl.damiankotynia.bankingsystem.model.User;

import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class FileDatabase implements Database {
    private List<User> users;
    private FileInputStream fileIn = null;
    private ObjectInputStream inputStream = null;
    private FileOutputStream fileOut = null;
    private ObjectOutputStream out = null;

    @Override
    public void loadDatabase() {
        if(users==null){
            users = new LinkedList<>();
            try {
                fileIn = new FileInputStream("users.txt");
                inputStream = new ObjectInputStream(fileIn);
                try {
                    Object object = inputStream.readObject();
                    if(object!=null)
                        users = (LinkedList<User>)object;

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
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void saveDatabase() {
        try {

            fileOut = new FileOutputStream("users.txt");
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
    }

    @Override
    public void createDatabase() {
        try {
            new File("users.txt").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
