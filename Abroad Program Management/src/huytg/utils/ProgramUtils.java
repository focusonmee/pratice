/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytg.utils;

import dto.Manager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.*;
import dto.Program;

/**
 *
 * @author acer
 */
public class ProgramUtils {

    public static String getString() {
        Scanner input = new Scanner(System.in);
        String result = "";
        boolean OK = false;
        do {
            result = input.nextLine();
            if (result.isEmpty()) {
                System.err.println("Not empty");
            } else {
                OK = true;
            }
        } while (!OK);
        return result;
    }

    public static String getID(ArrayList<Program> programList) {
        String result = "";
        boolean OK = false;
        do {
            result = ProgramUtils.getString();
            OK = true;
            for (Program program : programList) {
                if (program.getId().equals(result)) {
                    System.out.println("Duplicate ID!!!!");
                    OK = false;
                }
            }
        } while (!OK);
        return result;
    }

    public static String getTime() {
        String result = "";
        boolean OK = false;

        do {
            result = ProgramUtils.getString();
            if (result.equalsIgnoreCase("January") || result.equalsIgnoreCase("March")
                    || result.equalsIgnoreCase("May") || result.equalsIgnoreCase("July")
                    || result.equalsIgnoreCase("September") || result.equalsIgnoreCase("November")) {
                OK = true;
            } else {
                System.out.println("Invalid time!!!!");
                OK = false;
            }
        } while (!OK);
        return result;
    }

    public static Date getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean OK = false;
        Date date = null;
        do {
            String inputDate = ProgramUtils.getString();
            try {
                date = sdf.parse(inputDate);
                OK = true;
            } catch (ParseException e) {
                System.out.println("Wrong format (dd/MM/yyyy)");
            }
        } while (!OK);
        return date;
    }

    public static int getDays() {
        Scanner input = new Scanner(System.in);
        int result = 0;
        boolean OK = false;
        do {
            result = Integer.parseInt(input.nextLine());
            try {
                if (result < 30 || result > 40) {
                    System.out.println("From 30 to 40 days");
                    OK = false;
                } else {
                    OK = true;
                }
            } catch (Exception e) {
                System.out.println("Not empty!!!!");
            }
        } while (!OK);
        return result;
    }

    public static String getContent() {
        boolean OK = false;
        String result = "";
        do {
            result = ProgramUtils.getString();
            if (!(result.endsWith(".doc") || result.endsWith(".pdf"))) {
                System.out.println("Invalid format (must end with .doc or .pdf)");
                OK = false;
            } else {
                OK = true;
            }
        } while (!OK);
        return result;
    }

    public static double getCost() {
        Scanner input = new Scanner(System.in);
        double result = 0.0;
        boolean OK = false;
        do {
            try {
                result = Double.parseDouble(input.nextLine());
                OK = true;
            } catch (Exception e) {
                System.out.println("Not empty!!!!");
                OK = false;
            }
        } while (!OK);
        return result;
    }

    public static Program findProgramByID(ArrayList<Program> programList, String id) {
        for (Program program : programList) {
            if (program.getId().equals(id)) {
                return program;
            }
        }
        return null;
    }
}
