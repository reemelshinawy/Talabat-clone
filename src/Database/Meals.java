package Database;

import FirstPack.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Meals {

    private String mealName;
    private String mealDesc;
    private String resName;
    private float mealPrice;
    private String imageUrl;

    public Meals (){}

    public Meals(String productName, String productDescription, float productPrice, String resName, String imageUrl) {
        this.mealName = productName;
        this.mealDesc = productDescription;
        this.mealPrice = productPrice;
        this.resName = resName;
        this.imageUrl = imageUrl;
    }


    public Meals(String productName) {
        this.mealName = productName;
    }


    public String getProductName() {
        return mealName;
    }

    public String getProductDescription() {
        return mealDesc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public final float getProductPrice() {
        return mealPrice;
    }



    public ObservableList<Meals> getMealsList(String str) {
        ObservableList<Meals> mealList = FXCollections.observableArrayList();
        String query = "SELECT * FROM MEALS WHERE RESTURANT_NAME = '" + str + "'";
        try {
            Statement select = Main.conn.prepareStatement(query);
            ResultSet rs = select.executeQuery(query);
            Meals meals ;
            while (rs.next()) {
                meals = new Meals(rs.getString("PRODUCT_NAME"), rs.getString("PRODUCT_DESCRIPTION"), rs.getFloat("PRICE"), rs.getString("RESTURANT_NAME"), rs.getString("MEAL_IMG"));
                mealList.add(meals);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mealList;
    }

    public int add_meal() {
        try {
            String query = "INSERT INTO MEALS (PRODUCT_NAME, PRODUCT_DESCRIPTION, PRICE, RESTURANT_NAME, MEAL_IMG)" + "values(?,?,?,?,?)";
            PreparedStatement insert = Main.conn.prepareStatement(query);
            insert.setString(1, mealName);
            insert.setString(2, mealDesc);
            insert.setFloat (3, mealPrice);
            insert.setString(4, resName);
            insert.setString(5, imageUrl);
            insert.execute();
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    public int delete_meal(String str) {
        try {
            String query3 = "SELECT PRODUCT_NAME FROM MEALS WHERE PRODUCT_NAME = '" + str + "'";
            Statement statement = Main.conn.prepareStatement(query3);
            ResultSet rs = statement.executeQuery(query3);
            while(rs.next()) {
                    String query = "DELETE FROM MEALS WHERE PRODUCT_NAME = '" + str + "'";
                    statement.executeUpdate(query);
                    break;
            }
            statement.close();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int edit_meals(String oldName, String newName, String newDesc, float newPrice) {
        try {
            String query = "UPDATE MEALS SET PRODUCT_NAME = '" + newName +"',PRODUCT_DESCRIPTION = '" + newDesc + "', PRICE = " + newPrice + " WHERE PRODUCT_NAME = '" + oldName+ "'";
            PreparedStatement statement = Main.conn.prepareStatement(query);
            statement.executeUpdate(query);
            statement.close();
            return 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
}