/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import core.HospitalManagement;

/**
 *
 * @author acer
 */
public class Nurse extends Person implements Serializable {

    protected String staffID;
    protected String department;
    protected int shift;
    protected double salary;
    protected ArrayList<Patient> assignedPatients;

    public ArrayList getPatients() {
        return this.assignedPatients;
    }

    public void addpatient(Patient patient) {
        this.assignedPatients.add(patient);
    }

    public Nurse() {
        assignedPatients = new ArrayList<>();

    }

    public Nurse(String staffID, String department, int shift, double salary, ArrayList<Patient> assignedPatients) {
        this.staffID = staffID;
        this.department = department;
        this.shift = shift;
        this.salary = salary;
        this.assignedPatients = new ArrayList<>();

    }

    public Nurse(String staffID, String department, int shift, double salary, String id, String name, int age, String gender, String address, String phone) {
        super(id, name, age, gender, address, phone);
        this.staffID = staffID;
        this.department = department;
        this.shift = shift;
        this.salary = salary;
        this.assignedPatients = new ArrayList<>();

    }

    public String getStaffID() {
        return staffID;
    }

    public String getDepartment() {
        return department;
    }

    public int getShift() {
        return shift;
    }

    public double getSalary() {
        return salary;
    }

    public ArrayList<Patient> getAssignedPatients() {
        return assignedPatients;
    }

    public String getIdPatients() {
        String res = "";
        if (this.assignedPatients.isEmpty()) {
            res = "No patient here";
        } else {
            for (Patient assignedPatient : assignedPatients) {
                res = res + assignedPatient.getId() + " ";
            }
        }
        return res.trim();
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setAssignedPatients(ArrayList<Patient> assignedPatients) {
        this.assignedPatients = assignedPatients;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean canAssignPatient() {
        return assignedPatients.size() < 2;
    }

    public void assignPatient(Patient patient) {
        assignedPatients.add(patient);
    }

    @Override
    public String toString() {
        return "Nurse{" + super.toString() + "staffID=" + staffID + ", department=" + department + ", shift=" + shift + ", salary=" + salary + "List patient(1-2):" + this.getIdPatients() + '}';
    }

}
