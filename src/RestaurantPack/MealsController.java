package RestaurantPack;

import Database.Meals;
import FirstPack.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static FirstPack.Animation.LoadCurrentScene;
import static FirstPack.Animation.LoadNextScene;

public class MealsController implements Initializable {

    @FXML
    private TableView<Meals> tableMeals;
    @FXML
    private TableColumn<Meals, String> colName;
    @FXML
    private TableColumn<Meals, Float> colPrice;
    @FXML
    private TableColumn<Meals, String> colDesc;
    @FXML
    private TableColumn<Meals, String> colImg;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXButton btnRefresh;
    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtDesc;
    @FXML
    private JFXTextField txtPrice;
    @FXML
    private Label txtAlert;
    @FXML
    private JFXButton btnAddImage;
    @FXML
    public AnchorPane parentController;
    @FXML
    public Label txtImageUrl;

    Meals meals = new Meals();
    private String name, desc, checkPrice,oldName, imageURL;
    private float
            price;
    private final String resName = LoginController.name;
    private final ObservableList<Meals> list = meals.getMealsList(resName);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadCurrentScene(parentController);

        setMeals();

        btnBack.setOnAction(e -> {
            LoadNextScene("/RestaurantPack/RestaurantMenu.fxml", parentController);
        });


        btnAdd.setOnAction(e -> {
            if (!txtImageUrl.getText().trim().isEmpty() && !txtName.getText().trim().isEmpty() && !txtDesc.getText().trim().isEmpty() && !txtPrice.getText().trim().isEmpty()) {
                name = txtName.getText();
                checkPrice = txtPrice.getText();
                price = checkPrice(checkPrice);
                desc = txtDesc.getText();

                Meals meals = new Meals(name, desc, price, resName,imageURL);
                if (meals.add_meal() == 1){
                    txtAlert.setStyle("-fx-text-fill: #00FD1C;");
                    txtAlert.setText("Meal " + name + " add successfully");
                }
                else{
                    txtAlert.setStyle("-fx-text-fill: red;");
                    txtAlert.setText("Meal not add");
                }

            } else if (txtImageUrl.getText().trim().isEmpty() || txtName.getText().trim().isEmpty() || txtDesc.getText().trim().isEmpty() || txtPrice.getText().trim().isEmpty()){
                txtAlert.setText("Please enter required data!");
            }
        });
        btnDelete.setOnAction(e -> {
            name = txtName.getText();
            checkPrice = txtPrice.getText();
            price = checkPrice(checkPrice);
            desc = txtDesc.getText();
            Meals meals = new Meals(name, desc, price, resName,imageURL);

            if (meals.delete_meal(name) == 1){
                txtAlert.setText("Meal " + name + " deleted successfully.");
                txtAlert.setStyle("-fx-text-fill: #00FD1C;");
            }
            else {
                txtAlert.setText("Meal " + name + " not deleted.");
                txtAlert.setStyle("-fx-text-fill: red;");
            }
        });
        btnClear.setOnAction(e ->{
            txtName.setText(null);
            txtDesc.setText(null);
            txtPrice.setText(null);
            txtAlert.setText(null);
        });
        btnRefresh.setOnAction(e -> {
            LoadNextScene("/RestaurantPack/Meals.fxml",parentController);
        });
        btnEdit.setOnAction(e -> {
            if (!txtName.getText().trim().isEmpty() && !txtDesc.getText().trim().isEmpty() && !txtPrice.getText().trim().isEmpty()) {
                name = txtName.getText();
                checkPrice = txtPrice.getText();
                price = checkPrice(checkPrice);
                desc = txtDesc.getText();
                if (meals.edit_meals(oldName,name,desc,price) == 1){
                    txtAlert.setText("Meal " + oldName + " updated successfully.");
                    txtAlert.setStyle("-fx-text-fill: #00FD1C;");
                } else {
                    txtAlert.setText("Meal " + oldName + " update failed.");
                    txtAlert.setStyle("-fx-text-fill: red;");
                }
            } else if (txtName.getText().trim().isEmpty() || txtDesc.getText().trim().isEmpty() || txtPrice.getText().trim().isEmpty()){
                txtAlert.setText("Please enter required data!");
            }
        });
        btnAddImage.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            if (file != null){
                txtImageUrl.setText(file.getName());
                imageURL = file.getAbsolutePath();
            }
            else{
                txtImageUrl.setText("Image not found.");
                txtImageUrl.setStyle("-fx-text-fill: #00FD1C;");
            }

        });
    }

    public void setMeals() {
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        colImg.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));
        tableMeals.setItems(list);
    }

    @FXML
    private void Selected(MouseEvent event){
        Meals meals = tableMeals.getSelectionModel().getSelectedItem();
        if (meals==null){
            txtAlert.setText("Nothing selected.");
        }
        else {
            txtName.setText(meals.getProductName());
            txtDesc.setText(meals.getProductDescription());
            txtPrice.setText(String.valueOf(meals.getProductPrice()));
            oldName = meals.getProductName();
        }
    }

    public float checkPrice(String str) {
            try {
                if (Float.parseFloat(str) <= 0) {
                    txtAlert.setText("Not valid price, Please enter correct number.");
                }
            } catch (NumberFormatException e) {
                txtAlert.setText("Not valid price, Please enter correct number.");
            }
        return Float.parseFloat(str);
    }

}