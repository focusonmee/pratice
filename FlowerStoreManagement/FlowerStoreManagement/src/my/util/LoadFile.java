/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.util;

import DTO.Flower;
import DTO.Order;
import DTO.OrderDetail;
import DTO.OrderHeader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 *
 * @author laptoptk
 */
public class LoadFile {
    public static HashSet<Flower> Flower(HashSet<Flower> list) throws IOException, ParseException{
        File f = new File("flowers.txt"); // checking the file
        FileReader fr = new FileReader(f); //read() - read theo char
        BufferedReader bf = new BufferedReader(fr); // readLine()
        String details; // a line
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        while ((details= bf.readLine()) != null) { //doc tung line den khi cuoi file (=null)
            //Splitting detail into elements
            StringTokenizer stk = new  StringTokenizer(details, ",");
            String id = stk.nextToken();
            String name = stk.nextToken();
            String description = stk.nextToken();
            Date importDate = df.parse(stk.nextToken());
            double unitPrice = Double.parseDouble(stk.nextToken());
            String category = stk.nextToken();
            boolean isInOrder = Boolean.parseBoolean(stk.nextToken());

            //create a flower
            Flower flower = new Flower(id, name, description, importDate, unitPrice, category);
            flower.setIsInOrder(isInOrder);
            list.add(flower);
        }
        bf.close();
        fr.close();
        return list;
    }
    
    public static HashSet<Order> Order(HashSet<Order> list) throws IOException, ParseException{
        File f = new File("orders.txt"); // checking the file
        FileReader fr = new FileReader(f); //read() - read theo char
        BufferedReader bf = new BufferedReader(fr); // readLine()
        String details; // a line
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        OrderHeader h = new OrderHeader();
        
        while ((details= bf.readLine()) != null) { //doc tung line den khi cuoi file (=null)
            //Splitting detail into elements
            ArrayList<OrderDetail> d= new ArrayList<>();
            Order o;
            StringTokenizer stk; 
            if (details.matches("^Order: [a-zA-Z0-9,/]+ ;$")) {
                String replace1 = details.replace("Order: ", "");
                String replace2 = replace1.replace(" ;", "");
                stk = new  StringTokenizer(replace2, ",");
                String id = stk.nextToken();
                Date orderDate = df.parse(stk.nextToken());
                String customerName = stk.nextToken();
                h = new OrderHeader(id, orderDate, customerName);
            }
            if (details.matches("^Detail: [a-zA-Z0-9,.]+ ;$")) {
                String replace1 = details.replace("Detail: ", "");
                String replace2 = replace1.replace(" ;", "");
                stk = new  StringTokenizer(replace2, ",");
                String orderDetailId = stk.nextToken();
                String flowerId = stk.nextToken();
                int quantity = Integer.parseInt(stk.nextToken());
                double flowerCost = Double.parseDouble(stk.nextToken());
                OrderDetail od = new OrderDetail(orderDetailId, flowerId, quantity, flowerCost);
                d.add(od);
            }
            if (details.matches("^Total: [a-zA-Z0-9,.]+ ;$")) {
                String replace1 = details.replace("Total: ", "");
                String replace2 = replace1.replace(" ;", "");
                stk = new  StringTokenizer(replace2, ",");
                int totalQuantity = Integer.parseInt(stk.nextToken());
                double totalOrder = Double.parseDouble(stk.nextToken());
                o = new Order(h, d);
                o.setFlowerCount(totalQuantity);
                o.setOrderTotal(totalOrder);
                list.add(o);
                h = new OrderHeader();
            }
        }
        bf.close();
        fr.close();
        return list;
    }
}
