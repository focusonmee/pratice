/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Scanner;
import DTO.FlowerStore;
import my.util.Input;
import my.util.Menu;

/**
 *
 * @author laptoptk
 */
public class FlowerStoreManagement {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        FlowerStore f = new FlowerStore();
        Menu mnu = new Menu();
        mnu.add("Add a flower");
        mnu.add("Find a flower");
        mnu.add("Update a flower by name");
        mnu.add("Delte a flower by id");
        mnu.add("Display All");
        mnu.add("Add a Order");
        mnu.add("Display Order by date");
        mnu.add("Sort Order");
        mnu.add("Save data");
        mnu.add("Load data");
        mnu.add("Quit");
        
        int choice;
        do {
            choice = mnu.getChoice();
            switch(choice){
                case 1: 
                    f.addFlower();
                    break;
                case 2: 
                    f.findFLower();
                    break;
                case 3: 
                    f.updateFLower();
                    break;
                case 4: 
                    f.deleteFlower();
                    break;
                case 5: 
                    f.displayAll();
                    break;
                case 6: 
                    f.addOrder();
                    break;
                case 7: 
                    f.displayOrderByDate();
                    break;
                case 8: 
                    f.sortOrders();
                    break;
                case 9: 
                    f.saveData();
                    break;
                case 10: 
                    f.loadData();
                    break;
                case 11: 
                    f.quit();
                    break;
                default: System.out.println("Please enter number from 1 to 11 only"); break;
            }
        } while ((choice>0 && choice !=11));
    }
}
