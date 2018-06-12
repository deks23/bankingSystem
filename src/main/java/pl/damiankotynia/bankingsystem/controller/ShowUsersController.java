package pl.damiankotynia.bankingsystem.controller;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.damiankotynia.bankingsystem.model.User;


public class ShowUsersController {
    @FXML
    private TableView <User> tableView;
    @FXML
    private TableColumn <User, String> accountNumberColumn;
    @FXML
    private TableColumn <User, String> nameColumn;
    @FXML
    private TableColumn <User, String> surnameColumn;
    @FXML
    private TableColumn <User, String> peselColumn;
    @FXML
    private TableColumn <User, String> addressColumn;
    @FXML
    private TableColumn <User, String> cashColumn;

    public ShowUsersController(){

    }

    @FXML
    private void initialize(){
        StringProperty stringProperty ;

        /*accountNumberColumn.setCellValueFactory(cellData-> cellData.getValue().)*/
    }
}
