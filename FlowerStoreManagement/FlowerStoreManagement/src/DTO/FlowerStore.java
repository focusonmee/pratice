/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.IOException;
import java.text.ParseException;
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

    private HashSet<Flower> flowerList;
    private HashSet<Order> orderList;
    private ArrayList<OrderHeader> orderIdList;
    private boolean dataChanged;

    public FlowerStore() {
        flowerList = new HashSet<Flower>();
        orderList = new HashSet<Order>();
        orderIdList = new ArrayList<>();
        dataChanged = false;
    }

    public void addFlower() {
        boolean notUpdating = false;
        //while de continue
        while (true) {
            //1. Nhap du lieu tu ban phim
            String id = Input.isId(flowerList, "Enter FLower Id; ");
            String name = Input.isName(flowerList, "Enter Flower Name: ");
            String description = Input.isDescription("Enter FLower Description", notUpdating);
            Date importDate = Input.isDate("Enter Flower Import Date: ", notUpdating);
            double unitPrice = Input.isDouble("Enter the Flower Unit Price: ", notUpdating);
            String category = Input.isString("Enter Flower Category", notUpdating);

            //2. Tao doi tuong Nurse
            Flower newFlower = new Flower(id, name, description, importDate, unitPrice, category);

            //3. Them doi tuong nurse moi tao vao nurseList
            flowerList.add(newFlower);
            dataChanged = true;
            //4. Chon no de thoat khoi vong lap
            if (Input.isYesNo("Do you want to continue (Y/N)").equalsIgnoreCase("n")) {
                break;
            }
        }
    }

    public void findFLower() {
        //1. khai bao bien
        boolean isFound = false;
        boolean notUpdating = false;
        String searchKey;
        int searchChoice;
        Iterator iter = flowerList.iterator();

        //2. Nhap du lieu tu ban phim
        while (true) {
            searchChoice = Input.isInt("Select FLower Name[1] or Id[2] to Search: ", notUpdating);
            if (searchChoice > 2) {
                System.out.println("Please choose from 1 to 2 only!");
            } else {
                break;
            }
        }

        if (searchChoice == 1) {
            searchKey = Input.isString("Enter the Flower Name: ", notUpdating).toLowerCase().trim();
            while (iter.hasNext()) {
                Flower currentFlower = (Flower) iter.next();
                if (currentFlower.getName().toLowerCase().trim().contains(searchKey)) {
                    System.out.println(currentFlower);
                    isFound = true;
                }
            }
        }

        if (searchChoice == 2) {
            searchKey = Input.isString("Enter the Flower Id: ", notUpdating).toLowerCase().trim();
            while (iter.hasNext()) {
                Flower currentFlower = (Flower) iter.next();
                if (currentFlower.getId().toLowerCase().trim().contains(searchKey)) {
                    System.out.println(currentFlower);
                    isFound = true;
                }
            }
        }
        //4.2. Khong thay thi in not exist
        if (isFound == false) {
            System.out.println("The Flower does not exist");
        }
    }

    public Flower findFLowerByName(String name) {
        Iterator iter = flowerList.iterator();
        while (iter.hasNext()) {
            Flower currentFlower = (Flower) iter.next();
            if (currentFlower.getName().toLowerCase().trim().equals(name)) {
                return currentFlower;
            }
        }
        return null;
    }

    public Flower findFLowerById(String id) {
        Iterator iter = flowerList.iterator();
        while (iter.hasNext()) {
            Flower currentFlower = (Flower) iter.next();
            if (currentFlower.getId().equals(id)) {
                return currentFlower;
            }
        }
        return null;
    }

    public void updateFLower() {
        boolean notUpdating = false;
        boolean isUpdating = true;
        // 1.input data staff id to update 
        String inputName = Input.isString("Enter Flower Name to update: ", notUpdating);

        // 2.find nurse and return nurse by id 
        Flower updatingFlower = findFLowerByName(inputName.trim());

        // 3.use try catch to notify success or failure state
        try {
            //4. if found nurse then update
            if (updatingFlower != null) {
                // 5.input new data nurse, if not input keep the existing value
                String id = updatingFlower.getId();
                String description = Input.isDescription("Enter FLower Description", isUpdating);
                if (description.length() == 0) {
                    description = updatingFlower.getName();
                }
                Date importDate = Input.isDate("Enter Flower Import Date: ", isUpdating);
                if (importDate == null) {
                    importDate = updatingFlower.getImportDate();
                }
                double unitPrice = Input.isDouble("Enter the Flower Unit Price: ", isUpdating);
                if (unitPrice == 0) {
                    unitPrice = updatingFlower.getUnitPrice();
                }
                String category = Input.isString("Enter Flower Category", isUpdating);
                if (category.length() == 0) {
                    category = updatingFlower.getCategory();
                }

                // 6.put nurse into list 
                Flower newFlower = new Flower(id, inputName, description, importDate, unitPrice, category);
                flowerList.remove(updatingFlower);
                flowerList.add(newFlower);
                //7. print out success state 
                System.out.println("Update Success!");
                dataChanged = true;

                // 8.else print does not exist 
            } else {
                System.out.println("The Flower does not exist!");
            }
            // 7.1 if has exception print failure state
        } catch (Exception e) {
            System.out.println("Update Failed !");
        }
    }

    public void deleteFlower() {
        boolean notUpdating = false;
        // 1.input data staff id to update 
        String inputId = Input.isString("Enter Flower Id to Delete: ", notUpdating);

        // 2.find nurse and return nurse by id 
        Flower deletingFlower = findFLowerById(inputId.trim());

        // 3.use try catch to notify success or failure state
        try {
            //4. if found nurse then delete
            if (deletingFlower != null) {
                // 5.check if the nurse have a task (is taking care of any patient)
                if (deletingFlower.isIsInOrder() == true) {
                    System.out.println("The Flower cannot be deleted!");
                    System.out.println("Flower is currently in a order!");
                    System.out.println("Delete Failed !");
                } else {
                    // 6.delete nurse from list
                    System.out.println(deletingFlower);
                    if (Input.isYesNo("Do you want to delete Flower " + deletingFlower.getName() + " (Y/N)").equalsIgnoreCase("y")) {
                        flowerList.remove(deletingFlower);
                        //7. print out success state 
                        System.out.println("Delete Success!");
                        dataChanged = true;
                    }
                }
                // 8.else print does not exist 
            } else {
                System.out.println("The Flower does not exist!");
            }
            // 7.1 if has exception print failure state
        } catch (Exception e) {
            System.out.println("Failure Updated !");
        }
    }

    public void addOrder() {
        boolean notUpdating = false;
        while (true) {
            ArrayList<OrderDetail> details = new ArrayList<>();
            System.out.println("---Add a Order---");
            //Input order Header
            String orderId = Input.isOrderId(orderList, "Enter Order Id: ");
            Date orderDate = Input.isDate("Enter Order Date: ", notUpdating);
            String orderCustomerName = Input.isString("Enter the Order Customer Name: ", notUpdating);
            OrderHeader header = new OrderHeader(orderId, orderDate, orderCustomerName);
            orderIdList.add(header);

            while (true) {
                System.out.println("\nOrder: " + header);
                if (details.isEmpty()) {
                    System.out.println("[ No detail yet ]\n");
                } else {
                    for (OrderDetail detail : details) {
                        System.out.println("[ " + detail + " ]\n");
                    }
                }
                System.out.println("Enter detail for your order: ");
                String orderDetailId = Input.isOrderDetailId(details, "Enter order detail id: ");
                String flowerId = Input.isFlower(flowerList, "Enter Flower Id: ");
                int quantity = Input.isInt("Enter Flower Quantity: ", notUpdating);
                double flowerCost = quantity * findFLowerById(flowerId).getUnitPrice();
                OrderDetail detail = new OrderDetail(orderDetailId, flowerId, quantity, flowerCost);
                details.add(detail);

                if (Input.isYesNo("Do you want to continue add more detail to your order (Y/N)").equalsIgnoreCase("n")) {
                    System.out.println("\nOrder: " + header);
                    for (OrderDetail detail1 : details) {
                        System.out.println("[ " + detail1 + " ]");
                    }
                    break;
                }
            }
            Order newOrder = new Order(header, details);
            orderList.add(newOrder);
            dataChanged = true;

            if (Input.isYesNo("\nDo you want to continue add another order (Y/N)").equalsIgnoreCase("n")) {
                break;
            }
        }
    }

    public void displayOrderByDate() {
        boolean notUpdating = false;

        //1. input start and end date tu ban phim
        Date startDate = Input.isDate("Input start date: ", notUpdating);
        Date endDate = Input.isDate("Input end date: ", notUpdating);

        //2. out put the list
        System.out.println("LIST OF PATIENTS");
        Iterator iter = orderList.iterator();
        int i = 1;
        while (iter.hasNext()) {
            Order currentOrder = (Order) iter.next();
            Date checkDate = currentOrder.getHeader().getOrderDate();
            if (checkDate.compareTo(startDate) >= 0 && checkDate.compareTo(endDate) <= 0) {
                System.out.println("No." + (i++) + "," + currentOrder);
            }
        }
    }

    public void sortOrders() {
        boolean notUpdating = false;
        int field;
        int sortOrder;

        //1. input field and sort order
        while (true) {
            field = Input.isInt("Enter the field to search (OrderId[1] OrderDate[2] CustomeName[3] OrderTotal[4]): ", notUpdating);
            if (field > 4) {
                System.out.println("Please choose from 1 to 4 only!");
            } else {
                break;
            }
        }
        while (true) {
            sortOrder = Input.isInt("Enter the sort order (ASC[1] or DESC[2]): ", notUpdating);
            if (sortOrder > 2) {
                System.out.println("Please choose from 1 to 2 only!");
            } else {
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
        switch (field) {
            case 1:
                if (sortOrder == 1) {
                    Collections.sort(orderArrayList, (Order o1, Order o2) -> {
                        int d = o1.getHeader().getOrderId().compareTo(o2.getHeader().getOrderId());
                        if (d > 0) {
                            return 1;
                        }
                        if (d == 0) {
                            return 1;
                        }
                        return -1;
                    });
                } else {
                    Collections.sort(orderArrayList, (Order o1, Order o2) -> {
                        int d = o1.getHeader().getOrderId().compareTo(o2.getHeader().getOrderId());
                        if (d > 0) {
                            return -1;
                        }
                        if (d == 0) {
                            return 1;
                        }
                        return 1;
                    });
                }
                break;
            case 2:
                if (sortOrder == 1) {
                    Collections.sort(orderArrayList, (Order o1, Order o2) -> {
                        int d = o1.getHeader().getOrderDate().compareTo(o2.getHeader().getOrderDate());
                        if (d > 0) {
                            return 1;
                        }
                        if (d == 0) {
                            return 1;
                        }
                        return -1;
                    });
                } else {
                    Collections.sort(orderArrayList, (Order o1, Order o2) -> {
                        int d = o1.getHeader().getOrderDate().compareTo(o2.getHeader().getOrderDate());
                        if (d > 0) {
                            return -1;
                        }
                        if (d == 0) {
                            return 1;
                        }
                        return 1;
                    });
                }
                break;
            case 3:
                if (sortOrder == 1) {
                    Collections.sort(orderArrayList, (Order o1, Order o2) -> {
                        int d = o1.getHeader().getOrderCustomerName().compareTo(o2.getHeader().getOrderCustomerName());
                        if (d > 0) {
                            return 1;
                        }
                        if (d == 0) {
                            return 1;
                        }
                        return -1;
                    });
                } else {
                    Collections.sort(orderArrayList, (Order o1, Order o2) -> {
                        int d = o1.getHeader().getOrderCustomerName().compareTo(o2.getHeader().getOrderCustomerName());
                        if (d > 0) {
                            return -1;
                        }
                        if (d == 0) {
                            return 1;
                        }
                        return 1;
                    });
                }
                break;
            case 4:
                if (sortOrder == 1) {
                    Collections.sort(orderArrayList, (Order o1, Order o2) -> {
                        double d1 = o1.getOrderTotal();
                        double d2 = o2.getOrderTotal();
                        double d = d1 - d2;
                        if (d > 0) {
                            return 1;
                        }
                        if (d == 0) {
                            return 1;
                        }
                        return -1;
                    });
                } else {
                    Collections.sort(orderArrayList, (Order o1, Order o2) -> {
                        double d1 = o1.getOrderTotal();
                        double d2 = o2.getOrderTotal();
                        double d = d1 - d2;
                        if (d > 0) {
                            return -1;
                        }
                        if (d == 0) {
                            return 1;
                        }
                        return 1;
                    });
                }
                break;
        }

        //.4 output the sorted arrayList
        for (Order order : orderArrayList) {
            System.out.println(order);
        }
    }

    public void display() {
        int i = 1;
        Iterator iter = flowerList.iterator();
        System.out.println("Flower List: ");
        if (flowerList.isEmpty()) {
            System.out.println("List is Empty!");
        }
        while (iter.hasNext()) {
            Flower currentFLower = (Flower) iter.next();
            System.out.println(currentFLower);
        }
        System.out.println("\nOrder List: ");
        if (orderList.isEmpty()) {
            System.out.println("List is Empty!");
        }
        Iterator iter1 = orderList.iterator();
        while (iter1.hasNext()) {
            Order currentOrder = (Order) iter1.next();
            System.out.println("No." + i + "," + currentOrder);
            i++;
        }
    }

    public void saveData() {
        try {
            SaveFile.Flower(flowerList);
            SaveFile.Order(orderList);
        } catch (IOException e) {
            System.out.println("Error occurred while saving data: " + e.getMessage());
        }
        System.out.println("Data saved successfully.");
    }

    public void loadData() {
        try {
            flowerList = LoadFile.Flower(flowerList);
            orderList = LoadFile.Order(orderList);
        } catch (IOException | ParseException e) {
            System.out.println("Error occurred while loading data: " + e.getMessage());
        }
        System.out.println("Data Loaded successfully.");
    }

    public void quit() {
        if (Input.isYesNo("Do you want to quit (Y/N)").equalsIgnoreCase("y")) {
            if (dataChanged) {
                saveData();
            }
            System.out.println("Exiting the program...");
            System.exit(0);
        }
    }

    public void updateAllFlowerNameWithBoolean() {
        boolean isUpdating = true;
        String inputBoolean = Input.isBoolean("Enter Flower state to update (T/F): ");

        for (Flower currentFlower : flowerList) {
            if (currentFlower.isIsInOrder() == Boolean.parseBoolean(inputBoolean)) {
                String id = currentFlower.getId();
                String name = currentFlower.getName();
                String description = Input.isDescription("Enter FLower Description", isUpdating);
                if (description.length() == 0) {
                    description = currentFlower.getName();
                }
                Date importDate = Input.isDate("Enter Flower Import Date: ", isUpdating);
                if (importDate == null) {
                    importDate = currentFlower.getImportDate();
                }
                double unitPrice = Input.isDouble("Enter the Flower Unit Price: ", isUpdating);
                if (unitPrice == 0) {
                    unitPrice = currentFlower.getUnitPrice();
                }
                String category = Input.isString("Enter Flower Category", isUpdating);
                if (category.length() == 0) {
                    category = currentFlower.getCategory();
                }
                Flower newFlower = new Flower(id, name, description, importDate, unitPrice, category);
                newFlower.setIsInOrder(Boolean.parseBoolean(inputBoolean));
                flowerList.add(newFlower);
                System.out.println("Update Success!");
                dataChanged = true;
            }
        }
    }
}
