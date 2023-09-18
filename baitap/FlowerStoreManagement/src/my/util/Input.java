/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.util;

import DTO.Flower;
import DTO.Order;
import DTO.OrderDetail;
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
    public static final boolean NOT_UPDATING = false;
    public static final boolean IS_UPDATING = true;
    
    //check yes no
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
    
    //check is Id existed cho flower
    public static String isFlowerId(HashSet<Flower> list, String msg){
        String input = "";
        int count = 0;
        boolean isExisted;
        do {
            if (count >0) {
                System.out.println("Flower Id is existed!");
            }
            isExisted = false;
            input = Input.isString(msg, NOT_UPDATING).trim();
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
    
    //check is Id existed cho orderDetail
    public static String isOrderDetailId(ArrayList<OrderDetail> list, String msg){
        String input = "";
        int count = 0;
        boolean isExisted;
        do {
            if (count >0) {
                System.out.println("Order Detail Id is existed!");
            }
            isExisted = false;
            input = Input.isString(msg, NOT_UPDATING).trim();
            for (OrderDetail orderDetail : list) {
                if (orderDetail.getOrderDetailId().equals(input)) {
                    isExisted = true;
                }
            }
            count++;
        } while (isExisted == true && count != 10);
        return input;
    }
    
    //check is Id existed cho Order
    public static String isOrderId(HashSet<Order> list, String msg){
        String input = "";
        int count = 0;
        boolean isExisted;
        do {
            if (count >0) {
                System.out.println("Order Id is existed!");
            }
            isExisted = false;
            input = Input.isString(msg, NOT_UPDATING).trim();
            iter = list.iterator();
            while (iter.hasNext()) {                
                Order currentOrder = (Order) iter.next();
                if (currentOrder.getOrderId().equals(input)) {
                    isExisted = true;
                }
            }
            count++;
        } while (isExisted == true && count != 10);
        return input;
    }
    
    //check is name existed cho Flower
    public static String isName(HashSet<Flower> list, String msg){
        String input = "";
        int count = 0;
        boolean isExisted;
        do {
            if (count >0) {
                System.out.println("Flower Name is existed!");
            }
            isExisted = false;
            input = Input.isString(msg, NOT_UPDATING).trim();
            iter = list.iterator();
            while (iter.hasNext()) {                
                Flower currentFlower = (Flower) iter.next();
                if (currentFlower.getName().trim().equalsIgnoreCase(input)) {
                    isExisted = true;
                }
            }
            count++;
        } while (isExisted == true && count != 10);
        return input;
    }
    
    //check Flower descriptioon >3 <50
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
    
    //check Date format
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
    
    //check Double format
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
    
    //check Integer format, > 0
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
                if (i>0) {
                    return i;
                }else{
                    System.out.println("Invalid format!");
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid format!");
            }
        }
    }
    
    //Check flower in the list, trong add order
    public static String isFlower(HashSet<Flower> list, String msg){
        String inputFlowerId = "";
        while (true) {  
            inputFlowerId = Input.isString(msg, NOT_UPDATING).trim();
            iter = list.iterator();
            while (iter.hasNext()) {                
                Flower currentFlower = (Flower) iter.next();
                if (currentFlower.getId().equals(inputFlowerId)) {
                    currentFlower.setIsInOrder(true);
                    return inputFlowerId;
                }
            }
            System.out.println("Flower is not in the list!");
        }
    }
    
    public static String isTest (String msg){
        String input = "";
        int count = 0;
        do {
            if (count >0) {
                System.out.println("Invalid!");
            }
            System.out.println(msg);
            input = sc.nextLine();
            // if is updating skip the validation
            count++;
        } while (!input.matches("^Order [a-zA-Z0-9,/]+ :$"));
        return input;
    }
}
