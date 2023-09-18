/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class Utils {

    public static String getString(String msg) {
        Scanner input = new Scanner(System.in);
        String result = "";
        boolean OK = false;
        do {
            result = input.nextLine();
            if (result.isEmpty()) {
                System.out.println(msg);
                OK=true;
            } else {
                OK = false;
            }
        } while (OK==true);

        return result;

    }

    public static int getNumber(int min, String msg, String msg2) {
        Scanner input = new Scanner(System.in);
        int result = 0;
        boolean OK = false;
        try {
            do {
                result = input.nextInt();
                if (result < min) {
                    System.out.println(msg);
                    OK=true;
                } else {
                    OK = false;
                }
            } while (OK ==true);

        } catch (Exception e) {
            System.out.println(msg2);
        }
        return result;
    }
    
  

}
