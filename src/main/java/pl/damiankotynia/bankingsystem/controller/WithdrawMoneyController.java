package pl.damiankotynia.bankingsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.damiankotynia.bankingsystem.BankingSystemGraphicInterface;

public class WithdrawMoneyController {
    @FXML
    private TextField accountNumberField;

    @FXML
    private TextField cashAmmountField;

    @FXML
    private Button clearButton;

    @FXML
    private Button withdrawButton;

    private BankingSystemGraphicInterface mainApp;

    @FXML
    public void clearButtonClick(){
        accountNumberField.setText("");
        cashAmmountField.setText("");
    }

    @FXML
    private void initialize(){
    }

    public void setMainApp(BankingSystemGraphicInterface mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    private void withdrawButtonClick(){
        cashAmmountField.getCharacters().toString();
        accountNumberField.getCharacters().toString();
    }


    private void showAlert(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showSucces(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukces");
        alert.setHeaderText("Pieniądze zostały wypłącone pomyślnie");
        alert.showAndWait();
    }
}
