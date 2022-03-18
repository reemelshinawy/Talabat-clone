package FirstPack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
    
    public static Connection conn;
    public static void databaseConnection(){
        final String dbURL = "jdbc:mysql://remotemysql.com:3306/px9BHwYOzj";
        final String Username = "px9BHwYOzj";
        final String Password = "FfoEDsXzWQ";

        try {
            conn = DriverManager.getConnection(dbURL, Username, Password);
        } catch (SQLException e){
            e.printStackTrace();

            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Main.databaseConnection();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Food Express");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Res/talabat_logo.png"))); 
        
        Splashscreen splash = new Splashscreen();
        try {
            // Make JWindow appear for 3 seconds before disappear
            Thread.sleep(3000);
            splash.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene scene = new Scene(root,1300,750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
}
