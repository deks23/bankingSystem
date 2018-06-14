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
    private ShowUsersController userListController;
    private MainMenuController mainMenuController;
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
            rootLayout.setCenter(userListView);

    }

    public void showAddClient(){
            rootLayout.setCenter(addUserView);
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
