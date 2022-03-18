package RestaurantPack;

import Database.Order;
import FirstPack.LoginController;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static FirstPack.Animation.LoadCurrentScene;
import static FirstPack.Animation.LoadNextScene;

public class OrdersController implements Initializable{

    @FXML
    public AnchorPane parentContainer;
    @FXML
    public JFXButton btnBack;
    @FXML
    public TableView<Order> tableOrder;
    @FXML
    public TableColumn<Order, Integer> colOrderNumber;
    @FXML
    public TableColumn<Order, String> colOrderName;
    @FXML
    public TableColumn<Order, String> colNote;
    @FXML
    public TableColumn<Order, String> colDate;
    @FXML
    public TableColumn<Order, String> colResName;
    @FXML
    public TableColumn<Order, Float> colPrice;
    public TableColumn<Order, Integer> colQuantity;

    Order order = new Order();
    private final String resName = LoginController.name;
    private  ObservableList<Order> list = order.getOrderList(resName);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadCurrentScene(parentContainer);

        setOrders();

        btnBack.setOnAction(e -> {
            LoadNextScene("/RestaurantPack/RestaurantMenu.fxml", parentContainer);
        });


    }
    public void setOrders() {
        colOrderNumber.setCellValueFactory(new PropertyValueFactory<>("order_Number"));
        colOrderName.setCellValueFactory(new PropertyValueFactory<>("order_Name"));
        colNote.setCellValueFactory(new PropertyValueFactory<>("order_Notes"));
        colResName.setCellValueFactory(new PropertyValueFactory<>("restName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("order_Date"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tableOrder.setItems(list);
    }
}
