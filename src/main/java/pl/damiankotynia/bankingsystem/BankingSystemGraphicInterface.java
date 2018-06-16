package pl.damiankotynia.bankingsystem;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.damiankotynia.bankingsystem.controller.AddUserController;
import pl.damiankotynia.bankingsystem.controller.MainMenuController;
import pl.damiankotynia.bankingsystem.controller.ShowUsersController;
import pl.damiankotynia.bankingsystem.controller.WithdrawMoneyController;
import pl.damiankotynia.bankingsystem.database.Database;
import pl.damiankotynia.bankingsystem.database.DatabaseImpl;
import pl.damiankotynia.bankingsystem.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class BankingSystemGraphicInterface extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<User> userList = FXCollections.observableArrayList();
    private AnchorPane userListView;
    private AnchorPane addUserView;
    private AnchorPane depositMoney;
    private AnchorPane withdrawMoney;
    private AnchorPane transferMoney;
    private ShowUsersController userListController;
    private MainMenuController mainMenuController;
    private WithdrawMoneyController withdrawMoneyController;

    private AddUserController addUserController;
    private Database database;

    public static void main(String[] args) {
        launch(args);
    }

    private ObservableList<User> setUserList(){
        userList.addAll(database.getAllUsers());
        return userList;
    }

    public ObservableList<User> getUserList(){
        setUserList();
        return userList;
    }

    public void initRootLayout (){
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BankingSystemGraphicInterface.class.getResource("/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setMaxHeight(510);
            primaryStage.setMinHeight(510);
            primaryStage.setMaxWidth(900);
            primaryStage.setMinWidth(900);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMainMenu(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BankingSystemGraphicInterface.class.getResource("/MainMenu.fxml"));
            AnchorPane mainMenu = (AnchorPane) loader.load();
            rootLayout.setLeft(mainMenu);
            mainMenuController = loader.getController();
            mainMenuController.setMainApp(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showUserList(){
            userList.clear();
            userList.addAll(database.getAllUsers());
            rootLayout.setCenter(userListView);

    }

    public void showAddClient(){
            addUserController.clearButtonClick();
            rootLayout.setCenter(addUserView);
    }

    public void showDepositMoney(){
        rootLayout.setCenter(depositMoney);
    }

    public void showWithdrawMoney(){
        rootLayout.setCenter(withdrawMoney);
    }


    public void showTransferMoney(){
        rootLayout.setCenter(transferMoney);
    }

    //TODO
    public void loadFXML (){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BankingSystemGraphicInterface.class.getResource("/AddClient.fxml"));
            addUserView = (AnchorPane) loader.load();
            addUserController = loader.getController();

            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(BankingSystemGraphicInterface.class.getResource("/UserList.fxml"));
            userListView = (AnchorPane) loader2.load();
            userListController = loader2.getController();
            userListController.setMainApp(this);
            userListController.setTableData(getUserList());

            FXMLLoader loader3 = new FXMLLoader();
            loader3.setLocation(BankingSystemGraphicInterface.class.getResource("/DepositMoney.fxml"));
            depositMoney = (AnchorPane) loader3.load();

            FXMLLoader loader4 = new FXMLLoader();
            loader4.setLocation(BankingSystemGraphicInterface.class.getResource("/WithdrawMoney.fxml"));
            withdrawMoney = (AnchorPane) loader4.load();
            withdrawMoneyController = loader4.getController();
            withdrawMoneyController.setMainApp(this);


            FXMLLoader loader5 = new FXMLLoader();
            loader5.setLocation(BankingSystemGraphicInterface.class.getResource("/TransferMoney.fxml"));
            transferMoney = (AnchorPane) loader5.load();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {
        database = new DatabaseImpl();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Banking system");
        initRootLayout();
        showMainMenu();
        loadFXML();
        showUserList();

    }
}
