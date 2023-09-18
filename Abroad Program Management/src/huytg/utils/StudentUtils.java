/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytg.utils;

import dto.Program;
import dto.Student;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class StudentUtils {

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

    public static String getID(ArrayList<Student> studentList) {
        String result = "";
        boolean OK = false;
        do {
            result = ProgramUtils.getString();
            OK = true;
            for (Student student : studentList) {
                if (student.getId().equals(result)) {
                    System.out.println("Duplicate ID!!!!");
                    OK = false;
                }
            }
        } while (!OK);
        return result;
    }

    public static String getMajor() {
        String result = "";
        boolean OK = false;

        do {
            result = StudentUtils.getString();
            if (result.equals("SE") || result.equals("SB")
                    || result.equals("GD") || result.equals("MC")) {
                OK = true;
            } else {
                System.out.println("Invalid Major!!!!");
                OK = false;
            }
        } while (!OK);
        return result;
    }

    public static String getEmail() {
        String result = "";
        boolean OK = false;
        do {
            result = StudentUtils.getString();
            if (!result.endsWith("@fpt.edu.vn")) {
                System.out.println("Invalid (must be end with @fpt.edu.vn");
                OK = false;
            } else {
                OK = true;
            }
        } while (!OK);
        return result;
    }

    public static String getPhone() {
        String result = "";
        boolean OK = false;
        do {
            result = StudentUtils.getString();
            if (result.length() < 10 || result.length() > 10) {
                System.out.println("Invalid phone number!!!!");
                OK = false;
            } else {
                OK = true;
            }
        } while (!OK);
        return result;
    }

    public static Student findStudentByID(ArrayList<Student> studentList, String id) {
        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

}
