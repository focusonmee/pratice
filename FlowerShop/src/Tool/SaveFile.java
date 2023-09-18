/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tool;

import FlowerManagement.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashSet;

/**
 *
 * @author laptoptk
 */
public class SaveFile {

    public static void Flower(HashSet<Flower> list) throws IOException {
        if (list.isEmpty()) {
            System.out.println("Empty Flower List");
            return;
        }
        File f = new File("flowers.txt");
        FileWriter fw = new FileWriter(f); // write() write theo char
        PrintWriter pw = new PrintWriter(fw); // print() theo line
        for (Flower currentFLower : list) {
            pw.println(currentFLower);
        }
        pw.close();
        fw.close();
    }

    public static void Order(HashSet<Order> list) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (list.isEmpty()) {
            System.out.println("Empty Order List");
            return;
        }
        File f = new File("orders.txt");
        FileWriter fw = new FileWriter(f); // write() write theo char
        PrintWriter pw = new PrintWriter(fw); // print() theo line
        for (Order currentOrder : list) {
            pw.println("Order " + currentOrder.getOrderId() + ","
                    + df.format(currentOrder.getOrderDate()) + ","
                    + currentOrder.getOrderCustomerName() + " :");
            for (OrderDetail detail : currentOrder.getDetails()) {
                pw.println("[ " + detail + " ]");
            }
            pw.println("Total: " + currentOrder.getFlowerCount() + ","
                    + currentOrder.getOrderTotal() + " ;");
        }
        pw.close();
        fw.close();
    }
}
