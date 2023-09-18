package main;

import dto.Employee;
import dto.TeamLeader;
import java.util.List;
import manager.CompanyManagement;
import utils.Menu;

public class Main {

    public static void main(String[] args) {
        CompanyManagement companyManagement = new CompanyManagement("ListOfEmployees.txt", "PLInfo.txt");
        Menu menu = new Menu();
        menu.add("1. Read all Employees and print to screen");
        menu.add("2. Show staff proficient in a Programming Language");
        menu.add("3. Show Tester has the highest salary");
        menu.add("4. Show Employee's highest salary");
        menu.add("5. Show Leader of the Team has the most Employees");
        menu.add("6. Sort Employees in descending order of salary");
        menu.add("7. Write file");
        menu.add("8. Exit");

        int choice = 0;
        do {
            choice = menu.getUserChoice();
            switch (choice) {
                case 1:
                    companyManagement.printEmpList();
                    break;
                case 2:
//                    String programmingLanguage = menu.getUserInput("Enter the programming language: ");
//                    companyManagement.getDeveloperByProgrammingLanguage(programmingLanguage);
                    break;
                case 3:
//                    double minSalary = menu.getUserInputDouble("Enter the minimum salary: ");
//                    companyManagement.getTestersHaveSalaryGreaterThan(minSalary);
//                    break;
                case 4:
                    Employee highestSalaryEmployee = companyManagement.getEmployeeWithHighestSalary();
                    System.out.println("Employee with the highest salary: " + highestSalaryEmployee);
                    break;
                case 5:
                    TeamLeader leaderWithMostEmployees = companyManagement.getLeaderWithMostEmployees();
                    System.out.println("Team Leader with the most employees: " + leaderWithMostEmployees);
                    break;
                case 6:
                    List<Employee> sortedEmployees = companyManagement.sorted();
                    companyManagement.printEmpList(sortedEmployees);
                    break;
                case 7:
//                    companyManagement.writeFile("Req2.txt", companyManagement.getDeveloperByProgrammingLanguage("C++"));
//                    companyManagement.writeFile("Req3.txt", companyManagement.getTestersHaveSalaryGreaterThan(4700000));
//                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
    }
}
