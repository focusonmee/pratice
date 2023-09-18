/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import dto.Employee;

/**
 *
 * @author ADMIN
 */
public class Tester extends Employee {

    public double bonusRate;
    public String type;

    public double getSalary() {
        return baseSal + bonusRate * baseSal;
    }

    public String toString() {
        return "+" + super.toString() + "_" + bonusRate + "_" + type;
    }
}
