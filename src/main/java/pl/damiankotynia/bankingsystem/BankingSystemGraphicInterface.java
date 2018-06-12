package pl.damiankotynia.bankingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.damiankotynia.bankingsystem.controller.MainMenuController;

import java.io.IOException;

public class BankingSystemGraphicInterface extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    public static void main(String[] args) {
        launch(args);
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
            MainMenuController controller = loader.getController();
            controller.setMainApp(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showUserList(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BankingSystemGraphicInterface.class.getResource("/UserList.fxml"));
            AnchorPane userList = (AnchorPane) loader.load();
            rootLayout.setCenter(userList);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showAddClient(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(BankingSystemGraphicInterface.class.getResource("/AddClient.fxml"));
            AnchorPane userList = (AnchorPane) loader.load();
            rootLayout.setCenter(userList);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Banking system");
        initRootLayout();
        showMainMenu();
    }
}
