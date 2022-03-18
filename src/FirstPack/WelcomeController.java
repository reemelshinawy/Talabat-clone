package FirstPack;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static FirstPack.Animation.LoadCurrentScene;
import static FirstPack.Animation.LoadNextScene;

public class WelcomeController implements Initializable {

    @FXML
    public AnchorPane parentContainer;
    @FXML
    public JFXButton btn_login;
    @FXML
    public JFXButton btn_signup;
    public JFXButton btn_credits;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadCurrentScene(parentContainer);
        btn_login.setOnAction(e -> {
            LoadNextScene("Login.fxml", parentContainer);
        });
        btn_signup.setOnAction(e->{
            LoadNextScene("Signup.fxml", parentContainer);
        });
        btn_credits.setOnAction(e->{
            LoadNextScene("Credits.fxml", parentContainer);
        });
    }

}
