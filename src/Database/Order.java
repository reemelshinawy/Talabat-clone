package Database;

import FirstPack.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {

    public int order_Number;
    public int quantity;
    public String order_Name;
    public String order_Notes;
    public String restName;
    public float price;
    public String order_Date;




    public Order(int order_Number, String order_Name, String order_Notes, String restName, float price, String order_Date, int quantity) {
        this.order_Number = order_Number;
        this.order_Name = order_Name;
        this.order_Notes = order_Notes;
        this.restName = restName;
        this.price = price;
        this.order_Date = order_Date;
        this.quantity = quantity;
    }

    public Order() {

    }

    public Order(String orderName) {
        LocalDate date = LocalDate.now();
        this.order_Name = orderName;
        this.order_Date = java.sql.Date.valueOf(date).toString();

    }


    public ObservableList<Order> getOrderList(String str) {
        ObservableList<Order> orderList = FXCollections.observableArrayList();
        String query = "SELECT * FROM ORDERS WHERE RESTURANT_NAME = '" + str + "'";
        try {
            Statement select = Main.conn.prepareStatement(query);
            ResultSet rs = select.executeQuery(query);
            Order order;
            while (rs.next()) {
                order = new Order(rs.getInt("ORDER_NUMBER"), rs.getString("ORDER_NAME"), rs.getString("NOTES"), rs.getString("RESTURANT_NAME"),rs.getInt("PRICE"), rs.getString("ORDER_DATE"), rs.getInt("quantity"));
                orderList.add(order);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return orderList;
    }

    public int getOrder_Number() {
        return order_Number;
    }

    public void setOrder_Number(int order_Number) {
        this.order_Number = order_Number;
    }

    public String getOrder_Name() {
        return order_Name;
    }

    public String getOrder_Notes() {
        return order_Notes;
    }

    public String getRestName() {
        return restName;
    }

    public float getPrice() {
        return price;
    }

    public String getOrder_Date() {
        return order_Date;
    }

    public void setOrder_Name(String order_Name) {
        this.order_Name = order_Name;
    }

}
