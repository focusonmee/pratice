/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;

/**
 *
 * @author acer
 */
public class Registration {

    private String programId;
    private String studentId;
    private Date registrationDate;

    public Registration() {
    }

    public Registration(String programId, String studentId, Date registrationDate) {
        this.programId = programId;
        this.studentId = studentId;
        this.registrationDate = registrationDate;
    }

    public String getProgramId() {
        return programId;
    }

    public String getStudentId() {
        return studentId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    
}
