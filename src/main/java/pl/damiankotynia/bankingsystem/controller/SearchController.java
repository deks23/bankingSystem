package pl.damiankotynia.bankingsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.damiankotynia.bankingsystem.BankingSystemGraphicInterface;
import pl.damiankotynia.bankingsystem.model.User;
import pl.damiankotynia.bankingsystem.service.DataValidationService;
import pl.damiankotynia.bankingsystem.service.SearchService;

import java.util.Set;

public class SearchController {
    @FXML
    private Button nameSearchButton;

    @FXML
    private Button surnameSearchButton;

    @FXML
    private Button peselSearchButton;

    @FXML
    private Button accountNumberSearchButton;

    @FXML
    private Button addressSearchButton;

    @FXML
    private TextField searchPhraseField;

    private BankingSystemGraphicInterface mainApp;

    private SearchService searchService;

    @FXML
    private void initialize(){

    }

    public SearchController(){
        searchService = new SearchService();
    }

    public void setMainApp(BankingSystemGraphicInterface mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void nameSearchClick(){
        String searchPhrase =  searchPhraseField.getCharacters().toString();
        if(searchPhrase.equals(""))
            showAlert("Brak danych", "Pole wyszukiwania jest puste");
        else{
            Set<User> users = searchService.searchByName(searchPhrase);
            if (users.size()==0){
                showAlert("Błąd", "W bazie danych nie istnieje podany użytkownik");
                return;
            }
            else
            mainApp.showSearchedUserList(users);
        }

    }

    @FXML
    private void surnameSearchClick(){
        String searchPhrase =  searchPhraseField.getCharacters().toString();
        if(searchPhrase.equals(""))
            showAlert("Brak danych", "Pole wyszukiwania jest puste");
        else{
            Set<User> users = searchService.searchBySurname(searchPhrase);
            if (users.size()==0){
                showAlert("Błąd", "W bazie danych nie istnieje podany użytkownik");
                return;
            }
            mainApp.showSearchedUserList(users);
        }
    }

    @FXML
    private void peselSearchClick(){
        String searchPhrase =  searchPhraseField.getCharacters().toString();
        if(searchPhrase.equals("")) {
            showAlert("Brak danych", "Pole wyszukiwania jest puste");
            return;
        }

        Long pesel =  DataValidationService.validatePesel(searchPhrase);
        if(pesel<0){
            showAlert("Błąd", "Podany pesel jest niepoprawny");
            return;
        }
        else{
            Set<User> users = searchService.searchBypesel(pesel);
            if (users.size()==0){
                showAlert("Błąd", "W bazie danych nie istnieje podany użytkownik");
                return;
            }
            mainApp.showSearchedUserList(users);
        }
    }

    @FXML
    private void addressSearchClick(){
        String searchPhrase =  searchPhraseField.getCharacters().toString();
        if(searchPhrase.equals(""))
            showAlert("Brak danych", "Pole wyszukiwania jest puste");
        else{
            Set<User> users = searchService.searchByAddress(searchPhrase);
            if (users.size()==0){
                showAlert("Błąd", "W bazie danych nie istnieje podany użytkownik");
                return;
            }
            mainApp.showSearchedUserList(users);
        }
    }

    @FXML
    private void accountNumberSearchClick(){
        String searchPhrase =  searchPhraseField.getCharacters().toString();
        if(searchPhrase.equals("")) {
            showAlert("Brak danych", "Pole wyszukiwania jest puste");
            return;
        }

        Long accountNumber =  DataValidationService.validateLong(searchPhrase);
        if(accountNumber<0){
            showAlert("Błąd", "Podany numer konta jest niepoprawny");
            return;
        }
        else{
            Set<User> users = searchService.searchByAccountNumber(accountNumber);
            if (users.size()==0){
                showAlert("Błąd", "W bazie danych nie istnieje podany użytkownik");
                return;
            }
            mainApp.showSearchedUserList(users);
        }
    }



    public void showAlert(String title, String header){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    private void showSucces(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukces");
        alert.setHeaderText("Pieniądze zostały wpłacone pomyślnie");
        alert.showAndWait();
    }
}
