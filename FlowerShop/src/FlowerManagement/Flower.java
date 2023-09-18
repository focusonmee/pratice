/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlowerManagement;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author laptoptk
 */
public class Flower implements Serializable {

    //Khai bao bien
    private String id;
    private String name;
    private String description;
    private String date;
    private double unitPrice;
    private String category;
    private boolean isInOrder;

    //Constuctor
    public Flower() {
    }

    public Flower(String id, String name, String description, String date, double unitPrice, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.unitPrice = unitPrice;
        this.category = category;
        this.isInOrder = false;
    }

    //Getter Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isIsInOrder() {
        return isInOrder;
    }

    public void setIsInOrder(boolean isInOrder) {
        this.isInOrder = isInOrder;
    }

    //Output method
    public void print() {
        System.out.println("Flower Id: " + id + " - "
                + "Name: " + name + " - "
                + "Description: " + description + " - "
                + "ImportDate: " + date + " - "
                + "Price: " + unitPrice + " - "
                + "Category: " + category);
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return id + ","
                + name + ","
                + description + ","
                + date + ","
                + unitPrice + ","
                + category + ","
                + isInOrder;
    }

    // Override hashcode and equals
    @Override
    public int hashCode() {
        return 31 + id.hashCode() + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Flower) {
            Flower anotherFLower = (Flower) obj;
            if (this.getId().equals(anotherFLower.getId())
                    && this.getName().equals(anotherFLower.getName())) {
                return true;
            }
        }
        return false;
    }
}
