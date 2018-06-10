package pl.damiankotynia.bankingsystem.service;


import pl.damiankotynia.bankingsystem.database.Database;
import pl.damiankotynia.bankingsystem.model.User;
import pl.damiankotynia.bankingsystem.view.PrintLayout;

import javax.xml.crypto.Data;
import java.util.DoubleSummaryStatistics;
import java.util.Set;

public class SearchService {
    private Database database;
    private InputService inputService;

    public SearchService(Database database){
        this.database = database;
        inputService = new InputService();
    }

    public void search(){
        boolean exit = false;

        while(!exit) {
            PrintLayout.showSearchWindow();
            int option = inputService.getSearchWindowInput();
            switch (option) {
                case 1:
                    searchByName();
                    break;
                case 2:
                    searchBySurname();
                    break;
                case 3:
                    searchByPesel();
                    break;
                case 4:
                    searchByAccountNumber();
                    break;
                case 5:
                    searchByAddress();
                    break;
                case 0:
                    System.out.println("Chcesz zakonczyc wyszukiwanie");
                    if(inputService.getAreYouSure())
                        exit=true;       
                    break;
            }
        }      
    }

    private void searchByName(){
        System.out.println("Podaj wyszukiwane imie: \n");
        PrintLayout.showUsers(database.findUserByName(inputService.getString()));
    }
    private void searchBySurname(){
        System.out.println("Podaj wyszukiwane nazwisko: \n");
        PrintLayout.showUsers(database.findUserBySurname(inputService.getString()));
    }
    private void searchByPesel(){
        System.out.println("Podaj wyszukiwany pesel: \n");
        PrintLayout.showUser(database.findUserByPesel(inputService.getPesel()));

    }
    private void searchByAccountNumber(){
        System.out.println("Podaj wyszukiwany numer konta: \n");
        PrintLayout.showUser(database.findUserById(inputService.getLong()));
    }

    private void searchByAddress(){
        System.out.println("Podaj wyszukiwany adres: \n");
        PrintLayout.showUsers(database.findByAddress(inputService.getString()));
    }
}