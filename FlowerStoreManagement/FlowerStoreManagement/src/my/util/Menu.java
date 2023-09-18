/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.util;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author laptoptk
 */
public class Menu extends ArrayList<String>{
    static Scanner sc = new Scanner(System.in);
    boolean notUpdating = false;
    public void display(){
        System.out.println(this.toString());
    }
    public int getChoice(){
        int choice;
        for (int i = 0; i < this.size(); i++) {
            System.out.println("\n (" + (i+1) + ") - " + this.get(i));
        }
        choice = Input.isInt("\nChoose number(1 -> " + (this.size()) +") : ", notUpdating);
        return choice;
    }
}
