package Database;

import FirstPack.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Customer extends User {

    private int mobileNumber;
    private String address;
    private String email;
    private Order order = new Order();

    //for sign up
    public Customer(String username, String password, int mobileNumber, String address, String email) {
        super(username, password);
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.email = email;
        this.order.setOrder_Name(super.getUsername() + "," + this.address);

    }
    // for login
    public Customer(String username, String password) {
        super(username, password);
    }

    @Override
    public void register() throws SQLException{
        try {
            
            String query = "INSERT INTO CUSTOMER (CUSTOMER_username, CUSTOMER_password, address, mobile_number , email)" + "values(?,?,?,?,?)";
            PreparedStatement insert = Main.conn.prepareStatement(query);
            insert.setString(1, super.getUsername());
            insert.setString(2, super.getPassword());
            insert.setString(3, address);
            insert.setInt(4, mobileNumber);
            insert.setString(5, email);
            insert.execute();
         
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public boolean makeOrder(String orderName, float price, String notes, String resName, int quantity) {
        LocalDate date = LocalDate.now();
        try {
            String query = "INSERT INTO ORDERS (ORDER_NAME, NOTES, RESTURANT_NAME, PRICE, ORDER_DATE, quantity)" + "values(?,?,?,?,?,?)";
            PreparedStatement insert = Main.conn.prepareStatement(query);
            insert.setString(1, orderName);
            insert.setFloat(4, price);
            insert.setString(2, notes);
            insert.setString(3, resName);
            insert.setString(5,java.sql.Date.valueOf(date).toString());
            insert.setInt(6,quantity);
            insert.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int login() {
        int check = 0;
        try {
      
            String query = "SELECT * FROM CUSTOMER";
            PreparedStatement select = Main.conn.prepareStatement(query);
            ResultSet loginSet = select.executeQuery(query);
            while (loginSet.next()) {
                if (loginSet.getString("CUSTOMER_USERNAME").equals(super.getUsername())) {
                    if (loginSet.getString("CUSTOMER_PASSWORD").equals(super.getPassword())) {
                        check = 1;
                        break;
                    } else {
                        check = 0;
                    }
                } else {
                    check = 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }
}
