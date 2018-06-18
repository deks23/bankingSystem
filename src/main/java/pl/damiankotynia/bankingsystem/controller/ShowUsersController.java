package pl.damiankotynia.bankingsystem.controller;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.omg.CORBA.PRIVATE_MEMBER;
import pl.damiankotynia.bankingsystem.BankingSystemGraphicInterface;
import pl.damiankotynia.bankingsystem.model.User;
import pl.damiankotynia.bankingsystem.service.UserService;


public class ShowUsersController {
    @FXML
    private TableView <User> tableView;
    @FXML
    private TableColumn <User, Long> accountNumberColumn;
    @FXML
    private TableColumn <User, String> nameColumn;
    @FXML
    private TableColumn <User, String> surnameColumn;
    @FXML
    private TableColumn <User, Long> peselColumn;
    @FXML
    private TableColumn <User, String> addressColumn;
    @FXML
    private TableColumn <User, Double> cashColumn;
    @FXML
    private Button deleteButton;

    private User selectedUser;

    private UserService userService;
    private BankingSystemGraphicInterface mainApp;
    public ShowUsersController(){
        userService = new UserService();
    }

    @FXML
    private void initialize(){
        accountNumberColumn.setCellValueFactory(cellData-> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData-> cellData.getValue().nameProperty());
        surnameColumn.setCellValueFactory(cellData-> cellData.getValue().surnameProperty());
        nameColumn.setCellValueFactory(cellData-> cellData.getValue().nameProperty());
        peselColumn.setCellValueFactory(cellData-> cellData.getValue().peselProperty().asObject());
        addressColumn.setCellValueFactory(cellData-> cellData.getValue().addressProperty());
        cashColumn.setCellValueFactory(cellData-> cellData.getValue().cashProperty().asObject());

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setSelectedUser(newValue)
        );
    }

    public void setMainApp(BankingSystemGraphicInterface mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    private void deleteButtonClick(){
        if(selectedUser == null){
            showAlert("Błąd", "Wybierz użytkownika do usunięcia");
            return;
        }
        boolean removeFailed = !userService.removeUser(selectedUser.getId());
        if(removeFailed)
            showAlert("Błąd", "Wystąpił nieoczekiwany błąd");
        else
            showSucces();
        mainApp.setUserList();
    }

    private void setSelectedUser(User user){
        selectedUser = user;
    }

    public void setTableData(ObservableList<User> list){
        tableView.setItems(list);
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
        alert.setHeaderText("Użytkownik został usunięty ");
        alert.showAndWait();
    }

}
