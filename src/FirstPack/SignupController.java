package FirstPack;

import Database.Customer;
import Database.Restaurant;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static FirstPack.Animation.LoadCurrentScene;
import static FirstPack.Animation.LoadNextScene;

public class SignupController implements Initializable {

    @FXML
    public AnchorPane parentContainer;
    @FXML
    public JFXButton btnAddImage;
    @FXML
    public Label txtImageUrl;
    @FXML
    private RadioButton restaurant;
    @FXML
    private ToggleGroup type;
    @FXML
    private RadioButton customer;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPass;
    @FXML
    private JFXButton btn_signup2;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtResturantName;
    @FXML
    private JFXTextField txtPhone;
    @FXML
    public Label txtErrorSignup;
    @FXML
    private Button btn2login;
    @FXML
    private JFXButton btnBack;
    @FXML
    private Label txtAlert;

    protected String name, pass, email, phone, resName, address, imageUrl;
    private int checkN;
    private boolean registerComplete = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadCurrentScene(parentContainer);
        txtAddress.setVisible(false);
        txtEmail.setVisible(false);
        txtPass.setVisible(false);
        txtResturantName.setVisible(false);
        txtUsername.setVisible(false);
        txtPhone.setVisible(false);
        btnAddImage.setVisible(false);
        txtImageUrl.setVisible(false);

        type.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n) {
                if (restaurant.isSelected()) {
                    txtUsername.setVisible(true);
                    txtPass.setVisible(true);
                    txtResturantName.setVisible(true);
                    btnAddImage.setVisible(true);
                    txtImageUrl.setVisible(true);
                    txtAddress.setVisible(false);
                    txtEmail.setVisible(false);
                    txtPhone.setVisible(false);
                } else if (customer.isSelected()) {
                    txtUsername.setVisible(true);
                    txtPass.setVisible(true);
                    txtAddress.setVisible(true);
                    txtEmail.setVisible(true);
                    txtPhone.setVisible(true);
                    txtResturantName.setVisible(false);
                    btnAddImage.setVisible(false);
                    txtImageUrl.setVisible(false);
                }
            }
        });

        btn_signup2.setOnAction(e -> {

                    if (restaurant.isSelected() && (!txtImageUrl.getText().trim().isEmpty() && !txtUsername.getText().trim().isEmpty() && !txtPass.getText().trim().isEmpty() && !txtResturantName.getText().trim().isEmpty())) {
                        name = txtUsername.getText();
                        pass = txtPass.getText();
                        resName = txtResturantName.getText();

                        Restaurant res = new Restaurant(resName, pass, name,imageUrl);
                        try {
                            res.register();
                        } catch (SQLException ex) {
                            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        registerComplete = true;
                    } else if (customer.isSelected() && (!txtUsername.getText().trim().isEmpty() || !txtPass.getText().trim().isEmpty() || !txtPhone.getText().trim().isEmpty() || !txtAddress.getText().trim().isEmpty() || !txtEmail.getText().trim().isEmpty())) {
                        name = txtUsername.getText();
                        pass = txtPass.getText();
                        address = txtAddress.getText();
                        phone = txtPhone.getText();
                        checkN = checkNumber(phone);
                        email = txtEmail.getText();

                        Customer cus = new Customer(name, pass, checkN, address, email);
                        try {
                            cus.register();
                        } catch (SQLException ex) {
                            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        registerComplete = true;
                    } else {
                        txtAlert.setText("Please enter required data.");
                    }

                    if (registerComplete) {
                        LoadNextScene("Welcome.fxml", parentContainer);
                    }

                }
        );

        btnAddImage.setOnAction(e ->{
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            if (file != null){
                txtImageUrl.setText(file.getName());
                imageUrl = file.getAbsolutePath();

            }
            else{
                txtImageUrl.setText("Image not found.");
                txtImageUrl.setStyle("-fx-text-fill: #00FD1C;");
            }
        });
        btn2login.setOnAction(e -> {
            LoadNextScene("Login.fxml", parentContainer);
        });
        btnBack.setOnAction(e -> {
            LoadNextScene("Welcome.fxml", parentContainer);
        });


    }

    public int checkNumber(String str) {
        try {

            if (Integer.parseInt(str) <= 0) {
                txtAlert.setText("Invalid phone number, Please enter correct one.");
            }
        } catch (NumberFormatException e) {
            txtAlert.setText("Invalid phone number, Please enter correct one.");
        }
        return Integer.parseInt(str);
    }

}
