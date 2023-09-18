/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import dto.Developer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class TeamLeader extends Developer {

    public double bonusRate;

    public double getSalary() {
        return super.getSalary() + bonusRate * super.getSalary();
    }

    public String toString() {
        return "+" + super.toString() + "_" + bonusRate;
    }
}
