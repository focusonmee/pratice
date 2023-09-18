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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author laptoptk
 */
public class Input {
    static Scanner sc = new Scanner(System.in);
    static private Iterator iter;
    
    public static String isYesNo(String msg){
        String input = "";
        int count = 0;
        do {
            if (count >0) {
                System.out.println("Invalid format!");
            }
            System.out.println(msg);
            input = sc.nextLine();
            count++;
        } while (!input.matches("y|n|Y|N") && count != 10);
        return input;
    }
    
    // check string,  not null
    public static String isString (String msg, boolean isUpdating){
        String input = "";
        int count = 0;
        do {
            if (count >0) {
                System.out.println("Cannot be null!");
            }
            System.out.println(msg);
            input = sc.nextLine();
            // if is updating skip the validation
            if (isUpdating==true && input.length()==0) {
                return input;
            }
            count++;
        } while (input.length() == 0 && count != 10);
        return input;
    }
    
    public static String isId(HashSet<Flower> list, String msg){
        String input = "";
        int count = 0;
        boolean isExisted;
        boolean notUpdating = false;
        do {
            if (count >0) {
                System.out.println("Flower Id is existed!");
            }
            isExisted = false;
            input = Input.isString(msg, notUpdating).trim();
            iter = list.iterator();
            while (iter.hasNext()) {                
                Flower currentFlower = (Flower) iter.next();
                if (currentFlower.getId().equals(input)) {
                    isExisted = true;
                }
            }
            count++;
        } while (isExisted == true && count != 10);
        return input;
    }
    
    public static String isOrderDetailId(ArrayList<OrderDetail> list, String msg){
        String input = "";
        int count = 0;
        boolean isExisted;
        boolean notUpdating = false;
        do {
            if (count >0) {
                System.out.println("Order Detail Id is existed!");
            }
            isExisted = false;
            input = Input.isString(msg, notUpdating).trim();
            for (OrderDetail orderDetail : list) {
                if (orderDetail.getOrderDetailId().equals(input)) {
                    isExisted = true;
                }
            }
            count++;
        } while (isExisted == true && count != 10);
        return input;
    }
    
    public static String isOrderId(HashSet<Order> list, String msg){
        String input = "";
        int count = 0;
        boolean isExisted;
        boolean notUpdating = false;
        do {
            if (count >0) {
                System.out.println("Order Id is existed!");
            }
            isExisted = false;
            input = Input.isString(msg, notUpdating).trim();
            for (Order order : list) {
                if (order.getHeader().getOrderId().equals(input)) {
                    isExisted = true;
                }
            }
            count++;
        } while (isExisted == true && count != 10);
        return input;
    }
    
    public static String isName(HashSet<Flower> list, String msg){
        String input = "";
        int count = 0;
        boolean isExisted;
        boolean notUpdating = false;
        do {
            if (count >0) {
                System.out.println("Flower Name is existed!");
            }
            isExisted = false;
            input = Input.isString(msg, notUpdating).toLowerCase().trim();
            iter = list.iterator();
            while (iter.hasNext()) {                
                Flower currentFlower = (Flower) iter.next();
                if (currentFlower.getName().toLowerCase().trim().equals(input)) {
                    isExisted = true;
                }
            }
            count++;
        } while (isExisted == true && count != 10);
        return input;
    }
    
    public static String isDescription (String msg, boolean isUpdating){
        String input = "";
        int count = 0;
        do {
            if (count >0) {
                System.out.println("Only 3 to 50 character!");
            }
            System.out.println(msg);
            input = sc.nextLine();
            // if is updating skip the validation
            if (isUpdating==true && input.length()==0) {
                return input;
            }
            count++;
        } while ((input.length() < 3 || input.length() > 50) && count != 10);
        return input;
    }
    
    public static Date isDate(String msg, boolean isUpdating){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String input = "";
        Date d;
        while(true){
            System.out.println(msg);
            input = sc.nextLine();
            if (isUpdating==true && input.length()==0) {
                return null;
            }
            try{
                d = df.parse(input);
                return d;
            }catch(ParseException e){
                System.out.println("Invalid Date Format!");
            }
        }
    }
    
    public static double isDouble(String msg, boolean isUpdating){
        while(true){
            System.out.println(msg);
            String input = sc.nextLine();
            // if is updating skip the validation
            if (isUpdating==true && input.length()==0) {
                return 0;
            }
            try{
                double db = Double.parseDouble(input);
                if (db>0) {
                    return db;
                }else{
                    System.out.println("Invalid format!");
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid format!");
            }
        }
    }
    
    public static int isInt(String msg, boolean isUpdating){
        while(true){
            System.out.println(msg);
            String input = sc.nextLine();
            // if is updating skip the validation
            if (isUpdating==true && input.length()==0) {
                return 0;
            }
            try{
                int i = Integer.parseInt(input);
                if (i<=100 && i>0) {
                    return i;
                }else{
                    System.out.println("Invalid format!");
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid format!");
            }
        }
    }
    
    public static String isFlower(HashSet<Flower> list, String msg){
        String inputFlowerId = "";
        Flower newFLower = null;
        boolean isExisted = false;
        boolean notUpdating = false;
        while (true) {  
            inputFlowerId = Input.isString(msg, notUpdating).trim();
            iter = list.iterator();
            while (iter.hasNext()) {                
                Flower currentFlower = (Flower) iter.next();
                if (currentFlower.getId().equals(inputFlowerId)) {
                    newFLower = currentFlower;
                    isExisted = true;
                }
            }
            
            if (isExisted) {
                list.remove(newFLower);
                newFLower.setIsInOrder(true);
                list.add(newFLower);
                return inputFlowerId;
            } else{
                System.out.println("Flower is not in the list!");
            }
        }
    }
    
    public static String isTest (String msg){
        String input = "";
        int count = 0;
        do {
            if (count >0) {
                System.out.println("Cannot be null!");
            }
            System.out.println(msg);
            input = sc.nextLine();
            // if is updating skip the validation
            count++;
        } while (!input.matches("^Order: [a-zA-Z0-9,/]+ ;$"));
        return input;
    }
    
    public static String isBoolean(String msg){
        String input = "";
        int count = 0;
        do {
            if (count >0) {
                System.out.println("Invalid format!");
            }
            System.out.println(msg);
            input = sc.nextLine();
            count++;
        } while (!input.matches("t|f") && count != 10);
        if (input.equals("t")) {
            return "true";
        }else
            return "false";
    }
}
