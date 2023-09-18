/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

/**
 *
 * @author acer
 */
import java.util.ArrayList;
import java.util.Scanner;

public class SubMenu extends ArrayList<String> {

    public SubMenu() {
        super();
    }

    public int getUserChoice() {
        Scanner input = new Scanner(System.in);
        int choice = 1;
        for (int i = 0; i < this.size(); i++) {
            System.out.println(get(i));
        }

        do {
            System.out.println("-----------");
            System.out.println("Enter from 1 to 11");
            try {

                choice = Integer.parseInt(input.nextLine());
                if (choice < 1 || choice > 12) {
                    System.out.println("Please enter from 1 to 11 !!!!");
                }
            } catch (Exception e) {
                System.out.println("Wrong format!!!");
            }
        } while (choice < 1 || choice > 12);
        return choice;
    }

}
