/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytg.utils;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu extends ArrayList<String> {

    public Menu() {
        super();
    }

    public int getUserChoice() {
        Scanner input = new Scanner(System.in);
        int choice = 1;
        for (int i = 0; i < this.size(); i++) {
            System.out.println(get(i));
        }

        do {
            System.out.println("-----------------------------");
            System.out.println("Enter from 1 to 5");
            try {

                choice = Integer.parseInt(input.nextLine());
                if (choice < 1 || choice > 6) {
                    System.out.println("Please enter from 1 to 5 !!!!");
                }
            } catch (Exception e) {
                System.out.println("Wrong format!!!");
            }
        } while (choice < 1 || choice > 6);
        return choice;
    }

}
