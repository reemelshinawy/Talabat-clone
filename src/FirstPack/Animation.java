package FirstPack;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Animation {
    public static void LoadNextScene(String page, AnchorPane parentContainer){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(300));
        fadeTransition.setNode(parentContainer);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(e -> {
            Parent secondView;
            try {
                secondView = FXMLLoader.load(Animation.class.getResource(page));
                Scene scene = new Scene(secondView);
                Stage stage = (Stage) parentContainer.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        fadeTransition.play();
    }

    public static void LoadCurrentScene(AnchorPane parentContainer){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(300));
        fadeTransition.setNode(parentContainer);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

    }

}
