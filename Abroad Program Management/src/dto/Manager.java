/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;
import huytg.utils.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class Manager {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Scanner input = new Scanner(System.in);
    private ArrayList<Program> programList;
    private ArrayList<Student> studentList;

    public Manager() {
        programList = new ArrayList<Program>();
        studentList = new ArrayList<Student>();
    }

    public void displayProgram() {
        System.out.println("---DISPLAY PROGRAM LIST---");
        for (Program program : programList) {
            System.out.println(program);
        }
    }

    public void findStudentByEmail() {
        System.out.println("Enter email");
        String email = StudentUtils.getString();
        for (Student student : studentList) {
            if (student.getEmail().contains(email)) {
                System.out.println(student);
            }
        }

    }

    public void addProgram() {
        System.out.println("---ADD A PROGRAM---");
        System.out.print("Enter ID: ");
        String id = ProgramUtils.getID(programList);
        System.out.print("Enter Name: ");
        String name = ProgramUtils.getString();
        System.out.print("Enter time(Only accept as: January, March, May, July, September, November): ");
        String time = ProgramUtils.getTime();
        System.out.print("Enter From Registration Date: ");
        Date fromDate = ProgramUtils.getDate();
        System.out.print("End Registration Date: ");
        Date endDate = ProgramUtils.getDate();
        System.out.print("Enter days (30-40): ");
        int days = ProgramUtils.getDays();
        System.out.print("Enter location: ");
        String location = ProgramUtils.getString();
        System.out.print("Enter cost: ");
        double cost = ProgramUtils.getCost();
        System.out.print("Enter content (must be end with .doc or .pdf): ");
        String content = ProgramUtils.getContent();

        Program newProgram = new Program(id, name, time, fromDate, endDate,
                days, location, cost, content);
        programList.add(newProgram);

    }

    public void editProgramByID() {
        System.out.println("---EDIT PROGRAM----");

        System.out.print("Enter ID Of The Program That You Want To Update: ");
        String updateId = ProgramUtils.getString();
        boolean programFound = false;

        for (Program program : programList) {
            if (program.getId().equals(updateId)) {
                programFound = true;
                boolean OK = false;
                //  NAME               
                System.out.print("Enter Name (current value: " + program.getName() + "): ");
                String newName = input.nextLine();
                if (!newName.isEmpty()) {
                    program.setName(newName);
                } else {
                    program.setName(program.getName());
                }

                // TIME
                System.out.println("Enter Time (current value: " + program.getTime() + ") ");
                System.out.print("Only accept as: January, March, May, July, September, November: ");
                String newTime = "";
                do {
                    newTime = input.nextLine();
                    if (newTime.isEmpty()) {
                        program.setTime(program.getTime());
                        OK = true;
                    } else {
                        if (newTime.equalsIgnoreCase("January") || newTime.equalsIgnoreCase("March")
                                || newTime.equalsIgnoreCase("May") || newTime.equalsIgnoreCase("July")
                                || newTime.equalsIgnoreCase("September") || newTime.equalsIgnoreCase("November")) {
                            program.setTime(newTime);
                            OK = true;
                        } else {
                            System.out.println("Invalid time!!!!");
                            OK = false;
                        }
                    }
                } while (!OK);
                OK = false;
                // FROM REGISTRATION DATE
                System.out.print("Enter From Registration Date (current value: " + sdf.format(program.getFromDate()) + "): ");
                String newFromDate = "";
                Date fromDate = null;
                do {
                    try {
                        newFromDate = input.nextLine();
                        if (newFromDate.isEmpty()) {
                            program.setFromDate(program.getFromDate());
                            OK = true;
                        } else {
                            fromDate = sdf.parse(newFromDate);
                            program.setFromDate(fromDate);
                            OK = true;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid Format (must be dd/MM/yyyy)");
                        OK = false;
                    }

                } while (!OK);
                OK = false;
                // END REGISTRATION DATE
                System.out.print("Enter End Registration Date (current value: " + sdf.format(program.getEndDate()) + "): ");
                String newEndDate = "";
                Date endDate = null;
                do {
                    try {
                        newEndDate = input.nextLine();
                        if (newEndDate.isEmpty()) {
                            program.setEndDate(program.getEndDate());
                            OK = true;
                        } else {
                            endDate = sdf.parse(newEndDate);
                            program.setFromDate(endDate);
                            OK = true;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid Format (must be dd/MM/yyyy)");
                        OK = false;
                    }
                } while (!OK);
                OK = false;
                // DAYS
                System.out.print("Enter Days (current value: " + program.getDays() + "): ");
                int newDays = 0;
                do {
                    try {
                        newDays = Integer.parseInt(input.nextLine());
                        if (newDays < 30 || newDays > 40) {
                            System.out.println("From 30 to 40 days");
                            OK = false;
                        } else {
                            program.setDays(newDays);
                            OK = true;
                        }
                    } catch (Exception e) {
                        program.setDays(program.getDays());
                        OK = true;
                    }
                } while (!OK);
                OK = false;
                // LOCATION
                System.out.print("Enter location (current value: " + program.getLocation() + "): ");
                String newLocation = input.nextLine();
                if (!newLocation.isEmpty()) {
                    program.setLocation(newLocation);
                } else {
                    program.setLocation(program.getLocation());
                }
                // COST
                System.out.print("Enter Cost (current value: " + program.getCost() + "): ");

                try {
                    double newCost = Double.parseDouble(input.nextLine());
                    program.setCost(newCost);
                } catch (Exception e) {
                    program.setCost(program.getCost());
                }
                // CONTENT
                System.out.print("Enter Content (current value: " + program.getContent() + "): ");
                String newContent = "";
                do {
                    newContent = input.nextLine();
                    if (newContent.isEmpty()) {
                        program.setContent(program.getContent());
                        OK = true;
                    } else {
                        if (!(newContent.endsWith(".doc") || newContent.endsWith(".pdf"))) {
                            System.out.println("Invalid format (must end with .doc or .pdf)");
                            OK = false;
                        } else {
                            program.setContent(newContent);
                            OK = true;
                        }
                    }
                } while (!OK);
                OK = false;
                System.out.println("Update successfully");
                break; // Exit the loop once the program is found and updated
            }
        }

        if (!programFound) {
            System.out.println("ID Doesn't Exist!!!!");
        }

    }

    public void findProgram() {
        System.out.println("---FIND PROGRAM BY ID---");
        System.out.println("Enter ID Of the Program That You Want To Find");
        String findID = ProgramUtils.getString();
        Program programFound = ProgramUtils.findProgramByID(programList, findID);
        if (programFound != null) {
            System.out.println(programFound);
        } else {
            System.out.println("ID Doesn't Exist!!!!");
        }
    }
    //
    //
    //
    //

    public void displayStudent() {
        System.out.println("---STUDENT LIST---");
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    public void addStudent() {
        System.out.println("---ADD A STUDENT---");
        System.out.print("Enter ID: ");
        String id = StudentUtils.getID(studentList);
        System.out.print("Enter Name: ");
        String name = StudentUtils.getString();
        System.out.print("Enter Major (Only accept as: SE,SB,GD,MC): ");
        String major = StudentUtils.getMajor();
        System.out.print("Enter Email (Must end with @fpt.edu.vn): ");
        String email = StudentUtils.getEmail();
        System.out.print("Enter Phone (Must contain 10 characters): ");
        String phone = StudentUtils.getPhone();
        System.out.print("Enter Passport: ");
        String passport = StudentUtils.getString();
        System.out.print("Enter Address: ");
        String address = StudentUtils.getString();
        Student newStudent = new Student(id, name, major, email, phone,
                passport, address);
        studentList.add(newStudent);
    }

    public void editStudentByID() {
        System.out.println("---EDIT STUDENT----");

        System.out.print("Enter ID Of Student That You Want To Update: ");
        String updateId = StudentUtils.getString();
        boolean studentFound = false;
        for (Student student : studentList) {
            if (student.getId().equals(updateId)) {
                studentFound = true;
                boolean OK = false;
                //NAME
                System.out.print("Enter Name (current value: " + student.getName() + "): ");
                String newName = input.nextLine();
                if (newName.isEmpty()) {
                    student.setName(student.getName());
                } else {
                    student.setName(newName);
                }
                //MAJOR
                System.out.print("Enter Major (current value: " + student.getMajor() + "): ");
                String newMajor = "";
                do {
                    newMajor = input.nextLine();
                    if (newMajor.isEmpty()) {
                        student.setMajor(student.getMajor());
                        OK = true;
                    } else {
                        if (newMajor.equals("SE") || newMajor.equals("SB")
                                || newMajor.equals("GD") || newMajor.equals("MC")) {
                            student.setMajor(newMajor);
                            OK = true;

                        } else {
                            System.out.println("Invalid Major!!!!");
                            OK = false;
                        }
                    }
                } while (!OK);
                OK = false;

                //EMAIL
                System.out.print("Enter Email (current value: " + student.getEmail() + "): ");
                String newEmail = "";
                do {
                    newEmail = input.nextLine();
                    if (newEmail.isEmpty()) {
                        student.setEmail(student.getEmail());
                        OK = true;
                    } else {
                        if (newEmail.endsWith("@fpt.edu.vn")) {
                            student.setEmail(newEmail);
                            OK = true;
                        } else {
                            System.out.println("Invalid (must be end with @fpt.edu.vn");
                            OK = false;
                        }
                    }
                } while (!OK);
                OK = false;
                //PHONE
                System.out.print("Enter Phone (current value: " + student.getPhone() + "): ");
                String newPhone = "";
                do {
                    newPhone = input.nextLine();
                    if (newPhone.isEmpty()) {
                        student.setPhone(student.getPhone());
                        OK = true;
                    } else {
                        if (newPhone.length() < 10 || newPhone.length() > 10) {
                            System.out.println("Invalid phone number!!!!");
                            OK = false;
                        } else {
                            student.setPhone(newPhone);
                            OK = true;
                        }
                    }
                } while (!OK);
                //PASSPORT
                System.out.print("Enter Passport (current value: " + student.getPassport() + "): ");
                String newPassPort = input.nextLine();
                if (newPassPort.isEmpty()) {
                    student.setPassport(student.getPassport());
                } else {
                    student.setPassport(newPassPort);
                }
                //ADDRESS
                System.out.print("Enter Address (current value: " + student.getAddress() + "): ");
                String newAddress = input.nextLine();
                if (newAddress.isEmpty()) {
                    student.setAddress(student.getAddress());
                } else {
                    student.setAddress(newAddress);
                }
            }
        }
        if (!studentFound) {
            System.out.println("ID Doesn't Exist!!!!");
        }
    }

    //
    //
    //
    //
    public void registration() {
        // khai bao bien
        String programID;
        String studentID;
        Date registrationDate;
        String parentEmail;
        String parentPhone;
        //neu khong co 1 trong 2 thi khong dang ky duoc
        if (programList.isEmpty() || studentList.isEmpty()) {
            System.out.println("Must have at least 1 program and 1 student to register!");
        } else {
            System.out.println("Enter Program ID: ");
            programID = ProgramUtils.getString();
            System.out.println("Enter Student ID: ");
            studentID = StudentUtils.getString();
            // neu co thi bat nhap vao neu neu sai thi thoat ra
            Program program = ProgramUtils.findProgramByID(programList, programID);
            Student student = StudentUtils.findStudentByID(studentList, studentID);

            if (program == null) {
                System.out.println("The program ID doesn't exist!");
            } else if (student == null) {
                System.out.println("The student ID doesn't exist!");
            } else {
                //neu nhap dung thi bat dau tao form registation
                //nhap vao 3 gia tri cua parent
                System.out.println("Enter Registration date");
                System.out.println("Registration date must be between begin and end of the program’s registration date");
                registrationDate = RegistrationUtils.getRegistrationDate(programList);
                System.out.println("Enter parent's email");
                parentEmail = RegistrationUtils.getParentEmail();
                System.out.println("Enter parent's phone");
                parentPhone = RegistrationUtils.getParentPhone();
                // tao object trong class String builder sau noi chu lai tao form
                StringBuilder registrationForm = new StringBuilder();
                //thong tin cua sinh vien
                registrationForm.append("Aboard Program Registration Form\n");
                registrationForm.append("Information Student:\n");
                registrationForm.append("Student id: ").append(student.getId()).append(" Student name: ").append(student.getName()).append("\n");
                registrationForm.append("Major: ").append(student.getMajor()).append(" Email: ").append(student.getEmail()).append(" Phone: ").append(student.getPhone()).append(" Passport: ").append(student.getPassport()).append("\n");
                registrationForm.append("Address: ").append(student.getAddress()).append(" Email of the parents: ").append(parentEmail).append(" Phone of the parents: ").append(parentPhone).append("\n");
                //thong tin cua chuong trinh
                registrationForm.append("Information of the aboard program:\n");
                registrationForm.append("Program’s id: ").append(program.getId()).append(" Program’s name: ").append(program.getName()).append("\n");
                registrationForm.append("Time: ").append(program.getTime()).append(" Days: ").append(program.getDays()).append(" Location: ").append(program.getLocation()).append(" Cost: ").append(program.getCost()).append("\n");
                //thong tin cua registration 
                registrationForm.append("Information of the registration:\n");
                registrationForm.append("Registration date: ").append(sdf.format(registrationDate)).append("\n");

                // tao folder 
                File directory = new File("RegistrationForm");
                if (!directory.exists()) {
                    boolean created = directory.mkdir();
                    if (!created) {
                        System.out.println("Failed to create the RegistrationForm directory.");
                        return;
                    }
                }
                // tao file name
                // tao duong dan~
                String fileName = student.getId() + "_" + program.getId() + ".doc";
                String filePath = "RegistrationForm/" + fileName;
                //write vao file through pathname
                try {
                    FileWriter fileWriter = new FileWriter(filePath);
                    fileWriter.write(registrationForm.toString());
                    fileWriter.close();
                    System.out.println("Registration form saved successfully!");
                } catch (IOException e) {
                    System.out.println("Failed to save the registration form.");
                    e.printStackTrace();
                }

            }
        }
    }
    //
    //
    //
    //
//    public void printRegistrationByStudentId() {
//        System.out.print("Enter Student ID: ");
//        String studentId = RegistrationUtils.getString();
//        String[] files = RegistrationUtils.getFileNamesByStudentId(studentId);
//        if (files.length == 0) {
//            System.out.println("No files found for student ID: " + studentId);
//        } else {
//            for (String fileName : files) {
//                if (fileName.startsWith(studentId + "_")) {
//                    String filePath = "RegistrationForm/" + fileName;
//                    System.out.println("File content for " + fileName + ":");
//                    RegistrationUtils.printFileContent(filePath);
//                }
//            }
//        }
//    }

    //
//    public void printStudentsWithMultiplePrograms() {
//        File folder = new File("RegistrationForm");
//        File[] files = folder.listFiles();
//        Map<String, Integer> studentProgramCount = new HashMap<>();
//
//        if (files != null) {
//            for (File file : files) {
//                if (file.isFile()) {
//                    String fileName = file.getName();
//                    if (fileName.matches(".*\\.txt$")) {
//                        String[] parts = fileName.split("_");
//                        if (parts.length >= 2) {
//                            String studentId = parts[0];
//                            studentProgramCount.put(studentId, studentProgramCount.getOrDefault(studentId, 0) + 1);
//                        }
//                    }
//                }
//            }
//
//            for (Map.Entry<String, Integer> entry : studentProgramCount.entrySet()) {
//                String studentId = entry.getKey();
//                int programCount = entry.getValue();
//                if (programCount >= 2) {
//                    String filePath = RegistrationUtils.getFilePathByStudentId(studentId);
//                    if (filePath != null) {
//                        System.out.println("Student ID: " + studentId);
//                        System.out.println("Program Count: " + programCount);
//                        System.out.println("Registration Form Content:");
//                        printFileContent(filePath);
//                        System.out.println();
//                    }
//                }
//            }
//        }
//    }
//    public void countStudentsByProgramId() {
//        System.out.print("Enter ID Of The Program: ");
//        String programId = ProgramUtils.getString();
//        File folder = new File("RegistrationForm");
//        File[] files = folder.listFiles();
//        int count = 0;
//
//        if (files != null) {
//            for (File file : files) {
//                if (file.isFile()) {
//                    String fileName = file.getName();
//                    if (fileName.matches(".*\\.(doc|txt)$")) {
//                        String[] parts = fileName.split("_");
//                        if (parts.length > 1 && parts[1].equals(programId + ".doc") || parts[1].equals(programId + ".txt")) {
//                            count++;
//                        }
//                    }
//                }
//            }
//        }
//
//        System.out.println("Number of students registered for Program " + count);
//    }
}
