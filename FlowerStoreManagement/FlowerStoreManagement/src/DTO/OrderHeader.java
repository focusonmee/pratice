package DTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author laptoptk
 */
public class OrderHeader {
    private String orderId;
    private Date orderDate;
    private String orderCustomerName;

    public OrderHeader() {
    }

    public OrderHeader(String orderId, Date orderDate, String orderCustomerName) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderCustomerName = orderCustomerName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderCustomerName() {
        return orderCustomerName;
    }

    public void setOrderCustomerName(String orderCustomerName) {
        this.orderCustomerName = orderCustomerName;
    }
    
    public String toString(){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return orderId + "," +
                df.format(orderDate) + "," + 
                orderCustomerName;
    }
    
}
