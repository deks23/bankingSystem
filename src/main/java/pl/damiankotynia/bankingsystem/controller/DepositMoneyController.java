package pl.damiankotynia.bankingsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.damiankotynia.bankingsystem.BankingSystemGraphicInterface;
import pl.damiankotynia.bankingsystem.service.DataValidationService;
import pl.damiankotynia.bankingsystem.service.TransactionService;

public class DepositMoneyController {
    @FXML
    private TextField accountNumberField;

    @FXML
    private TextField cashAmmountField;

    @FXML
    private Button clearButton;

    @FXML
    private Button depositButton;

    private BankingSystemGraphicInterface mainApp;

    private TransactionService transactionService;

    public DepositMoneyController (){
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
        accountNumberField.setText("");
        cashAmmountField.setText("");
    }


    @FXML
    private void depositButtonClick(){
        Long accountNumber = DataValidationService.validateLong(accountNumberField.getCharacters().toString());
        if (accountNumber==-1L){
            accountNumberField.setText("");
            showAlert("Błąd", "Podano niewłaściwy numer konta");
            return;
        }

        Double cashAmmount = DataValidationService.validateDouble(cashAmmountField.getCharacters().toString());
        if (cashAmmount<0){
            cashAmmountField.setText("");
            showAlert("Błąd", "Podano niewłaściwą kwotę");
            return;
        }
        if(transactionService.depositMoney(accountNumber, cashAmmount, this)){
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
        alert.setHeaderText("Pieniądze zostały wpłacone pomyślnie");
        alert.showAndWait();
    }
}
