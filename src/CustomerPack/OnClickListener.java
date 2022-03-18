package CustomerPack;

import Database.Meals;
import Database.Restaurant;

public interface OnClickListener {
    void myListenerRes(Restaurant restaurant);
    void myListenerMeals(Meals meals);
}
