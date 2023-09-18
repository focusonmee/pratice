/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlowerManagement;

/**
 *
 * @author acer
 */
import Tool.MyUtils;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import Tool.LoadFile;
import Tool.SaveFile;

/**
 *
 * @author acer
 */
public class Manager {

    HashSet<Flower> flowersList = new HashSet<>();
    HashSet<Order> ordersList = new HashSet<>();

    public void addFlower() {
        while (true) {
            Scanner input = new Scanner(System.in);
            // Nhập vào ID
            String id = "";
            boolean OK = false;
            do {
                System.out.println("-----Add a flower-----");
                System.out.print("Enter ID (must be unique): ");
                id = MyUtils.getString("Do not empty!!!!");
                OK = true;
                Iterator<Flower> flowerIte = flowersList.iterator();
                while (flowerIte.hasNext()) {
                    if (flowerIte.next().getId().equals(id)) {
                        System.err.println("Duplicate ID!!!!");
                        OK = false;

                    }
                }
                if (OK == true) {
                    break;
                } else {
                    continue;
                }
            } while (!OK);
            OK = false;
            //

            //nhap vao name
            String name = "";
            do {
                System.out.print("Name: ");
                name = MyUtils.getString("Not empty!!!!!");
                OK = true;
                Iterator<Flower> flowerIte = flowersList.iterator();
                while (flowerIte.hasNext()) {
                    if (flowerIte.next().getName().equals(name)) {
                        System.err.println("Duplicate name!!!!");
                        OK = false;
                    }
                }
                if (OK == true) {
                    break;
                } else {
                    continue;
                }
            } while (!OK);

            //Nhập vào description
            String description = "";
            System.out.print("Enter description(3-50 character): ");
            description = MyUtils.getDescription("Do not empty!!!!", "Wrong format (3-50) Character!!!!");

            // Nhập vào Date
            String date = "";
            System.out.print("Enter Date(dd/MM/YYYY): ");
            date = MyUtils.getDate("Do not empty!!!!", "Wrong format (dd/MM/yyyy)!!!!");

            //Nhập vào unit price
            double price = 0;
            System.out.print("Enter price(>0): ");
            price = MyUtils.getNumber("Price must be a positive number !!!!", "Do not empty!!!!");

            //nhap vao category
            String category = "";
            System.out.print("Category: ");
            category = MyUtils.getString("Not empty!!!!");

            Flower newflower = new Flower(id, name, description, date, price, category);
            flowersList.add(newflower);

            System.out.println("Do you want to keep adding a new Flower?");
            System.out.println("Enter (Yes or y) to keep adding, Others to out");
            String choice = MyUtils.getString("Do not empty!!!!");
            if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                continue;
            } else {
                break;
            }
        }
    }

    public void findFlower() {
        System.out.println("-----find flower-----");
        System.out.println("which field that you want to find ?");
        System.out.println("Enter [1] if chose flower id || Enter [2] if chose flower name");
        String choice = MyUtils.getString("Not empty");
        String field = "";
        boolean OK = false;

        if (choice.equalsIgnoreCase("1")) {
            System.out.print("Enter ID of flower: ");
            field = MyUtils.getString("Not empty");
            // tạo iterator của set flower để duyệt
            Iterator<Flower> iterator = flowersList.iterator();
            while (iterator.hasNext()) {
                Flower flower = iterator.next();
                if (flower.getId().equalsIgnoreCase(field)) {
                    System.out.println(flower);
                    OK = true;
                    break;
                }
            }
            if (!OK) {
                System.out.println("The id doesn't exist!!!!");
            }
        } else if (choice.equalsIgnoreCase("2")) {
            System.out.print("Enter flower's name: ");
            field = MyUtils.getString("Not empty");
            Iterator<Flower> iterator = flowersList.iterator();
            while (iterator.hasNext()) {
                Flower flower = iterator.next();
                if (flower.getName().equalsIgnoreCase(field)) {
                    System.out.println(flower);
                    OK = true;
                }
            }
            if (!OK) {
                System.out.println("The name doesn't exist!!!!");
            }
        } else {
            System.out.println("Invalid field!!!!");
            return;
        }
    }

    public void updateFlower() {
        Scanner input = new Scanner(System.in);
        System.out.println("Update a Flower!!!!");
        System.out.println("------");
        try {
            System.out.print("Enter name of Flower: ");
            String name = MyUtils.getString("Not empty!!!!");
            boolean found = false;

            for (Flower flower : flowersList) {
                if (flower.getName().equals(name)) {
                    found = true;

                    //id
                    boolean isDuplicateId = false;
                    String newId;
                    do {
                        System.out.print("Enter ID (must be unique) (current value: " + flower.getId() + "): ");
                        newId = input.nextLine();

                        isDuplicateId = false;

                        if (!newId.isEmpty()) {
                            for (Flower flower1 : flowersList) {
                                if (flower1.getId().equals(newId)) {
                                    isDuplicateId = true;
                                    System.err.println("Duplicate ID!!!");
                                    break;
                                }
                            }
                        }
                    } while (isDuplicateId);

                    if (!newId.isEmpty()) {
                        flowersList.remove(flower); // Xóa flower cũ từ danh sách
                        flower.setId(newId); // Cập nhật ID mới cho flower
                        flowersList.add(flower); // Thêm flower đã cập nhật vào danh sách
                    }

                    // description
                    System.out.print("Enter description (must be 3-50 characters) (current value: " + flower.getDescription() + "): ");
                    String newDescription = "";
                    boolean isValidDescription = false;
                    do {
                        newDescription = input.nextLine();
                        if (newDescription.isEmpty()) {
                            isValidDescription = true;
                        } else {
                            if (newDescription.length() < 3 || newDescription.length() > 50) {
                                System.out.println("Wrong format (must be 3-50 characters)");
                            } else {
                                flower.setDescription(newDescription);
                                isValidDescription = true;
                            }
                        }
                    } while (!isValidDescription);

                    // date
                    System.out.print("Enter Date (must be dd/MM/YYYY) (current value: " + flower.getdate() + "): ");
                    String regex = "^\\d{2}/\\d{2}/\\d{4}$";
                    String date = "";
                    boolean isValidDate = false;
                    do {
                        date = input.nextLine();
                        if (date.isEmpty()) {
                            isValidDate = true;
                        } else {
                            if (!date.matches(regex)) {
                                System.out.println("Wrong format (must be dd/MM/YYYY)");
                            } else {
                                flower.setdate(date);
                                isValidDate = true;
                            }
                        }
                    } while (!isValidDate);

                    // price
                    System.out.print("Enter price (must be a positive number) (current value: " + flower.getUnitPrice() + "): ");
                    boolean isValidPrice = false;
                    double price = 0;
                    do {
                        try {
                            price = Double.parseDouble(input.nextLine());
                            if (price < 0) {
                                System.out.println("Price must be > 0");
                            } else {
                                flower.setUnitPrice(price);
                                isValidPrice = true;
                            }
                        } catch (NumberFormatException e) {
                            isValidPrice = true;
                        }
                    } while (!isValidPrice);

                    // category
                    System.out.print("Enter Category (current value: " + flower.getCategory() + "): ");
                    String category = input.nextLine();
                    if (!category.isEmpty()) {
                        flower.setCategory(category);
                    }

                    // name
                    System.out.print("Enter Name (current value: " + flower.getName() + "): ");
                    name = input.nextLine();
                    if (!name.isEmpty()) {
                        flower.setName(name);
                    }

                    System.out.println("Update Successfully");
                    break; // Kết thúc vòng lặp khi cập nhật hoàn thành
                }
            }

            if (!found) {
                System.out.println("Name doesn't exist!!!!");
            }
        } catch (Exception e) {
            System.out.println("Update Failed!!!!");
        }
    }

    public void deleteFlower() {
        if (!flowersList.isEmpty() || flowersList == null) {
            System.out.println("Delete flower!!!!");
            System.out.println("-----");
            System.out.print("Enter flower Id: ");
            String flowerID = MyUtils.getString("Not empty!!!!");
            Flower flower = findFlowerById(flowerID);
            if (flower != null) {
                if (flower.isIsInOrder() == false) {
                    flowersList.remove(flower);
                    System.out.println("Delete sucessfully!");
                } else {
                    System.out.println("Flower is ordered!!!!");
                    System.out.println("Delete failed!");
                }
            } else {
                System.out.println("The flower ID doesn't exist!!!!");
            }
        } else {
            System.out.println("There no flower to delete!!!!");
        }

    }

    public void addOrder() {
        //check xem co empty hay khong
        if (flowersList.isEmpty()) {
            System.out.println("Flower doesn't not exist to make an order");
            return;
        }
        // cho chay vong while de ask nguoi dung 
        while (true) {
            System.out.println("-----Add a Oder-----");
            //khai bao bien 
            Scanner input = new Scanner(System.in);
            int totalFlower = 0;
            double totalOrderCost = 0;
            ArrayList<OrderDetail> details = new ArrayList<>();
            boolean OK = false;
            String orderID = "";

            //nhap du lieu order header!!!
            do {
                //ID           
                System.out.print("Enter order ID (must be unique): ");
                orderID = MyUtils.getString("Not empty!!!!");
                OK = true;
                Iterator<Order> oderIte = ordersList.iterator();
                for (Order order : ordersList) {
                    if (order.getOrderId().equals(orderID)) {
                        System.err.println("Duplicate ID!!!!");
                        OK = false;
                    }
                }
                if (OK == true) {
                    break;
                } else {
                    continue;
                }

            } while (!OK);

            //date
            String date = "";
            System.out.print("Enter Date(dd/MM/YYYY): ");
            date = MyUtils.getDate("Do not empty!!!!", "Wrong format (dd/MM/yyyy)!!!!");

            //customer’s name
            String customerName = "";
            System.out.print("Enter customer name:");
            customerName = MyUtils.getString("Not empty!!!!");

            // nhap du lieu order detail
            while (true) {
                //orderdetail id
                String orderDetailID = "";
                OK = false;
                do {
                    //ID           
                    System.out.print("Enter ID of order detail: ");
                    orderDetailID = MyUtils.getString("Not empty!!!!");
                    OK = true;

                    for (int i = 0; i < details.size(); i++) {
                        if (details.get(i).getOrderDetailId().equalsIgnoreCase(orderDetailID)) {
                            System.err.println("Duplicate ID!!!!");
                            OK = false;
                            break;
                        }
                    }
                    if (OK == false) {
                        continue;
                    } else {
                        break;
                    }
                } while (!OK);
                // FLOWER ID
                String flowerID = "";
                boolean isFlowerIDValid = false;

                // Prompt for flower ID (must exist in the flowersList)
                do {
                    System.out.print("Enter ID of flower: ");
                    flowerID = MyUtils.getString("Not empty!");

                    // Check if flower ID exists in the flowersList
                    Flower flower = findFlowerById(flowerID);
                    if (flower == null) {
                        System.out.println("Flower is not in the list!");
                        isFlowerIDValid = false;
                    } else {
                        flower.setIsInOrder(true);
                        isFlowerIDValid = true;
                    }
                } while (!isFlowerIDValid);
                //the quantity
                System.out.print("Enter quantity: ");
                int quantity = MyUtils.getNumber("Must be >0", "Not empty!!!!");

                //flower cost
                double flowerCost = quantity * findFlowerById(flowerID).getUnitPrice();
                // tao orderdetail va add
                OrderDetail detail = new OrderDetail(orderDetailID, flowerID, quantity, flowerCost);
                details.add(detail);
                // tinh toan so luong va tong tien
                totalFlower = totalFlower + detail.getQuantity();
                totalOrderCost = totalOrderCost + detail.getFlowerCost();

                // hoi nguoi dung co muon tao tiep khong
                System.out.println("Would you like to make a new Order Details?");
                System.out.println("Enter yes or y to make a new order else to exit");
                String choice = MyUtils.getString("Not empty!!!!");
                if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                    continue;
                } else {
                    break;
                }
            }
            //tao order va them order
            Order newOder = new Order(orderID, date, customerName, details);
            ordersList.add(newOder);
            System.out.println("Total Flower Count: " + totalFlower);
            System.out.println("Total Order Cost: " + totalOrderCost);
            // hoi co muon tao order moi khong
            System.out.println("Would you like to make a new Order");
            System.out.println("Enter yes or y to make a new Order else to exit");
            String choice = MyUtils.getString("Not empty");
            if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                continue;
            } else {
                break;
            }
        }

    }

    public void displayOrder() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Enter start date (must be dd/MM/yyyy):");
        String startDate = MyUtils.getDate("Not empty!", "Wrong format (must be dd/MM/yyyy)!");
        System.out.println("Enter end date (must be dd/MM/yyyy):");
        String endDate = MyUtils.getDate("Not empty!", "Wrong format (must be dd/MM/yyyy)!");
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);

        System.out.println("-----ORDER LIST-----");
        System.out.printf("%-5s%-13s%-17s%-13s%-16s%-2s\n", "No.", "Order ID", "Order Date", "Customer", "Flower Count", "Order Total");
        int count = 0;
        for (Order order : ordersList) {
            count++;
            Date orderDate = sdf.parse(order.getOrderDate());
            if (orderDate.compareTo(start) >= 0 && orderDate.compareTo(end) <= 0) {
                int flowerCount = 0;
                double orderTotal = 0.0;
                for (OrderDetail detail : order.getDetails()) {
                    flowerCount += detail.getQuantity();
                    orderTotal += detail.getFlowerCost();
                }
                System.out.printf("%-5s%-13s%-17s%-13s%-16s%-2s\n", count, order.getOrderId(), order.getOrderDate(), order.getOrderCustomerName(), flowerCount, orderTotal);
            }
        }
    }

    public void sortOrder() {
        System.out.println("Enter the field to sort by ");
        System.out.println("Order id (1)- Order date(2)- Custormer name (3)- Order total(4)");
        String field = MyUtils.getString("Not empty!!!!");
        System.out.println("Enter the sort order");
        System.out.println("asc(1) - desc(2)");
        String sortOrder = MyUtils.getString("Not empty!!!!");

        Comparator<Order> comparator = null;
        if (field.equalsIgnoreCase("1")) {
            comparator = Comparator.comparing(Order::getOrderId);
        } else if (field.equalsIgnoreCase("2")) {
            comparator = Comparator.comparing(Order::getOrderDate);
        } else if (field.equalsIgnoreCase("3")) {
            comparator = Comparator.comparing(Order::getOrderCustomerName);
        } else if (field.equalsIgnoreCase("4")) {
            comparator = Comparator.comparingDouble(Order::getOrderTotal);
        } else {
            System.out.println("Invalid field!!!!");
        }

        if (comparator != null) {
            // Convert HashMap to ArrayList
            ArrayList<Order> orderArrayList = new ArrayList<>(ordersList);

            if (sortOrder.equalsIgnoreCase("1")) {
                Collections.sort(orderArrayList, comparator);
            } else if (sortOrder.equalsIgnoreCase("2")) {
                Collections.sort(orderArrayList, comparator.reversed());
            } else {
                System.out.println("Invalid method Sort!!!!");
            }

            // Output the sorted arrayList
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
    }

    public void saveData() {
        try {
            SaveFile.Flower(flowersList);
            SaveFile.Order(ordersList);
        } catch (IOException e) {
            System.out.println("Error occurred while saving data: " + e.getMessage());
        }
        System.out.println("Data saved successfully.");
    }

    public void loadData() {
        try {
            flowersList = LoadFile.Flower(flowersList);
            ordersList = LoadFile.Order(ordersList);
        } catch (IOException | ParseException e) {
            System.out.println("Error occurred while loading data: " + e.getMessage());
        }
        System.out.println("Data Loaded successfully.");
    }

    public boolean quitProgram() {
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to save data before exiting the program?");
        System.out.println("(yes/y to save ) others to exit without save");
        String choice = input.nextLine();
        if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }

    }
//
    // ham phu dung de xu ly delete and find

    public void displayAll() {
        System.out.println("Flower list");
        Iterator<Flower> iter = flowersList.iterator();
        while (iter.hasNext()) {
            Flower flower = iter.next();
            System.out.println(flower);
        }
        System.out.println("Order list");
        Iterator<Order> iter2 = ordersList.iterator();
        while (iter2.hasNext()) {
            Order order = iter2.next();
            System.out.println(order);
        }
//        System.out.println("Order detail");
//        for (Order order : ordersList) {
//            for (OrderDetail detail : order.getDetails()) {
//                System.out.println(detail);
//            }
//        }
    }

    public Flower findFlowerById(String id) {
        Iterator<Flower> iter = flowersList.iterator();
        while (iter.hasNext()) {
            Flower flower = iter.next();

            if (flower.getId().equals(id)) {
                return flower;
            }
        }

        return null;
    }

    public Flower findFlowerByName(String name) {
        Iterator<Flower> iter = flowersList.iterator();
        while (iter.hasNext()) {
            Flower flower = iter.next();

            if (flower.getName().equals(name)) {
                return flower;
            }
        }

        return null;
    }

    public void getFlowerByDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Enter x (dd/MM/yyyy)");
        String x = MyUtils.getDate("not empty", "invalid");
        Date day = sdf.parse(x);

        for (Flower flower : flowersList) {
            Date date = sdf.parse(flower.getdate());
            if (date.compareTo(day) == 0) {
                System.out.println(flower);
            }
        }

    }
}
