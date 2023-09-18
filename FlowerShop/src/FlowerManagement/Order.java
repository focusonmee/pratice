/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlowerManagement;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author laptoptk
 */
public class Order implements Serializable{

    //Khai bao bien
    // Nam trong OrderHeader
    private String orderId;
    private String orderDate;
    private String orderCustomerName;
    //Nam trong OrderDetail
    private ArrayList<OrderDetail> details;
    private int flowerCount = 0;
    private double orderTotal = 0;

    //Constuctor
    public Order() {
    }

    public Order(String orderId, String orderDate, String orderCustomerName, ArrayList<OrderDetail> details) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderCustomerName = orderCustomerName;
        this.details = details;
        for (OrderDetail detail : details) {
            flowerCount += detail.getQuantity();
            orderTotal += detail.getFlowerCost();
        }
    }

    //Getter and setter
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderCustomerName() {
        return orderCustomerName;
    }

    public void setOrderCustomerName(String orderCustomerName) {
        this.orderCustomerName = orderCustomerName;
    }

    public ArrayList<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<OrderDetail> details) {
        this.details = details;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public int getFlowerCount() {
        return flowerCount;
    }

    public void setFlowerCount(int flowerCount) {
        this.flowerCount = flowerCount;
    }

    // Output method
    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return "OrderID: " + orderId + " - "
                + "Order Date: " + orderDate + " - "
                + "Customer: " + orderCustomerName + " - "
                + "FLowers count: " + flowerCount + " - "
                + "Order Total: " + orderTotal;
    }

    //Overiden equal and hashcode
    @Override
    public int hashCode() {
        return 31 + orderId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Order) {
            Order anotherOrder = (Order) obj;
            if (this.getOrderId().equals(anotherOrder.getOrderId())) {
                return true;
            }
        }
        return false;
    }
}
