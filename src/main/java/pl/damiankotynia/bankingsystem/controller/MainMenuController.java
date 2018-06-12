package pl.damiankotynia.bankingsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pl.damiankotynia.bankingsystem.BankingSystemGraphicInterface;


public class MainMenuController {
    @FXML
    private Button showClients;
    @FXML
    private Button addClient;
    @FXML
    private Button depositMoney;
    @FXML
    private Button withdrawMoney;
    @FXML
    private Button transferMoney;
    @FXML
    private Button removeClient;
    @FXML
    private Button searchClient;

    private BankingSystemGraphicInterface mainApp;

    public MainMenuController(){

    }

    @FXML
    private void initialize(){

    }

    public void setMainApp(BankingSystemGraphicInterface mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    public void showClientsClick(){
        mainApp.showUserList();
    }

    @FXML
    public void addClientClick(){
        mainApp.showAddClient();
    }

    @FXML
    public void depositMoneyClick(){
        mainApp.showUserList();
    }

    @FXML
    public void withdrawMoneyClick(){
        mainApp.showUserList();
    }

    @FXML
    public void transferMoneyClick(){
        mainApp.showUserList();
    }

    @FXML
    public void removeClientClick(){
        mainApp.showUserList();
    }

    @FXML
    public void searchClick(){
        mainApp.showUserList();
    }
}
