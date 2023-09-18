/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.TeamLeader;
import dto.Tester;
import dto.Employee;
import dto.Developer;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CompanyManagement {

    private List<Employee> empList;

    public CompanyManagement() {
    }

    public CompanyManagement(String path, String path1) {
        empList = new ArrayList<>();
        getEmployeeFromFile(path, path1);
    }

    public void getEmployeeFromFile(String path, String path1) {
        try (BufferedReader br = new BufferedReader(new FileReader(path));
                BufferedReader br1 = new BufferedReader(new FileReader(path1))) {

            String employeeLine;
            String languageLine;

            while ((employeeLine = br.readLine()) != null && (languageLine = br1.readLine()) != null) {
                String[] employeeData = employeeLine.split(",");
                String[] languageData = languageLine.split(",");

                String empType = employeeData[0];
                String empID = employeeData[1];
                String empName = employeeData[2];

                if (empType.equals("D")) {
                    Developer developer = new Developer();
                    developer.empID = empID;
                    developer.empName = empName;
                    developer.baseSal = Double.parseDouble(employeeData[5]);
                    developer.teamName = employeeData[3];
                    developer.expYear = Integer.parseInt(employeeData[4]);
                    developer.programmingLanguages = new ArrayList<>();

                    for (String language : languageData) {
                        developer.programmingLanguages.add(language);
                    }

                    empList.add(developer);
                } else if (empType.equals("TL")) {
                    TeamLeader teamLeader = new TeamLeader();
                    teamLeader.empID = empID;
                    teamLeader.empName = empName;
                    teamLeader.baseSal = Double.parseDouble(employeeData[7]);
                    teamLeader.teamName = employeeData[3];
                    teamLeader.expYear = Integer.parseInt(employeeData[4]);
                    teamLeader.programmingLanguages = new ArrayList<>();

                    for (String language : languageData) {
                        teamLeader.programmingLanguages.add(language);
                    }

                    teamLeader.bonusRate = Double.parseDouble(employeeData[6]);

                    empList.add(teamLeader);
                } else if (empType.equals("T")) {
                    Tester tester = new Tester();
                    tester.empID = empID;
                    tester.empName = empName;
                    tester.baseSal = Double.parseDouble(employeeData[4]);
                    tester.bonusRate = Double.parseDouble(employeeData[3]);
                    tester.type = languageData[0];

                    empList.add(tester);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Developer> getDeveloperByProgrammingLanguage(String pl) {
        List<Developer> developers = new ArrayList<>();

        for (Employee employee : empList) {
            if (employee instanceof Developer) {
                Developer developer = (Developer) employee;
                if (developer.programmingLanguages.contains(pl)) {
                    developers.add(developer);
                }
            }
        }

        return developers;
    }

    public List<Tester> getTestersHaveSalaryGreaterThan(double value) {
        List<Tester> testers = new ArrayList<>();

        for (Employee employee : empList) {
            if (employee instanceof Tester) {
                Tester tester = (Tester) employee;
                if (tester.getSalary() > value) {
                    testers.add(tester);
                }
            }
        }

        return testers;
    }

    public Employee getEmployeeWithHighestSalary() {
        return Collections.max(empList, Comparator.comparing(Employee::getSalary));
    }

    public TeamLeader getLeaderWithMostEmployees() {
        TeamLeader leader = null;
        int maxEmployees = 0;

        for (Employee employee : empList) {
            if (employee instanceof TeamLeader) {
                TeamLeader teamLeader = (TeamLeader) employee;
                int employeeCount = 0;

                for (Employee emp : empList) {
                    if (emp instanceof Developer) {
                        Developer developer = (Developer) emp;
                        if (developer.teamName.equals(teamLeader.teamName)) {
                            employeeCount++;
                        }
                    }
                }

                if (employeeCount > maxEmployees) {
                    maxEmployees = employeeCount;
                    leader = teamLeader;
                }
            }
        }

        return leader;
    }

    public List<Employee> sorted() {
        List<Employee> sortedList = new ArrayList<>(empList);
        sortedList.sort((e1, e2) -> {
            if (e1.getSalary() != e2.getSalary()) {
                return Double.compare(e2.getSalary(), e1.getSalary());
            } else {
                String[] name1 = e1.empName.split(" ");
                String[] name2 = e2.empName.split(" ");
                return name1[name1.length - 1].compareTo(name2[name2.length - 1]);
            }
        });

        return sortedList;
    }

    public void printEmpList() {
        printEmpList(empList);
    }

    public void printEmpList(List<Employee> list) {
        for (Employee employee : list) {
            System.out.println(employee.toString());
        }
    }

    public void writeFile(String path, List<Employee> list) {
        try (FileWriter writer = new FileWriter(path)) {
            for (Employee employee : list) {
                writer.write(employee.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String path, Employee object) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(object.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
