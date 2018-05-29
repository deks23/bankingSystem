package pl.damiankotynia.bankingsystem.view;


import pl.damiankotynia.bankingsystem.model.User;
import java.util.Set;

public class PrintLayout {

    public static void mainMenu() {
        System.out.println("\n\n####################################################");
        System.out.println("System bankowy");
        System.out.println("Wybierz zadanie");
        System.out.println("[1] Wypisz wszystkich urzytkownikow");
        System.out.println("[2] Dodaj urzytkownika");
        System.out.println("[3] Usun urzytkownika");
        System.out.println("[4] Dokonaj transakcji");
        System.out.println("[5] Usun baze danych");
        System.out.println("[6] Stworz baze danych");
        System.out.println("[7] Wplac pieniadze");
        System.out.println("[8] Wyplac pieniadze");
        System.out.println("[9] Wyjdz");
        System.out.println("####################################################\n\n");
    }

    public static void showUsers(Set<User> users) {
        if(users.size()==0){
            System.out.println("Baza danych jest pusta");
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

}
