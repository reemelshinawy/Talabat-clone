package FirstPack;

import Database.Customer;
import Database.Restaurant;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static FirstPack.Animation.LoadCurrentScene;
import static FirstPack.Animation.LoadNextScene;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane parentContainer;
    @FXML
    private JFXTextField usernameLogin;
    @FXML
    private JFXPasswordField passwordLogin;
    @FXML
    private JFXButton btn_login2;
    @FXML
    private Button btnPleaseSign;
    @FXML
    private Label txtAlert;

    public static String name, pass;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadCurrentScene(parentContainer);

        btnPleaseSign.setOnAction(e -> {
            LoadNextScene("Signup.fxml",parentContainer);

        });

        btn_login2.setOnAction((ActionEvent e) -> {
            if (!usernameLogin.getText().trim().isEmpty() && !passwordLogin.getText().trim().isEmpty()) {
                try {
                    name = usernameLogin.getText();
                    pass = passwordLogin.getText();
                    
                    Customer cus = new Customer(name, pass);
                    Restaurant res = new Restaurant(name, pass);
                    if (cus.login() == 1) {
                        LoadNextScene("/CustomerPack/ViewAllRestaurants.fxml",parentContainer);
                    } else if (res.login() == 1) {
                        LoadNextScene("/RestaurantPack/RestaurantMenu.fxml",parentContainer);
                    }
                    else {
                        txtAlert.setText("Invalid username or password.");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                txtAlert.setText("Please enter username and password.");
            }
        });
    }
}
