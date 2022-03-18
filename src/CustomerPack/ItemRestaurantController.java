package CustomerPack;

import Database.Restaurant;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ItemRestaurantController {

    @FXML
    public ImageView restaurantImg;
    @FXML
    public Label restaurantName;
    @FXML
    private void click(){
        onClickListener.myListenerRes(restaurant);
    }

    private OnClickListener onClickListener;
    private Restaurant restaurant;

    public void setData(Restaurant restaurant, OnClickListener onClickListener){
        this.onClickListener = onClickListener;
        this.restaurant = restaurant;
        Image image = new Image("file:///" + restaurant.getImageUrl());
        restaurantImg.setImage(image);
        restaurantName.setText(restaurant.getBusinessName());

    }
}
