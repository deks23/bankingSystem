package pl.damiankotynia.bankingsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.damiankotynia.bankingsystem.BankingSystemGraphicInterface;
import pl.damiankotynia.bankingsystem.database.Database;
import pl.damiankotynia.bankingsystem.database.DatabaseImpl;
import pl.damiankotynia.bankingsystem.model.User;
import pl.damiankotynia.bankingsystem.service.DataValidationService;
import pl.damiankotynia.bankingsystem.service.UserService;


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

    private UserService userService;

    @FXML
    private void initialize(){

    }
    public AddUserController(){
        userService = new UserService();
    }


    public void setMainApp(BankingSystemGraphicInterface mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    public void clearButtonClick(){
        nameTextField.setText("");
        surnameTextField.setText("");
        peselTextField.setText("");
        addressTextField.setText("");
        cashTextField.setText("");
    }

    @FXML
    private void addButtonClick(){
        String name = nameTextField.getCharacters().toString();
        String surname = surnameTextField.getCharacters().toString();
        String address = addressTextField.getCharacters().toString();

        if (name.equals("")||surname.equals("")||address.equals("")){
            showAlert("Podano niepoprawne dane", "Nie podano wymaganych danych", "Proszę podać wszystkie wymagane dane");
            return;
        }

        Long pesel = DataValidationService.validatePesel(peselTextField.getCharacters().toString());
        if (pesel<0){
            peselTextField.setText("");
            showAlert("Podano błędne dane", "Niepoprawny pesel", "Pesel musi składać się wyłącznie z 11 cyfr oraz być liczbą dodatnią");
            return;
        }
        Double cash = DataValidationService.validateDouble(cashTextField.getCharacters().toString());
        if (cash<0){
            cashTextField.setText("");
            showAlert("Podano błędne dane", "Niepoprawna kwota", "Kwota musi składać się wyłącznie z cyfr oraz być liczbą dodatnią");
            return;
        }
        User user = new User();
        user.setPesel(pesel);
        user.setAddress(address);
        user.setCash(cash);
        user.setName(name);
        user.setSurname(surname);
        if(!userService.addUser(user)){
            showAlert("Błąd", "Wystąpił nieoczekiwany błąd", "Podany pesel istnieje już w bazie danych");
        }
        clearButtonClick();
        showSucces();

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
        alert.setTitle("Poprawnie dodano uzytkownika");
        alert.setHeaderText("Użytkownik został dodany poprawnie");
        alert.showAndWait();
    }

}
