/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author acer
 */
public class Program implements Comparable<Program>{
    
    private String id;
    private String name;
    private String time;
    private Date fromDate;
    private Date endDate;
    private int days;
    private String location;
    private double cost;
    private String content;
    
    public Program() {
    }
    
    public Program(String id, String name, String time, Date fromDate, Date endDate, int days, String location, double cost, String content) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.fromDate = fromDate;
        this.endDate = endDate;
        this.days = days;
        this.location = location;
        this.cost = cost;
        this.content = content;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getTime() {
        return time;
    }
    
    public Date getFromDate() {
        return fromDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public int getDays() {
        return days;
    }
    
    public String getLocation() {
        return location;
    }
    
    public double getCost() {
        return cost;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public void setDays(int days) {
        this.days = days;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Program{" + "id=" + id + ", name=" + name + ", time=" + time + ","
                + " fromDate=" + sdf.format(fromDate) + ", endDate=" + sdf.format(endDate) + ","
                + " days=" + days + ", location=" + location + ","
                + " cost=" + cost + ", content=" + content + '}';
    }

     @Override
    public int compareTo(Program other) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date thisDate = this.fromDate;
            Date otherDate = other.fromDate;
            return thisDate.compareTo(otherDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    
    
}
