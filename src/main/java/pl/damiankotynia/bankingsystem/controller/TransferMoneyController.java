package pl.damiankotynia.bankingsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.damiankotynia.bankingsystem.BankingSystemGraphicInterface;
import pl.damiankotynia.bankingsystem.service.DataValidationService;
import pl.damiankotynia.bankingsystem.service.TransactionService;

public class TransferMoneyController {
    @FXML
    private TextField sourceAccountNumberField;

    @FXML
    private TextField targetAccountNumberField;

    @FXML
    private TextField cashAmmountField;

    @FXML
    private Button clearButton;

    @FXML
    private Button transferButton;

    private BankingSystemGraphicInterface mainApp;

    private TransactionService transactionService;

    public TransferMoneyController (){
        transactionService = new TransactionService();
    }
    @FXML
    private void initialize(){
    }

    public void setMainApp(BankingSystemGraphicInterface mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    public void clearButtonClick(){
        sourceAccountNumberField.setText("");
        targetAccountNumberField.setText("");
        cashAmmountField.setText("");
    }


    @FXML
    private void transferButtonClick(){
        Long sourceAccountNumber = DataValidationService.validateLong(sourceAccountNumberField.getCharacters().toString());
        Long targetAccountNumber = DataValidationService.validateLong(targetAccountNumberField.getCharacters().toString());
        if (sourceAccountNumber==-1L){
            sourceAccountNumberField.setText("");
            showAlert("Błąd", "Podano zły numer konta źródłowego. Numer konta musi być liczbą dodatnią");
            return;
        }
        if (targetAccountNumber==-1L){
            targetAccountNumberField.setText("");
            showAlert("Błąd", "Podano zły numer konta docelowego. Numer konta musi być liczbą dodatnią");
            return;
        }

        Double cashAmmount = DataValidationService.validateDouble(cashAmmountField.getCharacters().toString());
        if (cashAmmount<0){
            cashAmmountField.setText("");
            showAlert("Błąd", "Podano niewłaściwą kwotę");
            return;
        }
        if(transactionService.transferMoney(sourceAccountNumber, targetAccountNumber, cashAmmount, this)){
            clearButtonClick();
            showSucces();
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
        alert.setHeaderText("Pieniądze zostały przelane pomyślnie");
        alert.showAndWait();
    }
}
