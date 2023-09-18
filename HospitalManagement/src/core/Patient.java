/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.Serializable;
import java.util.ArrayList;
import core.Nurse;

/**
 *
 * @author acer
 */
public class Patient extends Person implements Serializable {

    protected String diagnosis;
    protected String admissionDate;
    protected String dischargeDate;
    protected Nurse nurse1;
    protected Nurse nurse2;

    public Patient() {
    }

    public Patient(String diagnosis, String admissionDate, String dischargeDate, Nurse nurse1, Nurse nurse2) {
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.nurse1 = nurse1;
        this.nurse2 = nurse2;
    }

    public Patient(String diagnosis, String admissionDate, String dischargeDate, Nurse nurse1, Nurse nurse2, String id, String name, int age, String gender, String address, String phone) {
        super(id, name, age, gender, address, phone);
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.nurse1 = nurse1;
        this.nurse2 = nurse2;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public Nurse getNurse1() {
        return nurse1;
    }

    public Nurse getNurse2() {
        return nurse2;
    }

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

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
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

    public void addNurse(Nurse nurse) {
        if (nurse1 == null) {
            nurse1 = nurse;
        } else if (nurse2 == null && nurse != nurse1) {
            nurse2 = nurse;
        }
    }

   

    @Override
    public String toString() {
        return "Patient{"
                + "diagnosis='" + diagnosis + '\''
                + ", admissionDate='" + admissionDate + '\''
                + ", dischargeDate='" + dischargeDate + '\''
                + ", nurse1=" + (nurse1 != null ? nurse1.getStaffID() : "None")
                + ", nurse2=" + (nurse2 != null ? nurse2.getStaffID() : "None")
                + '}';
    }

}
