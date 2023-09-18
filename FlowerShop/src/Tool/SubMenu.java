/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tool;

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
            System.out.println("Enter from 1 to 12");
            try {

                choice = Integer.parseInt(input.nextLine());
                if (choice < 1 || choice > 12) {
                    System.out.println("Please enter from 1 to 12 !!!!");
                }
            } catch (Exception e) {
                System.out.println("Wrong format!!!");
            }
        } while (choice < 1 || choice > 12);
        return choice;
    }

}
