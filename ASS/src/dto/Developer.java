/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Developer extends Employee {

    public String teamName;
    public List<String> programmingLanguages;
    public int expYear;

    public double getSalary() {
        if (expYear >= 5) {
            return baseSal + expYear * 2000000; // 2 million
        } else if (expYear >= 3) {
            return baseSal + expYear * 1000000; // 1 million
        } else {
            return baseSal;
        }
    }

    public String toString() {
        return "+" + super.toString() + "_" + teamName + "_" + programmingLanguages.toString() + "_" + expYear;
    }
}
