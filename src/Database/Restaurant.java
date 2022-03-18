package Database;

import FirstPack.Main;
import javafx.collections.FXCollections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Restaurant extends User {

    private String businessName;
    private String imageUrl;
    private Meals m = new Meals();

    public Restaurant() {
        super(null, null);
    }

    // sign up
    public Restaurant(String resName, String password, String businessName, String imageUrl) {
        super(resName, password);
        this.businessName = businessName;
        this.imageUrl = imageUrl;
    }

    //login
    public Restaurant(String resName, String password) {
        super(resName, password);
    }

    //to get image and restaurant name for grid pane
    public Restaurant(String businessName, String imageUrl, String pass) {
        super(null, null);
        this.businessName = businessName;
        this.imageUrl = imageUrl;

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBusinessName() {
        return businessName;
    }


    @Override
    public void register() throws SQLException {
        try {
            String query = "INSERT INTO RESTURANTS_OWNER (OWNER_NAME, RESTURANT_NAME, PASSWORD, RESTAURANT_IMG)" + "values(?,?,?,?)";
            PreparedStatement insert = Main.conn.prepareStatement(query);
            insert.setString(1, businessName);
            insert.setString(2, super.getUsername());
            insert.setString(3, super.getPassword());
            insert.setString(4, imageUrl);
            insert.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int login() throws SQLException {
        int check = 0;
        try {
            String query = "SELECT * FROM RESTURANTS_OWNER";
            Statement select = Main.conn.prepareStatement(query);
            ResultSet loginSet = select.executeQuery(query);
            while (loginSet.next()) {
                if (loginSet.getString("RESTURANT_NAME").equals(super.getUsername())) {
                    if (loginSet.getString("PASSWORD").equals(super.getPassword())) {
                        check = 1;
                        break;
                    } else {
                        check = 0;
                    }
                } else {
                    check = 0;
                }
            }
            if (check == 1) {
            }
            if (check == 0) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> RestaurantList = FXCollections.observableArrayList();

        String query = "SELECT * FROM RESTURANTS_OWNER";
        try {
            Statement select = Main.conn.prepareStatement(query);
            ResultSet rs = select.executeQuery(query);
            Restaurant restaurant;
            while (rs.next()) {
                restaurant = new Restaurant(rs.getString("RESTURANT_NAME"), rs.getString("RESTAURANT_IMG"), "empty pass");
                RestaurantList.add(restaurant);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return RestaurantList;

    }
}
