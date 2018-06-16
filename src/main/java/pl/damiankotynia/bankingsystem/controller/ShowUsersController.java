package pl.damiankotynia.bankingsystem.controller;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.damiankotynia.bankingsystem.BankingSystemGraphicInterface;
import pl.damiankotynia.bankingsystem.model.User;


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

    private BankingSystemGraphicInterface mainApp;
    public ShowUsersController(){

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
    }

    public void setMainApp(BankingSystemGraphicInterface mainApp){
        this.mainApp = mainApp;
    }

    public void setTableData(ObservableList<User> list){
        tableView.setItems(list);
    }
}
