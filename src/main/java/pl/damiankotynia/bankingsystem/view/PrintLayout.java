package pl.damiankotynia.bankingsystem.view;


import pl.damiankotynia.bankingsystem.model.User;

import java.util.Set;

public class PrintLayout {

    public static void mainMenu() {
        System.out.println("\n\n####################################################");
        System.out.println("System bankowy");
        System.out.println("Wybierz zadanie");
        System.out.println("[1] Wypisz wszystkich uzytkownikow");
        System.out.println("[2] Dodaj urzytkownika");
        System.out.println("[3] Usun urzytkownika");
        System.out.println("[4] Dokonaj transakcji");
        System.out.println("[5] Usun baze danych");
        System.out.println("[6] Stworz baze danych");
        System.out.println("[7] Wplac pieniadze");
        System.out.println("[8] Wyplac pieniadze");
        System.out.println("[9] Wyszukaj uzytkownika");
        System.out.println("[0] Wyjdz");
        System.out.println("####################################################\n\n");
    }

    public static void showUsers(Set<User> users) {
        if(users.size()==0){
            System.out.println("Brak uzytkownikow");
        }else {
            for (User user : users) {
                System.out.println("\n////////////////////////////////////////////////////");
                System.out.println("Numer konta: " + user.getId());
                System.out.println("Imie: " + user.getName());
                System.out.println("Nazwisko: " + user.getSurname());
                System.out.println("Adres: " + user.getAddress());
                System.out.println("PESEL: " + user.getPesel());
                System.out.println("Saldo: " + user.getCash());

            }
        }
    }

    public static void showSearchWindow(){
        System.out.println("\n\n####################################################");
        System.out.println("[1]Wyszukaj po imieniu");
        System.out.println("[2] Wyszukaj po nazwisku");
        System.out.println("[3] Wyszukaj po numerze pesel");
        System.out.println("[4] Wyszukaj po numerze konta");
        System.out.println("[5] Wyszukaj po adresie");
        System.out.println("[0] Powróć do menu głównego");
    }

    public static void showUser(User user) {
        if(user==null){
            System.out.println("Brak podanego uzytkownika");
        }else {

            System.out.println("\n////////////////////////////////////////////////////");
            System.out.println("Numer konta: " + user.getId());
            System.out.println("Imie: " + user.getName());
            System.out.println("Nazwisko: " + user.getSurname());
            System.out.println("Adres: " + user.getAddress());
            System.out.println("PESEL: " + user.getPesel());
            System.out.println("Saldo: " + user.getCash());

        }
    }
}
