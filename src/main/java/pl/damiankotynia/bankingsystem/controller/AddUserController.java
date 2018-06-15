package pl.damiankotynia.bankingsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.damiankotynia.bankingsystem.BankingSystemGraphicInterface;
import pl.damiankotynia.bankingsystem.database.Database;
import pl.damiankotynia.bankingsystem.database.DatabaseImpl;
import pl.damiankotynia.bankingsystem.model.User;


public class AddUserController {
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField peselTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField cashTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    private BankingSystemGraphicInterface mainApp;

    @FXML
    private void initialize(){

    }

    public void setMainApp(BankingSystemGraphicInterface mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    private void clearButtonClick(){
        nameTextField.setText("");
        surnameTextField.setText("");
        peselTextField.setText("");
        addressTextField.setText("");
        cashTextField.setText("");
    }

    @FXML
    private void addButtonClick(){
        Database database = new DatabaseImpl();
        String name = nameTextField.getCharacters().toString();
        String surname = surnameTextField.getCharacters().toString();
        String address = addressTextField.getCharacters().toString();

        Long pesel = Long.parseLong(peselTextField.getCharacters().toString());
        Double cash = Double.parseDouble(cashTextField.getCharacters().toString());

        User user = new User();
        user.setPesel(pesel);
        user.setAddress(address);
        user.setCash(cash);
        user.setName(name);
        user.setSurname(surname);
        database.addUser(user);


        /*Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Podano błędne dane");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();*/

    }

}
