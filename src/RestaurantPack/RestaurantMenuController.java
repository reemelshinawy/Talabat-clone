package RestaurantPack;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

import static FirstPack.Animation.LoadCurrentScene;
import static FirstPack.Animation.LoadNextScene;

public class RestaurantMenuController implements Initializable {

    @FXML
    public AnchorPane parentContainer;
    @FXML
    public Button Logout;
    @FXML
    public VBox meals;
    @FXML
    public VBox orders;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadCurrentScene(parentContainer);

        meals.setOnMouseClicked(e -> {
            LoadNextScene("/RestaurantPack/Meals.fxml",parentContainer);
        });

        orders.setOnMouseClicked(e -> {
            LoadNextScene("/RestaurantPack/Orders.fxml",parentContainer);
        });
        
        Logout.setOnAction(e -> {
            LoadNextScene("/FirstPack/Welcome.fxml",parentContainer);
        });
    }

}
