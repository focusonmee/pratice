package Main;

import Management.Manager;
import validation.Validation;

public class Menu {

    Validation validation = new Validation();
    Manager manager = new Manager();

    public void int_choice() {
        boolean result = true;
        while (result) {
            System.out.println(" ---------------------------------------------");
            System.out.println("|=======Aboard Programs Management System=====|");
            System.out.println("|        1. Manage aboard programs            |");
            System.out.println("|        2. Manage students                   |");
            System.out.println("|        3. Register a program for a student  |");
            System.out.println("|        4. Report                            |");
            System.out.println("|        5. Quit                              |");
            System.out.println(" ---------------------------------------------");
            int choice = validation.inputInt("Please enter your choice: ");
            switch (choice) {
                case 1:
                    manageAboardPrograms();
                    break;
                case 2:
                    manageStudents();
                    break;
                case 3:
                    manager.register();
                    break;
                case 4:
                    Report();
                    break;
                case 5:
                    result = false;
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("enter in the range of 1-5");
                    break;
            }
        }
    }

    public void manageAboardPrograms() {
        boolean result = true;
        while (result) {
            System.out.println(" --------------------------------------------");
            System.out.println("|===========Manage aboard programs===========|");
            System.out.println("|     1. Displays all aboard programs        |");
            System.out.println("|     2. Add a new aboard program            |");
            System.out.println("|     3. Edit information a program by id    |");
            System.out.println("|     4. Search and display a program by id  |");
            System.out.println("|     5. Save data                           |");
            System.out.println("|     6. Load data                           |");
            System.out.println("|     7. Back to main menu                   |");
            System.out.println(" --------------------------------------------");
            int choice = validation.inputInt("Please enter your choice: ");
            switch (choice) {
                case 1:
                    manager.display("programs");
                    break;
                case 2:
                    manager.addPrograms();
                    break;
                case 3:
                    manager.updatePrograms();
                    break;
                case 4:
                    manager.searchPrograms();
                    break;
                case 5:
                    manager.savePrograms();
                    break;
                case 6:
                    manager.loadPrograms();
                    break;
                case 7:
                    result = false;
                    break;
                default:
                    System.out.println("enter in the range of 1-7");
                    break;
            }
        }
    }

    public void manageStudents() {
        boolean result = true;
        while (result) {
            System.out.println(" ------------------------------------------");
            System.out.println("|============Manage students===============|");
            System.out.println("|   1.Displays all students                |");
            System.out.println("|   2.Add a new student                    |");
            System.out.println("|   3.Edit information a student by id     |");
            System.out.println("|   4.Save data                            |");
            System.out.println("|   5.Load data                            |");
            System.out.println("|   6.Back to main menu                    |");
            System.out.println(" ------------------------------------------");
            int choice = validation.inputInt("Please enter your choice: ");
            switch (choice) {
                case 1:
                    manager.display("students");
                    break;
                case 2:
                    manager.addStudents();
                    break;
                case 3:
                    manager.updateStudent();
                    break;
                case 4:
                    manager.saveStudents();
                    break;
                case 5:
                    manager.loadStudents();
                    break;
                case 6:
                    result = false;
                    break;
                default:
                    System.out.println("enter in the range of 1-6");
                    break;
            }
        }
    }

    public void Report() {
        boolean result = true;
        while (result) {
            System.out.println(" -------------------------------------------------------------------");
            System.out.println("|==============================Report===============================|");
            System.out.println("|   1. Print out the registration by student’s id                   |");
            System.out.println("|   2. Print out the students that registered more than 2 programs  |");
            System.out.println("|   3. Count students that registered the program                   |");
            System.out.println("|   4. Back to main menu                                            |");
            System.out.println(" -------------------------------------------------------------------");
            int choice = validation.inputInt("Please enter your choice: ");
            switch (choice) {
                case 1:
                    manager.printByStudentId(1);;
                    break;
                case 2:
                    manager.printbyProgramID(2);
                    break;
                case 3:
                    manager.printbyProgramID(1);
                    break;
                case 4:
                    result = false;
                    break;
                default:
                    System.out.println("enter in the range of 1-4");
                    break;
            }
        }
    }
}
