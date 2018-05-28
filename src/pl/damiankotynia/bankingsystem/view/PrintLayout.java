package pl.damiankotynia.bankingsystem.view;


import pl.damiankotynia.bankingsystem.model.User;
import java.util.Set;

public class PrintLayout {

    public static void mainMenu() {
        System.out.println("\n\n####################################################");
        System.out.println("System bankowy");
        System.out.println("Wybierz zadanie");
        System.out.println("[1] wypisz wszystkich urzytkownikow");
        System.out.println("[2] dodaj urzytkownika");
        System.out.println("[3] usun urzytkownika");
        System.out.println("[4] dokonaj transakcji");
        System.out.println("####################################################\n\n");
    }

    public static void showUsers(Set<User> users) {
        for (User user : users) {
            System.out.println("\n////////////////////////////////////////////////////");
            System.out.println("Id: " + user.getId());
            System.out.println("Imie: " + user.getName());
            System.out.println("Nazwisko: " + user.getSurname());
            System.out.println("Adres: " + user.getAddress());
            System.out.println("PESEL: " + user.getPesel());
            System.out.println("Saldo: " + user.getCash());
        }
    }

}
