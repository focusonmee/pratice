/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author laptoptk
 */
public class Flower {
    private String id;
    private String name;
    private String description;
    private Date importDate;
    private double unitPrice;
    private String category;
    
    //khac nhau
    private boolean isInOrder;

    public Flower() {
    }

    public Flower(String id, String name, String description, Date importDate, double unitPrice, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.importDate = importDate;
        this.unitPrice = unitPrice;
        this.category = category;
        
        this.isInOrder = false;
    }


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

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
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
    
    @Override
    public String toString(){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return id + "," +
                name + "," + 
                description + "," + 
                df.format(importDate) + "," + 
                unitPrice + "," + 
                category + "," +
                isIsInOrder();
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(id,name);
    }
    
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Flower)) {
            return false;
        }
        Flower f = (Flower) obj;
        return f.getId().equals(this.getId())
                && f.getName().equals(this.getName());
    }
}
