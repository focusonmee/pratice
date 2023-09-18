/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytg.utils;

import dto.Program;
import dto.Student;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class RegistrationUtils {

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

    public static Date getRegistrationDate(ArrayList<Program> programList) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date registrationDate = null;
        String regisDate = "";
        boolean OK = false;

        do {
            try {
                regisDate = RegistrationUtils.getString();
                registrationDate = sdf.parse(regisDate);

                boolean isWithinRange = false;
                for (Program program : programList) {
                    if (registrationDate.compareTo(program.getFromDate()) >= 0
                            && registrationDate.compareTo(program.getEndDate()) <= 0) {
                        isWithinRange = true;
                        break;
                    }
                }

                if (isWithinRange) {
                    OK = true;
                } else {
                    System.out.println("Registration date must be between the begin and end of the program's registration period.");
                    OK = false;
                }
            } catch (ParseException e) {
                System.out.println("Wrong format (dd/MM/yyyy)");
            }
        } while (!OK);

        return registrationDate;
    }

    public static String getParentEmail() {
        String result = "";
        boolean OK = false;
        do {
            result = RegistrationUtils.getString();
            if (!result.endsWith("@gmail.com")) {
                System.out.println("Invalid (must be end with @gmail.com");
                OK = false;
            } else {
                OK = true;
            }
        } while (!OK);
        return result;
    }

    public static String getParentPhone() {
        String result = "";
        boolean OK = false;
        do {
            result = RegistrationUtils.getString();
            if (result.length() < 10 || result.length() > 10) {
                System.out.println("Invalid phone number!!!!");
                OK = false;
            } else {
                OK = true;
            }
        } while (!OK);
        return result;
    }

    public static String[] getFileNamesByStudentId(String studentId) {
        File folder = new File("RegistrationForm");
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File folder, String fileName) {
                return fileName.startsWith(studentId + "_");
            }
        };
        return folder.list(filter);
    }

    public static void printFileContent(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Failed to read the file.");
        }
    }
//public static String getFilePathByStudentId(String studentId) {
//    File folder = new File("RegistrationForm");
//    File[] files = folder.listFiles();
//
//    if (files != null) {
//        for (File file : files) {
//            if (file.isFile()) {
//                String fileName = file.getName();
//                if (fileName.startsWith(studentId + "_")) {
//                    return file.getAbsolutePath();
//                }
//            }
//        }
//    }
//
//    return null; // Trả về null nếu không tìm thấy file
//}

}
