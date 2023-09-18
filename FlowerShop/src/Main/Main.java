/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import FlowerManagement.*;
import java.text.ParseException;
import java.util.Scanner;
import Tool.SubMenu;

/**
 *
 * @author acer
 */
public class Main {

    public static void main(String[] args) throws ParseException {

        Scanner input = new Scanner(System.in);
        Manager obj = new Manager();
        SubMenu menu = new SubMenu();
//
        menu.add("-----Flower management-----");
        menu.add("1.Add a new flower");
        menu.add("2.Find a flower");
        menu.add("3.Update a flower");
        menu.add("4.Delete a flower");
        System.out.println("");
        menu.add("-----Orders management-----");
        menu.add("5.Add a new order");
        menu.add("6.Display Orders by Start Date and End Date");
        menu.add("7.Sort Order list");
        menu.add("8.Save data");
        menu.add("9.Load data");
        menu.add("10.Exiting the program");
        menu.add("11. Display all");
        menu.add("12. Testing");
//        menu.add("11.DeleteNurse by Salary");

        int choice = 0;

        do {
            choice = menu.getUserChoice();
            switch (choice) {
                case 1:
                    obj.addFlower();
                    System.out.println("");

                    break;
                case 2:
                    obj.findFlower();
                    System.out.println("");
                    break;
                case 3:
                    obj.updateFlower();
                    System.out.println("");
                    break;
                case 4:
                    obj.deleteFlower();
                    System.out.println("");
                    break;
                case 5:
                    obj.addOrder();
                    System.out.println("");
                    break;
                case 6:
                    obj.displayOrder();
                    System.out.println("");
                    break;
                case 7:
                    obj.sortOrder();
                    System.out.println("");
                    break;
                case 8:
                    obj.saveData();
                    System.out.println("");
                    break;
                case 9:
                    obj.loadData();
                    System.out.println("");
                    break;
                case 10:
                    if (obj.quitProgram() == true) {
                        obj.saveData();
                        System.out.println("Thanks for Using my program");
                        System.exit(0);
                    } else {
                        System.out.println("Thanks for Using my program");
                        System.exit(0);
                    }
                case 11:
                    obj.displayAll();
                    break;
                case 12:
                  obj.getFlowerByDate();
                    System.out.println("");
                    break;

            }
        } while (choice >= 1 && choice <= 12);
    }
}
