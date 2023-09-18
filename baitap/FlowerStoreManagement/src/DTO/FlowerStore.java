/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import my.util.Input;
import my.util.LoadFile;
import my.util.SaveFile;

/**
 *
 * @author laptoptk
 */
public class FlowerStore {
    //Khai bao bien
    private HashSet<Flower> flowerList;
    private HashSet<Order> orderList;
    private boolean dataChanged;

    //Constructor
    public FlowerStore() {
        flowerList  = new HashSet<Flower>();
        orderList  = new HashSet<Order>();
        dataChanged = false;
    }
    
    //Them Flower vao list
    public void addFlower(){
        //while de continue
        while(true){
            //1. Nhap du lieu tu ban phim
            String id = Input.isFlowerId(flowerList,"Enter FLower Id; ");
            String name = Input.isName(flowerList,"Enter Flower Name: ");
            String description = Input.isDescription("Enter FLower Description", Input.NOT_UPDATING);
            Date importDate = Input.isDate("Enter Flower Import Date: ", Input.NOT_UPDATING);
            double unitPrice = Input.isDouble("Enter the Flower Unit Price: ", Input.NOT_UPDATING);
            String category = Input.isString("Enter Flower Category", Input.NOT_UPDATING);

            //2. Tao doi tuong Flower
            Flower newFlower = new Flower(id, name, description, importDate, unitPrice, category);

            //3. Them doi tuong nurse moi tao vao flowerList
            flowerList.add(newFlower);
            dataChanged = true;
            
            //4. Chon no de thoat khoi vong lap
            if(Input.isYesNo("Do you want to continue (Y/N)").equalsIgnoreCase("n")){
                break;
            }
        }
    }
    
    //Tim flower theo name hoac id
    public void findFLower(){
        //1. khai bao bien
        boolean isFound = false;
        String searchKey;
        int searchChoice;
        Iterator iter = flowerList.iterator();

        //2. Chon cach search
        while (true) {            
            searchChoice = Input.isInt("Select FLower Name[1] or Id[2] to Search: ", Input.NOT_UPDATING);
            if (searchChoice>2) {
                System.out.println("Please choose from 1 to 2 only!");
            }else{
                break;
            }
        }
        
        //3.1 xu ly search
        if (searchChoice == 1) {
            searchKey = Input.isString("Enter the Flower Name: ", Input.NOT_UPDATING).toLowerCase().trim();
            while (iter.hasNext()) {
                Flower currentFlower = (Flower) iter.next();
                if (currentFlower.getName().toLowerCase().trim().contains(searchKey)) {
                    currentFlower.print();
                    isFound = true;
                }
            }
        }
        if (searchChoice == 2) {
            searchKey = Input.isString("Enter the Flower Id: ", Input.NOT_UPDATING).toLowerCase().trim();
            while (iter.hasNext()) {
                Flower currentFlower = (Flower) iter.next();
                if (currentFlower.getId().toLowerCase().trim().contains(searchKey)) {
                    currentFlower.print();
                    isFound = true;
                }
            }
        }
        //3.2 Khong thay thi in not exist
        if (isFound==false) {
            System.out.println("The Flower does not exist");
        }
    }
    
    //Tim flower by name return flower/null cho update method
    public Flower findFLowerByName(String name){
        Iterator iter = flowerList.iterator();
        //1. Dung while chay het list
        while (iter.hasNext()) {
            Flower currentFlower = (Flower) iter.next();
            //2. If name bang nhau thi tra ve Flower do
            if (currentFlower.getName().trim().equalsIgnoreCase(name.trim())) {
                return currentFlower;
            }
        }
        //3. khong bang thi tra ve null
        return null;
    }
    
    //Tim flower by id reurn flower/null cho delete method
    public Flower findFLowerById(String id){
        Iterator iter = flowerList.iterator();
        //1. Dung while chay het list
        while (iter.hasNext()) {
            Flower currentFlower = (Flower) iter.next();
            //2. If id bang nhau thi tra ve Flower do
            if (currentFlower.getId().trim().equals(id.trim())) {
                return currentFlower;
            }
        }
        //3. khong bang thi tra ve null
        return null;
    }
    
    //Update flower by name
    public void updateFLower(){
        // 1.input data Flower name to update 
        String inputName = Input.isString("Enter Flower Name to update: ", Input.NOT_UPDATING); 
        
        // 2.find flower and return flower by name 
        Flower updatingFlower = findFLowerByName(inputName);
        
        // 3.use try catch to notify success or failure state
        try{
            //4. if found Flower then update
            if(updatingFlower != null){
                // 5.input new data cho flower, if not input keep the existing value
                String id = updatingFlower.getId();
                String name = updatingFlower.getName();
                String description = Input.isDescription("Enter FLower Description", Input.IS_UPDATING);
                if (description.length()==0) description = updatingFlower.getName();
                Date importDate = Input.isDate("Enter Flower Import Date: ", Input.IS_UPDATING);
                if (importDate==null) importDate = updatingFlower.getImportDate();
                double unitPrice = Input.isDouble("Enter the Flower Unit Price: ", Input.IS_UPDATING);
                if (unitPrice==0) unitPrice = updatingFlower.getUnitPrice();
                String category = Input.isString("Enter Flower Category", Input.IS_UPDATING);
                if (category.length()==0) category = updatingFlower.getCategory();
                
                // 6.put flower into list 
                Flower newFlower = new Flower(id, name, description, importDate, unitPrice, category);
                flowerList.remove(updatingFlower);
                flowerList.add(newFlower);
                 //7. print out success state 
                System.out.println("Update Success!");
                dataChanged = true;

            // 8.else print does not exist 
            }else{
                System.out.println("The Flower does not exist!");
            }
        // 7.1 if has exception print failure state
        }catch(Exception e){
            System.out.println("Update Failed !");
        }
    }
    
    //Delete flower by id
    public void deleteFlower(){
        // 1.input flower id to delete 
        String inputId = Input.isString("Enter Flower Id to Delete: ", Input.NOT_UPDATING); 
        
        // 2.find flower and return flower by id 
        Flower deletingFlower = findFLowerById(inputId);
        
        // 3.use try catch to notify success or failure state
        try{
            //4. if found flower then delete
            if(deletingFlower != null){
                // 5.check if the flower is in any order)
                if (deletingFlower.isIsInOrder()==true) {
                    System.out.println("The Flower cannot be deleted!");
                    System.out.println("Flower is currently in a order!");
                    System.out.println("Delete Failed !");
                }else{
                    // 6.delete flower from list
                    deletingFlower.print();
                    if(Input.isYesNo("Do you want to delete Flower " + deletingFlower.getName() + " (Y/N)").equalsIgnoreCase("y")){
                        flowerList.remove(deletingFlower);
                         //7. print out success state 
                        System.out.println("Delete Success!");
                        dataChanged = true;
                    }
                }
            // 8.else print does not exist 
            }else{
                System.out.println("The Flower does not exist!");
            }
        // 7.1 if has exception print failure state
        }catch(Exception e){
            System.out.println("Failure Updated !");
        }
    }
    
    //Add order
    public void addOrder(){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        //check if flower list is empty
        if (flowerList.isEmpty()) {
            System.out.println("No Flower yet!");
            return;
        }
        //Dung while de continue add order
        while (true) { 
            int totalFlowerCount = 0;
            double totalOrderCost = 0;
            ArrayList<OrderDetail> details = new ArrayList<>();
            System.out.println("---Add a Order---");
            //Input order Header
            String orderId = Input.isOrderId(orderList, "Enter Order Id: ");
            Date orderDate = Input.isDate("Enter Order Date: ", Input.NOT_UPDATING);
            String orderCustomerName = Input.isString("Enter the Order Customer Name: ", Input.NOT_UPDATING);
            
            //dung while de contiune add order detail
            while (true) { 
                //In order dang co
                System.out.println("\nOrder: "+ orderId +" - "+ df.format(orderDate) +" - "+ orderCustomerName);
                if (details.isEmpty()) {
                    System.out.println("[ No detail yet ]");
                }else{
                    for (OrderDetail detail : details) {
                        System.out.println("[ " + detail + " ]");
                    }
                }
                // Ipnut order detail
                System.out.println("\nEnter detail for your order: ");
                String orderDetailId = Input.isOrderDetailId(details, "Enter order detail id: ");
                String flowerId = Input.isFlower(flowerList, "Enter Flower Id: ");
                int quantity = Input.isInt("Enter Flower Quantity: ", Input.NOT_UPDATING);
                double flowerCost = quantity * findFLowerById(flowerId).getUnitPrice();
                OrderDetail detail = new OrderDetail(orderDetailId, flowerId, quantity, flowerCost);
                details.add(detail);
                totalFlowerCount += detail.getQuantity();
                totalOrderCost += detail.getFlowerCost();
                
                
                if(Input.isYesNo("Do you want to continue add more detail to your order (Y/N)").equalsIgnoreCase("n")){
                    System.out.println("\nOrder: "+ orderId +" - "+ df.format(orderDate) +" - "+ orderCustomerName);
                    for (OrderDetail detail1 : details) {
                        System.out.println("[ " + detail1 + " ]");
                    }
                    break;
                }
            }
            //Tao order moi, add vao orderList
            Order newOrder = new Order(orderId, orderDate, orderCustomerName, details);
            orderList.add(newOrder);
            System.out.println("Total Flower Count: " + totalFlowerCount);
            System.out.println("Total Order Cost: " + totalOrderCost);
            dataChanged = true;
            
            if(Input.isYesNo("\nDo you want to continue add another order (Y/N)").equalsIgnoreCase("n")){
                break;
            }
        }
    }
    
    //Display order by date
    public void displayOrderByDate(){
        //1. input start and end date tu ban phim
        Date startDate = Input.isDate("Input start date: ", Input.NOT_UPDATING);
        Date endDate = Input.isDate("Input end date: ", Input.NOT_UPDATING);
        int totalFlowerCount = 0;
        double totalOrderCost = 0;
        
        //2. out put the list
        System.out.println("LIST OF Order");
        Iterator iter = orderList.iterator();
        int i =1;
        while (iter.hasNext()) {
            Order currentOrder = (Order) iter.next();
            Date checkDate = currentOrder.getOrderDate();
            if (checkDate.compareTo(startDate) >= 0 && checkDate.compareTo(endDate) <= 0) {
                System.out.println("No." + (i++) + "," + currentOrder);
                totalFlowerCount += currentOrder.getFlowerCount();
                totalOrderCost += currentOrder.getOrderTotal();
            }
        }
        System.out.println("Total Flower Count: " + totalFlowerCount);
        System.out.println("Total Orders Cost: " + totalOrderCost);
    }
    
    //Sort Order
    public void sortOrders(){
        boolean notUpdating = false;
        int field;
        int sortOrder;
        
        //1. input field and sort order
        while (true) {            
            field = Input.isInt("Enter the field to search (OrderId[1] OrderDate[2] CustomeName[3] OrderTotal[4]): ", notUpdating);
            if (field>4) {
                System.out.println("Please choose from 1 to 4 only!");
            }else{
                break;
            }
        }
        while (true) {            
            sortOrder = Input.isInt("Enter the sort order (ASC[1] or DESC[2]): ", notUpdating);
            if (sortOrder>2) {
                System.out.println("Please choose from 1 to 2 only!");
            }else{
                break;
            }
        }
        
        //2.Convert HashMap to ArrayList
        ArrayList<Order> orderArrayList = new ArrayList<>();
        Iterator iter = orderList.iterator();
        while (iter.hasNext()) {
            Order currentOrder = (Order) iter.next();
            orderArrayList.add(currentOrder);
            
        }
        
        //3.Sort
        switch(field){
            case 1: 
                if (sortOrder==1) {
                    Collections.sort(orderArrayList, (Order o1, Order o2) -> {
                    int d = o1.getOrderId().compareTo(o2.getOrderId());
                    if (d>0) return 1;
                    if (d==0) return 1;
                    return -1;
                    });
                }else{
                    Collections.sort(orderArrayList, (Order o1, Order o2) -> {
                    int d = o1.getOrderId().compareTo(o2.getOrderId());
                    if (d>0) return -1;
                    if (d==0) return 1;
                    return 1;
                    });
                }
                break;
            case 2: 
                if (sortOrder==1) {
                    Collections.sort(orderArrayList, (Order o1, Order o2) -> {
                    int d = o1.getOrderDate().compareTo(o2.getOrderDate());
                    if (d>0) return 1;
                    if (d==0) return 1;
                    return -1;
                    });
                }else{
                    Collections.sort(orderArrayList, (Order o1, Order o2) -> {
                    int d = o1.getOrderDate().compareTo(o2.getOrderDate());
                    if (d>0) return -1;
                    if (d==0) return 1;
                    return 1;
                    });
                }
                break;
            case 3: 
                if (sortOrder==1) {
                    Collections.sort(orderArrayList, (Order o1, Order o2) -> {
                    int d = o1.getOrderCustomerName().compareTo(o2.getOrderCustomerName());
                    if (d>0) return 1;
                    if (d==0) return 1;
                    return -1;
                    });
                }else{
                    Collections.sort(orderArrayList, (Order o1, Order o2) -> {
                    int d = o1.getOrderCustomerName().compareTo(o2.getOrderCustomerName());
                    if (d>0) return -1;
                    if (d==0) return 1;
                    return 1;
                    });
                }
                break;
            case 4: 
                if (sortOrder==1) {
                    Collections.sort(orderArrayList, (Order o1, Order o2) -> {
                    double d1 = o1.getOrderTotal();
                    double d2 = o2.getOrderTotal();
                    double d = d1 -d2;
                    if (d>0) return 1;
                    if (d==0) return 1;
                    return -1;
                    });
                }else{
                    Collections.sort(orderArrayList, (Order o1, Order o2) -> {
                    double d1 = o1.getOrderTotal();
                    double d2 = o2.getOrderTotal();
                    double d = d1 -d2;
                    if (d>0) return -1;
                    if (d==0) return 1;
                    return 1;
                    });
                }
                break;
        }
        
        //.4 output the sorted arrayList
        int totalFlowerCount = 0;
        double totalOrderCost = 0;
        for (Order order : orderArrayList) {
            System.out.println(order);
            totalFlowerCount += order.getFlowerCount();
            totalOrderCost += order.getOrderTotal();
        }
        System.out.println("Total Flower Count: " + totalFlowerCount);
        System.out.println("Total Orders Cost: " + totalOrderCost);
    }
    
    //Display All
    public void displayAll(){
        int i = 1;
        //Display FLower List
        Iterator iter = flowerList.iterator();
        System.out.println("Flower List: ");
        if (flowerList.isEmpty()) {
            System.out.println("List is Empty!");
        }
        while (iter.hasNext()) {
            Flower currentFLower = (Flower) iter.next();
            currentFLower.print();
        }
        
        //Display Order List
        System.out.println("\nOrder List: ");
        if (orderList.isEmpty()) {
            System.out.println("List is Empty!");
        }
        Iterator iter1 = orderList.iterator();
        while (iter1.hasNext()) {
            Order currentOrder = (Order) iter1.next();
            System.out.println("No."+ i + " - " + currentOrder);
            i++;
        }
    }
    
    //Save data to file text
    public void saveData(){
        try {
            SaveFile.Flower(flowerList);
            SaveFile.Order(orderList);
        } catch (IOException e) {
            System.out.println("Error occurred while saving data: " + e.getMessage());
        }
        System.out.println("Data saved successfully.");
    }
    
    //Load data from file text
    public void loadData(){
        try {
            flowerList = LoadFile.Flower(flowerList);
            orderList = LoadFile.Order(orderList);
        } catch (IOException | ParseException e) {
            System.out.println("Error occurred while loading data: " + e.getMessage());
        }
        System.out.println("Data Loaded successfully.");
    }
    
    //Quit if data changed, save file
    public void quit(){
        if(Input.isYesNo("Do you want to quit (Y/N)").equalsIgnoreCase("y")){
            if (dataChanged) {
                saveData();
            }
            System.out.println("Exiting the program...");
            System.exit(0);
        }
    } 
}
