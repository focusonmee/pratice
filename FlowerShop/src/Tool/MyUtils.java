/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tool;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author acer
 */
/**
 *
 * @author acer
 */
public class MyUtils {

    public static String getString(String msg) {
        Scanner input = new Scanner(System.in);
        String result = "";
        boolean OK = false;
        do {
            result = input.nextLine();
            if (result.isEmpty()) {
                System.err.println(msg);
            } else {
                OK = true;
            }
        } while (!OK);
        return result;
    }

    public static int getNumber(String msg1, String msg2) {
        Scanner input = new Scanner(System.in);
        int number = 0;
        boolean OK = false;
        do {
            try {
                OK = false;
                number = Integer.parseInt(input.nextLine());
                if (number < 0) {
                    System.err.println(msg1);
                } else {
                    OK = true;
                }
            } catch (Exception e) {
                System.err.println(msg2);
            }
        } while (!OK);
        return number;
    }

    public static String getDescription(String msg1, String msg2) {
        Scanner input = new Scanner(System.in);
        String result = "";
        boolean OK = false;
        do {
            result = input.nextLine();
            if (result.isEmpty()) {
                System.err.println(msg1);
            } else if (result.length() < 3 || result.length() > 50) {
                System.err.println(msg2);
            } else {
                OK = true;
            }
        } while (!OK);
        return result;
    }

    public static String getDate(String msg1, String msg2) {
        Scanner input = new Scanner(System.in);
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        String date = "";
        boolean OK = false;
        do {
            date = input.nextLine();
            if (date.isEmpty()) {
                System.err.println(msg1);
            } else if (!date.matches(regex)) {
                System.err.println(msg2);
            } else {
                OK = true;
            }
        } while (!OK);
        return date;
    }
}
