/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author laptoptk
 */
public class Order {
    OrderHeader header;
    ArrayList<OrderDetail> details;
    
    //khac nhau 
    int flowerCount =0;
    double orderTotal =0;

    public Order() {
    }

    public Order(OrderHeader header, ArrayList<OrderDetail> details) {
        this.header = header;
        this.details = details;
        for (OrderDetail detail : details) {
            flowerCount += detail.getQuantity();
            orderTotal += detail.getFlowerCost();
        }
    }

    public OrderHeader getHeader() {
        return header;
    }

    public void setHeader(OrderHeader header) {
        this.header = header;
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
    
    @Override
    public String toString(){
        return header +","+
                flowerCount +","+
                orderTotal;
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(header.getOrderId());
    }
    
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Order)) {
            return false;
        }
        Order o = (Order) obj;
        return o.header.getOrderId().equals(this.header.getOrderId());
    }
}
