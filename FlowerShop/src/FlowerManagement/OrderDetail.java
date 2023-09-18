/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlowerManagement;

/**
 *
 * @author laptoptk
 */
public class OrderDetail {
    private String orderDetailId;
    private String flowerId;
    private int quantity;
    private double flowerCost;

    public OrderDetail() {
    }

    public OrderDetail(String orderDetailId, String flowerId, int quantity, double flowerCost) {
        this.orderDetailId = orderDetailId;
        this.flowerId = flowerId;
        this.quantity = quantity;
        this.flowerCost = flowerCost;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public String getFlowerId() {
        return flowerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getFlowerCost() {
        return flowerCost;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public void setFlowerId(String flowerId) {
        this.flowerId = flowerId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setFlowerCost(double flowerCost) {
        this.flowerCost = flowerCost;
    }
    
    @Override
    public String toString(){
        return orderDetailId + "," +
                flowerId + "," + 
                quantity + "," +
                flowerCost;
    }
}
