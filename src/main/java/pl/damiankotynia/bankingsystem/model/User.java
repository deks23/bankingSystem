package pl.damiankotynia.bankingsystem.model;

import javafx.beans.property.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class User implements Serializable{
    @Id
    private long id;
    private String name;
    private  String surname;
    private String address;
    private long pesel;
    private double cash;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getPesel() {
        return pesel;
    }

    public void setPesel(long pesel) {
        this.pesel = pesel;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        cash *=100;
        long qwe = Math.round(cash);
        cash = (double)qwe/100;
        this.cash = cash;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StringProperty nameProperty(){
        return new SimpleStringProperty(name);
    }

    public StringProperty surnameProperty(){
        return new SimpleStringProperty(surname);
    }

    public StringProperty addressProperty(){
        return new SimpleStringProperty(address);
    }

    public LongProperty peselProperty(){
        return new SimpleLongProperty(pesel);
    }

    public DoubleProperty cashProperty(){
        return new SimpleDoubleProperty(cash);
    }
    public LongProperty idProperty(){
        return new SimpleLongProperty(id);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (pesel != user.pesel) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        return address != null ? address.equals(user.address) : user.address == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (int) (pesel ^ (pesel >>> 32));
        temp = Double.doubleToLongBits(cash);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString(){
        return "#Numer konta: " + id + "\n#Imie: " + name + "\n#Nazwisko: " + surname + "\n#Adres:" + address + "\n#PESEL:" +pesel;
    }
}
