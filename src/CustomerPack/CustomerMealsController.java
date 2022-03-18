package CustomerPack;

import Database.Customer;
import Database.Meals;
import Database.Restaurant;
import FirstPack.LoginController;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static FirstPack.Animation.LoadCurrentScene;
import static FirstPack.Animation.LoadNextScene;

public class CustomerMealsController implements Initializable {

    @FXML
    public JFXButton btnBack;
    @FXML
    public TextField txtSearch;
    public TextField txtNotes;
    public Label confirm;
    public TextField txtQuantity;
    @FXML
    private AnchorPane parentContainer;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private Label txtMealName;
    @FXML
    private ImageView imgMeal;
    @FXML
    private Label txtMealPrice;
    @FXML
    private Label txtMealDesc;
    @FXML
    private JFXButton btnBuy;

    @FXML
    private GridPane GridView;

    Meals meals = new Meals();
    String resName = ViewAllRestaurantsController.chosenRes;
    public List<Meals> list = meals.getMealsList(resName);
    int quantity;

    public int getQuantity() {
        return quantity;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadCurrentScene(parentContainer);

        OnClickListener myListener = new OnClickListener() {
            @Override
            public void myListenerRes(Restaurant restaurant) {

            }
            @Override
            public void myListenerMeals(Meals meals) {
                previewMeal(meals);
            }
        };

        int column = 0, row = 1;

        try  {
            for (Meals value : list) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CustomerPack/ItemMeal.fxml"));

                VBox settingItem = fxmlLoader.load();
                ItemMealController itemMealController = fxmlLoader.getController();
                itemMealController.setData(value, myListener);

                if (column == 4) {
                    column = 0;
                    ++row;
                }

                GridView.add(settingItem, column++, row);
                GridPane.setMargin(settingItem, new Insets(30));

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        btnBack.setOnAction(e ->{
            LoadNextScene("/CustomerPack/ViewAllRestaurants.fxml", parentContainer);
        });
        btnSearch.setOnAction(e ->{
            String search = txtSearch.getText();
            for (int i = 0 ; i < list.size() ; i++) {
                if(list.get(i).getProductName().equals(search)){
                    this.previewMeal(list.get(i));
                    confirm.setText("Meal found.");
                    confirm.setStyle("-fx-text-fill: green;");
                    break;
                }
                else{
                    confirm.setText("No meal found.");
                    confirm.setStyle("-fx-text-fill: red;");
                }
            }
        });
        btnBuy.setOnAction(e -> {
             quantity = 1;
            String notes = txtNotes.getText();
            String orderName = txtMealName.getText() + "," + LoginController.name;
            float price = Float.parseFloat(txtMealPrice.getText());
            try {
                quantity = Integer.parseInt(txtQuantity.getText());
            }catch (NumberFormatException exception) {
                confirm.setText("Please, Enter quantity value");
                confirm.setStyle("-fx-text-fill: red;");
            }
            if(txtQuantity.getText().trim().isEmpty()){
                quantity = 1;
            }
            else if (quantity == 0) {
                confirm.setText("Please, Enter correct value");
                confirm.setStyle("-fx-text-fill: red;");
            } else if (quantity < 0){
                quantity *= -1;
            } else{
                price = price * quantity;
                Customer customer = new Customer(LoginController.name , LoginController.pass);

                if (customer.makeOrder(orderName, price, notes, resName,quantity)){
                    confirm.setText("Done");
                    confirm.setStyle("-fx-text-fill: green;");
                } else {
                    confirm.setText("Failed to buy");
                    confirm.setStyle("-fx-text-fill: red;");
                }
            }

        });
    }

    private void previewMeal(Meals meals){
        txtMealName.setText(meals.getProductName());
        txtMealPrice.setText(String.valueOf(meals.getProductPrice()));
        Image image = new Image("file:///" + meals.getImageUrl());
        imgMeal.setImage(image);
        txtMealDesc.setText(meals.getProductDescription());
        btnBuy.setText("Buy");
    }
}
