/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.Iterator;
import java.util.HashMap;
import tools.Utils;
import core.Nurse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Collections;
import java.text.SimpleDateFormat;
import core.*;

/**
 *
 * @author acer
 */
public class HospitalManagement {

    //id, name, age, gender, address, phone.
    HashMap<String, Nurse> nurseList = new HashMap<>();
    HashMap<String, Patient> patientList = new HashMap<>();
    Iterator<Map.Entry<String, Nurse>> itera1;

    Iterator<Map.Entry<String, Patient>> itera2;

    // Create SimpleDateFormat object to parse to Date to compare Date
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public HashMap<String, Nurse> getNurseList() {
        return nurseList;
    }

    public void addNurse() {
        while (true) {
            boolean OK = false;
            System.out.println("Add a Nurse!!!!");
            System.out.println("---------------");
            //id
            System.out.print("Enter ID: ");
            String id = Utils.getString("Not empty!!!!");
            //name
            System.out.print("Enter Name: ");
            String name = Utils.getString("Not empty!!!!");
            //age
            System.out.print("Enter age: ");
            int age = Utils.getNumber(0, "Age can't be < 0", "Not empty!!!!");
            //gender
            System.out.print("Enter gender: ");
            String gender = Utils.getString("Not empty!!!!");
            //address
            System.out.print("Enter address: ");
            String address = Utils.getString("Not empty!!!!");
            //phone     
            String phone = "";
            do {
                System.out.print("Enter phone: ");
                phone = Utils.getString("Not empty!!!!");
                if (phone.length() < 10 || phone.length() > 10) {
                    System.out.print("Wrong format(must be 10 digits)");
                    System.out.println("");
                    OK = false;
                } else {
                    OK = true;
                }
            } while (OK == false);
            OK = false;
            //staffID
            String staffID = "";
            do {
                System.out.print("Enter staffID: ");
                staffID = Utils.getString("Not empty!!!!");
                OK = true;
                for (Map.Entry<String, Nurse> entry : nurseList.entrySet()) {
                    String key = entry.getKey();

                    if (staffID.equals(key)) {
                        System.out.println("Duplicated staffID");
                        OK = false;
                        break;
                    }
                }
            } while (OK == false);

            OK = false;
            //department
            String department = "";
            do {
                System.out.print("Enter department: ");
                department = Utils.getString("Not empty!!!!");
                if (department.length() < 3 || department.length() > 50) {
                    System.out.println("Wrong format (must be 3-50)");
                    OK = false;
                } else {
                    OK = true;
                }
            } while (OK == false);
            //shift
            System.out.print("Enter shift: ");
            int shift = Utils.getNumber(0, "Shift can't be < 0", "Not empty!!!!");
            //salary
            System.out.print("Enter salary: ");
            double salary = Utils.getNumber(0, "Salry can't be < 0", "Not empty!!!!");

            //add nurse to hashmap
            Nurse newnurse = new Nurse(staffID, department, shift, salary, id, name, age, gender, address, phone);
            nurseList.put(staffID, newnurse);
            System.out.println("Would you like to add a new Nurse ? (Enter 'Y') or back to Main menu (Enter else)");
            if (Utils.getString("Please enter your chocie!!!!").equalsIgnoreCase("y")) {
                continue;
            } else {
                break;
            }

        }
    }

    public void findNurse() {
        //bat nguoi dung nhap vao 
//        System.out.print("Enter name of nurse: ");
//        String nurseName = Utils.getString("Not empty!!!!").trim();
//        boolean OK = false;
//
//        //duyet qua danh sach y ta trong hashmap
//        for (Nurse nurse : nurseList.values()) {
//            if (nurse.getName().toLowerCase().contains(nurseName.toLowerCase())) {
//                System.out.println(nurse);
//                OK = true;
//                break;
//            }
//        }
//        if (OK == false) {
//            System.out.println("Name does not exist!!!!");
//        }
        System.out.println("Find a Nurse!!!!");
        System.out.println("------------");
        boolean OK = false;
        System.out.print("Enter name or part of name of nurse: ");
        String nurseName = Utils.getString("Not empty!!!!").trim();
        Iterator<Map.Entry<String, Nurse>> nurseIte = nurseList.entrySet().iterator();
        while (nurseIte.hasNext()) {
            Map.Entry<String, Nurse> next = nurseIte.next();
            if (next.getValue().getName().toLowerCase().contains(nurseName.toLowerCase())) {
                System.out.println(next);
                OK = true;
            }
        }
        if (!OK) {
            System.out.println("Invalid name or name does not exist!!!!");
        }
    }

    public Nurse PatientList(String id) {
        //duyet qua danh sach y ta trong hashmap
        Iterator<Map.Entry<String, Nurse>> iterator = nurseList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Nurse> next = iterator.next();
            if (next.getValue().getStaffID().equals(id)) {

            }
        }
        return null;
    }

    public void updateNurse() {
        Scanner input = new Scanner(System.in);
        System.out.println("Update a Nurse!!!!");
        System.out.println("------");
        System.out.print("Enter staffID of nurse: ");
        String staffID = Utils.getString("Not empty!!!!");
        boolean OK = false;

        try {
            for (Nurse nurse : nurseList.values()) {
                if (nurse.getStaffID().equals(staffID)) {
                    // id
                    System.out.print("Enter ID (current value: " + nurse.getId() + "): ");
                    String id = input.nextLine();
                    if (!id.isEmpty()) {
                        nurse.setId(id);
                    }

                    // name
                    System.out.print("Enter Name (current value: " + nurse.getName() + "): ");
                    String name = input.nextLine();
                    if (!name.isEmpty()) {
                        nurse.setName(name);
                    }

                    // age
                    System.out.print("Enter age (current value: " + nurse.getAge() + "): ");

                    int age = 0;
                    boolean check = false;
                    do {
                        try {
                            age = Integer.parseInt(input.nextLine());
                            if (age < 0) {
                                System.out.println("Age can't be < 0");
                            } else {
                                nurse.setAge(age);
                                check = true;
                            }

                        } catch (Exception e) {
                            nurse.setAge(nurse.getAge());
                            check = true;
                        }

                    } while (!check);

                    // gender
                    System.out.print("Enter gender (current value: " + nurse.getGender() + "): ");
                    String gender = input.nextLine();
                    if (!gender.isEmpty()) {
                        nurse.setGender(gender);
                    }

                    // address
                    System.out.print("Enter address (current value: " + nurse.getAddress() + "): ");
                    String address = input.nextLine();
                    if (!address.isEmpty()) {
                        nurse.setAddress(address);
                    }

                    // phone
                    System.out.print("Enter phone (current value: " + nurse.getPhone() + "): ");
                    check = false;
                    String phone = "";
                    do {
                        phone = input.nextLine();
                        if (phone.isEmpty()) {
                            nurse.setPhone(nurse.getPhone());
                            check = true;
                        } else {
                            if (phone.length() < 10 || phone.length() > 10) {
                                System.out.println("Informat phone!!!!");
                            } else {
                                nurse.setPhone(phone);
                                check = true;
                            }
                        }
                    } while (!check);

                    // staffID
                    System.out.print("Enter staffID (current value: " + nurse.getStaffID() + "): ");
                    String newStaffID;
                    boolean isDuplicate;

                    do {
                        newStaffID = input.nextLine();
                        isDuplicate = nurseList.containsKey(newStaffID);

                        if (isDuplicate) {
                            System.out.println("Duplicated staffID. Please enter a different staffID.");
                        }
                    } while (isDuplicate);

                    if (!newStaffID.isEmpty()) {
                        nurseList.remove(staffID);
                        nurseList.put(newStaffID, nurse);
                        nurse.setStaffID(newStaffID);
                    }

                    // department
                    System.out.print("Enter department (current value: " + nurse.getDepartment() + "): ");
                    String department = input.nextLine();
                    if (!department.isEmpty()) {
                        nurse.setDepartment(department);
                    }

                    // shift
                    System.out.print("Enter shift (current value: " + nurse.getShift() + "): ");
                    String shiftInput = input.nextLine();
                    int shift = (shiftInput.isEmpty()) ? nurse.getShift() : Utils.getNumber(0, "Shift can't be < 0", shiftInput);
                    nurse.setShift(shift);

                    // salary
                    System.out.print("Enter salary (current value: " + nurse.getSalary() + "): ");
                    check = false;
                    double salary;
                    do {
                        try {
                            salary = Double.parseDouble(input.nextLine());
                            if (salary < 0) {
                                System.out.println("salary can't be < 0");
                            } else {
                                nurse.setSalary(salary);
                                check = true;
                            }
                        } catch (Exception e) {
                            nurse.setSalary(nurse.getSalary());
                            check = true;
                        }

                    } while (!check);

                    System.out.println("Updating nurse successfully!!!!");
                    OK = true;
                    break;
                }

//                if (!OK) {
//                    System.out.println("StaffID does not exist!!!!");
//                }
            }
        } catch (Exception e) {
            System.out.println("Updating nurse failure!!!!");
        }

    }

    public void deleteNurse() {
        try {
            System.out.println("Delete a Nurse!!!!");
            System.out.println("------");
            System.out.print("Enter staffID: ");
            String staffID = Utils.getString("Not empty!!!!");
            boolean OK = false;

            Iterator<Nurse> iterator = nurseList.values().iterator();
            while (iterator.hasNext()) {
                Nurse nurse = iterator.next();
                if (nurse.getStaffID().equals(staffID)) {
                    OK = true;
                    System.out.println("DO YOU REALLY WANT TO DELETE THIS NURSE? (Enter (Yes/y) to accept, else to deny)");
                    String choice = Utils.getString("Enter your choice!!!!");
                    if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                        if (nurse.getAssignedPatients().isEmpty()) {
                            iterator.remove();
                            System.out.println("Nurse deleted successfully!!!!");
                        } else {
                            System.out.println("The nurse is assigned to patient(s) and cannot be deleted.");
                        }
                    }
                }
            }

            if (!OK) {
                System.out.println("The nurse does not exist!!!!");
            }
        } catch (Exception e) {
            System.out.println("Delete failure!!!!");
        }
    }

    public void addPatient() {
        // Kiểm tra xem có y tá nào còn khả năng chăm sóc thêm bệnh nhân không
        if (nurseList.isEmpty()) {
            System.out.println("There are no available nurses to assign a patient.");
            return;
        }
        int availableNursesCount = 0;
        for (Nurse nurse : nurseList.values()) {
            if (nurse.canAssignPatient()) {
                availableNursesCount++;
            }
        }

        if (availableNursesCount < 2) {
            System.out.println("There must be at least 2 available nurses to add a patient!");
            return;
        }

        System.out.println("Add a Patient!!!!");
        System.out.println("");
//        Nurse availableNurse = null;
//        for (Nurse nurse : nurseList.values()) {
//            if (nurse.canAssignPatient()) {
//                availableNurse = nurse;
//                break;
//            }
//        }
//
//        if (availableNurse == null) {
//            System.out.println("All nurses are currently assigned to their maximum number of patients.");
//            return;
//        }

        // Hiển thị danh sách y tá có sẵn
        System.out.println("Available Nurses:");
        for (Nurse nurse : nurseList.values()) {
            if (nurse.canAssignPatient()) {

                System.out.println("Staff ID: " + nurse.getStaffID() + ", Name: " + nurse.getName());
            }
        }

        //id
        boolean OK = false;
        String id = "";
        do {
            System.out.print("Enter ID: ");
            id = Utils.getString("Not empty!!!!");
            OK = true;
            for (Map.Entry<String, Nurse> entry : nurseList.entrySet()) {
                String key = entry.getKey();

                if (id.equals(key)) {
                    System.out.println("Duplicated ID");
                    OK = false;
                    break;
                }
            }
        } while (OK == false);

        OK = false;
        //name
        System.out.print("Enter Name: ");
        String name = Utils.getString("Not empty!!!!");
        //age
        System.out.print("Enter age: ");
        int age = Utils.getNumber(0, "Age can't be < 0", "Not empty!!!!");
        //gender
        System.out.print("Enter gender: ");
        String gender = Utils.getString("Not empty!!!!");
        //address
        System.out.print("Enter address: ");
        String address = Utils.getString("Not empty!!!!");
        //phone     
        String phone = "";
        do {
            System.out.print("Enter phone: ");
            phone = Utils.getString("Not empty!!!!");
            if (phone.length() < 10 || phone.length() > 10) {
                System.out.print("Wrong format(must be 10 digits)");
                System.out.println("");
                OK = false;
            } else {
                OK = true;
            }
        } while (OK == false);
        OK = false;

        // diagnosis
        System.out.print("Enter diagnosis: ");
        String diagnosis = Utils.getString("Not empty!!!!");

        // admissionDate, dischargeDate, nurseAssigned
        //admissionDate
        String admissionDate = "";
        do {
            System.out.print("Enter admissionDate: ");
            admissionDate = Utils.getString("Not empty");
            String regex = "^\\d{2}/\\d{2}/\\d{4}$";
            if (!(admissionDate.matches(regex))) {
                System.out.println("Wrong format!!!!");
                OK = false;
            } else {
                OK = true;
            }

        } while (OK == false);
        OK = false;
        //dischargeDate
        String dischargeDate = "";
        do {
            System.out.print("Enter dischargeDate: ");
            dischargeDate = Utils.getString("Not empty");
            String regex = "^\\d{2}/\\d{2}/\\d{4}$";
            if (!(dischargeDate.matches(regex))) {
                System.out.println("Wrong format!!!!");
                OK = false;
            } else {
                OK = true;
            }

        } while (OK == false);
        OK = false;
        // Nhận đầu vào từ người dùng
// Chọn y tá đầu tiên
        Nurse firstNurse = null;
        String firstNurseID = "";
        boolean isValidFirstNurseID;
        do {
            System.out.print("Enter Staff ID of the first Nurse: ");
            firstNurseID = Utils.getString("Not empty!!!!");
            // Kiểm tra xem ID y tá có tồn tại trong danh sách không
            firstNurse = nurseList.get(firstNurseID);
            isValidFirstNurseID = firstNurse != null && firstNurse.canAssignPatient();
            if (!isValidFirstNurseID) {
                System.out.println("Invalid Nurse ID! Please enter a valid Staff ID of an available Nurse.");
            }
        } while (firstNurseID.isEmpty() || !isValidFirstNurseID);

// Chọn y tá thứ hai
        Nurse secondNurse = null;
        String secondNurseID = "";
        boolean isValidSecondNurseID;
        do {
            System.out.print("Enter Staff ID of the second Nurse: ");
            secondNurseID = Utils.getString("Not empty!!!!");
// Kiểm tra xem ID y tá có tồn tại trong danh sách không và không trùng với y tá đầu tiên
            secondNurse = nurseList.get(secondNurseID);
            isValidSecondNurseID = secondNurse != null && secondNurse.canAssignPatient() && !secondNurseID.equals(firstNurseID);
            if (!isValidSecondNurseID) {
                System.out.println("Invalid Nurse ID! Please enter a valid Staff ID of an available Nurse (different from the first Nurse).");
            }
        } while (secondNurseID.isEmpty() || !isValidSecondNurseID);

// Tạo bệnh nhân mới và gán y tá
        Patient newPatient = new Patient(diagnosis, admissionDate, dischargeDate, firstNurse, secondNurse, id, name, age, gender, address, phone);
        firstNurse.assignPatient(newPatient);
        secondNurse.assignPatient(newPatient);

        // luu thong tin y ta cham soc benh nahn moi
        newPatient.addNurse(firstNurse);
        newPatient.addNurse(secondNurse);

        patientList.put(id, newPatient);
        System.out.println("Patient created successfully.");

    }

    public void saveData() {
        if (!nurseList.isEmpty() || !patientList.isEmpty()) {
            try {
                // save nurse data to nurses.dat file
                FileOutputStream fileOut = new FileOutputStream("nurses.txt");
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(nurseList);
                objectOut.close();
                fileOut.close();

                // save patient data to patients.dat file
                fileOut = new FileOutputStream("patients.txt");
                objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(patientList);
                objectOut.close();
                fileOut.close();

                System.out.println("Data saved successfully.");
            } catch (Exception e) {
                System.out.println("Error occurred while saving data: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("No data to save");
        }
    }

    public void loadData() {
        try {
            FileInputStream fis = new FileInputStream("nurses.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            nurseList = (HashMap<String, Nurse>) ois.readObject();
            ois.close();
            fis.close();

            Iterator<Map.Entry<String, Nurse>> set = nurseList.entrySet().iterator();
            while (set.hasNext()) {
                Map.Entry<String, Nurse> next = set.next();
                System.out.println(next);

            }

            FileInputStream fis2 = new FileInputStream("patients.txt");
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            patientList = (HashMap<String, Patient>) ois2.readObject();
            ois2.close();
            fis2.close();
            Iterator<Map.Entry<String, Patient>> set1 = patientList.entrySet().iterator();
            while (set1.hasNext()) {
                Map.Entry<String, Patient> next2 = set1.next();
                System.out.println(next2);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sortPatientList() {
// Người dùng nhập vào thông tin sắp xếp
        System.out.println("Enter the field to sort by (patient id or patient's name)");
        String field = Utils.getString("Not empty!!!!");
        System.out.println("Enter the sort order (asc or desc)");
        String sortOrder = Utils.getString("Not empty");

        Comparator<Patient> comparator = null;
        if (field.equalsIgnoreCase("patient id")) {
            comparator = Comparator.comparing(Patient::getId);
        } else if (field.equalsIgnoreCase("patient's name")) {
            comparator = Comparator.comparing(Patient::getName);
        } else {
            System.out.println("Invalid field!");
            return;
        }

        List<Patient> sortedList = new ArrayList<>(patientList.values());
        if (sortOrder.equalsIgnoreCase("asc")) {
            sortedList.sort(comparator);
        } else if (sortOrder.equalsIgnoreCase("desc")) {
            sortedList.sort(comparator.reversed());
        } else {
            System.out.println("Invalid sort order!");
            return;
        }

        System.out.println("LIST OF PATIENTS");
        int count = 1;
        for (Patient p : sortedList) {
            System.out.printf("%-4s%-12s%-16s%-12s%-15s%-1s\n", count, p.getId(), p.getAdmissionDate(), p.getName(), p.getPhone(), p.getDiagnosis());
            count++;
        }

    }

    public boolean exitProgram() {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to save Data?");
        System.out.println("(y/yes) to exit with save. Others to exit without save");
        String choice = Utils.getString("Not empty!!!!");
        boolean check = false;

        if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
            check = true;
        } else if (choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("no")) {
            check = false;
        }
        return check;
    }

    public void displayPatient() throws ParseException {
        itera2 = patientList.entrySet().iterator();
        boolean OK = false;
        String admissionDate = "";
        do {
            System.out.print("Enter admissionDate: ");
            admissionDate = Utils.getString("Not empty");
            String regex = "^\\d{2}/\\d{2}/\\d{4}$";
            if (!(admissionDate.matches(regex))) {
                System.out.println("Wrong format!!!!");
                OK = false;
            } else {
                OK = true;
            }

        } while (OK == false);
        OK = false;
        //dischargeDate
        String dischargeDate = "";
        do {
            System.out.print("Enter dischargeDate: ");
            dischargeDate = Utils.getString("Not empty");
            String regex = "^\\d{2}/\\d{2}/\\d{4}$";
            if (!(dischargeDate.matches(regex))) {
                System.out.println("Wrong format!!!!");
                OK = false;
            } else {
                OK = true;
            }

        } while (OK == false);
        OK = false;
        Date start = sdf.parse(admissionDate);
        Date end = sdf.parse(dischargeDate);

        System.out.println("-----PATIENTS LIST-----");
        System.out.printf("%-5s%-13s%-17s%-13s%-16s%-2s\n", "No.", "Patient Id", "Admission Date", "Full name", "Phone", "Diagnosis");
        int count = 0;
        while (itera2.hasNext()) {
            Map.Entry<String, Patient> entry = itera2.next();
            count++;
            Patient p = entry.getValue();
            Date checkDate = sdf.parse(p.getAdmissionDate());

            if (checkDate.compareTo(start) >= 0 && checkDate.compareTo(end) <= 0) {
                System.out.printf("%-5s%-13s%-17s%-13s%-16s%-2s\n", count, p.getId(), p.getAdmissionDate(), p.getName(), p.getPhone(), p.getDiagnosis());
            }
        }
    }

//    public void test() {
//        String patientid = Utils.getString("not empty!!!!");
////        Iterator<Map.Entry<String, Patient>> patientIte = patientList.entrySet().iterator();
////        while (patientIte.hasNext()) {
////            Map.Entry<String, Patient> hashnext = patientIte.next();
////            if (hashnext.getValue().getId().equalsIgnoreCase(patientid)) {
////                System.out.println("Nurse 1: " + hashnext.getValue().getNurse1().getPhone());
////                System.out.println("Nurse 2: " + hashnext.getValue().getNurse2().getName());
//        Iterator<Map.Entry<String, Patient>> patientValue = patientList.entrySet().iterator();
//        while (patientValue.hasNext()) {
//            Map.Entry<String, Patient> nexto = patientValue.next();
//            if (nexto.getValue().getId().equalsIgnoreCase(patientid)) {
//                System.out.println("Nurse 1: " + nexto.getValue().getNurse1().getName());
//                System.out.println("Nurse 2: " + nexto.getValue().getNurse2().getName());
//
//            }
//        }
//    }
    public void displayAll() throws ParseException {
        System.out.println("All nurse");
        for (Nurse nurse : nurseList.values()) {
            System.out.println(nurse);
//        }
            System.out.println("---------");
            System.out.println("All patient");
            for (Patient patient : patientList.values()) {
                System.out.println(patient);
            }
        }

    }

    public void sort() throws ParseException {
        List<Patient> list = new ArrayList<>(patientList.values());
        list.sort((o1, o2) -> o1.getAdmissionDate().compareTo(o2.getAdmissionDate()));
        for (Patient patient : list) {
            System.out.println(patient);
        }
    }
}
