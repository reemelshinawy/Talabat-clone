package CustomerPack;

import Database.Meals;
import Database.Restaurant;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static FirstPack.Animation.LoadCurrentScene;
import static FirstPack.Animation.LoadNextScene;

public class ViewAllRestaurantsController implements Initializable {

    public JFXButton btnLogout;
    @FXML
    private AnchorPane parentController;
    @FXML
    private GridPane gridPane;

    Restaurant restaurant = new Restaurant();
    public List<Restaurant> list = restaurant.getAllRestaurants();
    protected static String chosenRes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadCurrentScene(parentController);

        OnClickListener myListener = new OnClickListener() {
            @Override
            public void myListenerRes(Restaurant restaurant) {
                chosenRes = restaurant.getBusinessName();
                LoadNextScene("/CustomerPack/CustomerMeals.fxml", parentController);
            }

            @Override
            public void myListenerMeals(Meals meals) {

            }
        };

        int column = 0, row = 1;

        try  {
            for (int i = 0; i < list.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CustomerPack/ItemRestaurant.fxml"));

                VBox vBox = fxmlLoader.load();
                ItemRestaurantController itemRestaurantController = fxmlLoader.getController();
                itemRestaurantController.setData(list.get(i), myListener);

                if (column == 4){
                    column = 0;
                    ++row;
                }

                gridPane.add(vBox, column++, row);
                GridPane.setMargin(vBox, new Insets(38));

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        btnLogout.setOnAction(e -> {
            LoadNextScene("/FirstPack/Welcome.fxml",parentController);
        });
    }

}