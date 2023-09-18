/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author ADMIN
 */
public abstract class Employee {

    public String empID;
    public String empName;
    public double baseSal;

    public abstract double getSalary();

    public String toString() {
        return empID + "_" + empName + "_" + baseSal;
    }
}


