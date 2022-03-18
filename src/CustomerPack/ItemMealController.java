package CustomerPack;

import Database.Meals;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemMealController {

    @FXML
    private ImageView mealImage;
    @FXML
    private Label mealName;
    @FXML
    private Label mealPrice;

    @FXML
    private void click(MouseEvent MouseEvent){
        onClickListener.myListenerMeals(meals);
    }

    private OnClickListener onClickListener;
    private Meals meals;

    public void setData(Meals meals, OnClickListener onClickListener){
        this.onClickListener = onClickListener;
        this.meals = meals;

        Image image = new Image("file:///" + meals.getImageUrl());
        mealImage.setImage(image);
        mealName.setText(meals.getProductName());
        mealPrice.setText(meals.getProductPrice() + " LE.");
    }

}
