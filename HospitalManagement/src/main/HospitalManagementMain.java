/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import core.HospitalManagement;
import tools.Utils;
import java.text.ParseException;
import java.util.Scanner;
import tools.SubMenu;

/**
 *
 * @author acer
 */
public class HospitalManagementMain {

    public static void main(String[] args) throws ParseException {

        Scanner input = new Scanner(System.in);
        HospitalManagement obj = new HospitalManagement();
        SubMenu menu = new SubMenu();
        // Add menu
//        1. Nurse’s management
//        1.1 Create a nurse
//        1.2 Find the nurse
//        1.3 Update the nurse
//        1.4 Delete the nurse
//        2. Patient’s management
//        2.1 Add a patient
//        2.2 Display patients
//        2.3 Sort the patients list
//        2.4 Save data
//        2.5 Load data
//        Others – Quit.
        menu.add("-----Nurse’s management-----");
        menu.add("1.Add a new nurse");
        menu.add("2.Find a nurse by name");
        menu.add("3.Update a nurse by staffID");
        menu.add("4.Delete a nurse by staffID");
        System.out.println("");
        menu.add("-----Patient’s management-----");
        menu.add("5.Add a new patient");
        menu.add("6.Display a patient by AddmisionDate and DischargeDate");
        menu.add("7.Sort the patients list");
        menu.add("8.Save data");
        menu.add("9.Load data");
        menu.add("10.Exit the programming");
        menu.add("11.Display all");
        menu.add("12 testing");
        int choice = 0;

        do {
            choice = menu.getUserChoice();
            switch (choice) {
                case 1:
                    obj.addNurse();
                    System.out.println("");

                    break;
                case 2:
                    obj.findNurse();
                    System.out.println("");
                    break;
                case 3:
                    obj.updateNurse();
                    System.out.println("");
                    break;
                case 4:
                    obj.deleteNurse();
                    System.out.println("");
                    break;
                case 5:
                    obj.addPatient();
                    System.out.println("");
                    break;
                case 6:
                    obj.displayPatient();
                    System.out.println("");
                    break;
                case 7:
                    obj.sortPatientList();
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
                    if (obj.exitProgram() == true) {
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
                    obj.sort();
                    break;

            }
        } while (choice >= 1 && choice <= 12);
    }
}
