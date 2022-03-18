package FirstPack;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

import static FirstPack.Animation.LoadCurrentScene;
import static FirstPack.Animation.LoadNextScene;

public class CreditsController implements Initializable {


    public AnchorPane parentContainer;
    @FXML
    private Circle circle1;
    @FXML
    private Circle circle2;
    @FXML
    private Circle circle3;
    @FXML
    private Circle circle4;
    @FXML
    private Circle circle5;
    @FXML
    private Circle circle6;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadCurrentScene(parentContainer);

        Image diaa = new Image("/Res/credits/diaa.png");
        Image mostafa = new Image("/Res/credits/mostafa.png");
        Image dola = new Image("/Res/credits/dola.png");
        Image reem = new Image("/Res/credits/reem.png");
        Image laura = new Image("/Res/credits/laura.png");
        Image yousra = new Image("/Res/credits/yousra.png");

        circle1.setFill(new ImagePattern(diaa));
        circle1.scaleXProperty();
        circle1.scaleYProperty();
        circle2.setFill(new ImagePattern(mostafa));
        circle3.setFill(new ImagePattern(dola));
        circle4.setFill(new ImagePattern(laura));
        circle5.setFill(new ImagePattern(reem));
        circle6.setFill(new ImagePattern(yousra));

    }
}
