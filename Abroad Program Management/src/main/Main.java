/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dto.Manager;
import huytg.utils.Menu;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Manager obj = new Manager();
        Menu menu = new Menu();
        menu.add("-----Aboard Program Registration System-----");
        menu.add("1. Manage aboard programs");
        menu.add("2. Manage students");
        menu.add("3. Register a program for students");
        menu.add("4. Report");
        menu.add("5. Exit program");
        menu.add("6. Find student by Email");

        int choice = 0;

        do {
            choice = menu.getUserChoice();
            switch (choice) {
                case 1:
                    int subChoice = 0;
                    boolean backToMainMenu = false;
                    do {
                        System.out.println("---MANAGE ABROAD PROGRAMS---");
                        System.out.println("1. Display All Abroad Programs");
                        System.out.println("2. Add A New Abroad Program");
                        System.out.println("3. Edit Information A Program By ID");
                        System.out.println("4. Search And Display A Program By ID");
                        System.out.println("5. Back to main menu");
                        try {
                            subChoice = Integer.parseInt(input.nextLine());
                            switch (subChoice) {
                                case 1:
                                    obj.displayProgram();
                                    break;
                                case 2:
                                    obj.addProgram();
                                    break;
                                case 3:
                                    obj.editProgramByID();
                                    break;
                                case 4:
                                    obj.findProgram();
                                    break;
                                case 5:
                                    backToMainMenu = true; // Đánh dấu để thoát khỏi submenu
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid format");
                        }
                    } while (!backToMainMenu);
                    break;

                case 2:
                    int subChoiceCase2 = 0;
                    boolean backToMainMenuCase2 = false;
                    do {
                        System.out.println("---MANAGE STUDENTS---");
                        System.out.println("1. Displays all students");
                        System.out.println("2. Add a new student");
                        System.out.println("3. Edit information a student by id");
                        System.out.println("4. Back to main menu");
                        try {
                            subChoiceCase2 = Integer.parseInt(input.nextLine());
                            switch (subChoiceCase2) {
                                case 1:
                                    obj.displayStudent();
                                    break;
                                case 2:
                                    obj.addStudent();
                                    break;
                                case 3:
                                    obj.editStudentByID();
                                    break;
                                case 4:
                                    backToMainMenuCase2 = true; // Đánh dấu để thoát khỏi submenu
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid format");
                        }
                    } while (!backToMainMenuCase2);
                    break;

                case 3:
                    System.out.println("---REGISTER A PROGRAM FOR STUDENTS---");
                    obj.registration();
                    break;
                case 4:
                    int subChoiceCase4 = 0;
                    boolean backToMainMenuCase4 = false;
                    do {
                        System.out.println("---REPORT---");
                        System.out.println("1. Print out the registration by student’s id");
                        System.out.println("2. Print out the students that registered more than 2 programs");
                        System.out.println("3. Count students that registered the program   ");
                        System.out.println("4. Back to main menu");
                        try {
                            subChoiceCase4 = Integer.parseInt(input.nextLine());
                            switch (subChoiceCase4) {
                                case 1:
//                               
                                    break;
                                case 2:
//                                    
                                    break;
                                case 3:

                                    break;
                                case 4:
                                    backToMainMenuCase4 = true; // Đánh dấu để thoát khỏi submenu
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid format");
                        }
                    } while (!backToMainMenuCase4);
                    break;
                case 5:
                    System.exit(0);
                    break;
                case 6:
                    obj.findStudentByEmail();
                    break;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//                    break;
            }
        } while (choice != 5);
    }
}
