package pl.damiankotynia.bankingsystem.model;


import java.util.List;

public class DatabaseObject {
    private long counter;
    private List<User> userList;

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
