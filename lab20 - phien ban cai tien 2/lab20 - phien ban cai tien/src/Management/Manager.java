package Management;

import Object.Programs;
import Object.Register;
import Object.Student;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import validation.Validation;

public class Manager {

    List<Programs> pro = new ArrayList<>();
    List<Student> stu = new ArrayList<>();
    Validation valid = new Validation();

    public void display(String s) {
        if (s.equalsIgnoreCase("programs")) {
            if (pro.isEmpty()) {
                System.out.println("List is empty");
            } else {
                for (Programs program : pro) {
                    System.out.println(program);
                }
            }
        }
        if (s.equalsIgnoreCase("students")) {
            if (stu.isEmpty()) {
                System.out.println("List is empty");
            } else {
                for (Student student : stu) {
                    System.out.println(student);
                }
            }
        }
    }

    public void addPrograms() {
        boolean cont = true;
        String id;
        int day;
        while (cont) {
            while (true) {
                id = valid.inputStringRegrex("Please enter programID(Pxxx):", "P[0-9]{3}").toUpperCase();
                if (valid.exist(id, 1, stu, pro, null)) {
                    System.out.println("the id fields must be uniqque!");
                } else {
                    break;
                }
            }
            String name = valid.checkNameInput("Enter name:");
            String time = valid.inputStringRegrex("Enter time (January, March, May, July, September, November):", "JANUARY|MARCH|MAY|JULY|SEPTEMBER|NOVEMBER");
            Date begin;
            Date end;
            while (true) {
                begin = valid.InputDate("Please enter begin day (dd-MM-yyyy):");
                end = valid.InputDate("Please enter end day (dd-MM-yyyy):");
                if (!valid.compareDate1(begin, end)) {
                    System.out.println("begin day must be before end day");
                } else {
                    break;
                }
            }
            while (true) {
                day = valid.inputInt("Enter day (30 to 40):");
                if (day < 30 || day > 40) {
                    System.out.println("day shouble be 30 to 40");
                } else {
                    break;
                }
            }
            String location = valid.inputString("Enter location:");
            double cost = valid.inputDouble("Enter cost: ");
            String content = valid.inputString("Enter content: ");
            Programs program = new Programs(id, name, time, begin, end, day, location, cost, content);
            pro.add(program);
            cont = valid.checkInputYN("Do you want add more?(Y/N)");
        }
    }

    public void updatePrograms() {
        String id;
        boolean found = false;
        boolean cont = true;
        boolean result = true;
        Date begin;
        Date end;
        int day;
        while (cont) {
            id = valid.inputStringRegrex("Please enter programID(Pxxx):", "P[0-9]{3}");
            for (Programs program : pro) {
                if (program.getId().equalsIgnoreCase(id)) {
                    found = true;
                    while (result) {
                        System.out.println(" -------------------------------");
                        System.out.println("|=======Update Program==========|");
                        System.out.println("|    1. Update name             |");
                        System.out.println("|    2. Update time             |");
                        System.out.println("|    3. Update date             |");
                        System.out.println("|    4. Update day              |");
                        System.out.println("|    5. Update cost             |");
                        System.out.println("|    6. Update location         |");
                        System.out.println("|    7. Update all              |");
                        System.out.println("|    8. Show output and quit!!  |");
                        System.out.println(" -------------------------------");
                        int choice = valid.inputInt("Please enter your choice: ");
                        switch (choice) {
                            case 1:
                                program.setName(valid.checkNameInput("Enter name:"));
                                break;
                            case 2:
                                program.setTime(valid.inputStringRegrex("Enter time (January, March, May, July, September, November):", "JANUARY|MARCH|MAY|JULY|SEPTEMBER|NOVEMBER"));
                                break;
                            case 3:
                                while (true) {
                                    begin = valid.InputDate("Please enter begin day (dd-MM-yyyy):");
                                    end = valid.InputDate("Please enter end day (dd-MM-yyyy):");
                                    if (!valid.compareDate1(begin, end)) {
                                        System.out.println("begin day must be before end day");
                                    } else {
                                        break;
                                    }
                                }
                                program.setBegin(begin);
                                program.setEnd(end);
                                break;
                            case 4:
                                while (true) {
                                    day = valid.inputInt("Enter day (30 to 40):");
                                    if (day <= 30 && day >= 40) {
                                        System.out.println("day shouble be 30 to 40");
                                    } else {
                                        break;
                                    }
                                }
                                program.setDays(day);
                                break;
                            case 5:
                                program.setCost(valid.inputDouble("Enter cost: "));
                                break;
                            case 6:
                                program.setLocation(valid.inputString("Enter location:"));
                                break;
                            case 7:
                                program.setName(valid.checkNameInput("Enter name:"));
                                program.setTime(valid.inputStringRegrex("Enter time (January, March, May, July, September, November):", "JANUARY|MARCH|MAY|JULY|SEPTEMBER|NOVEMBER"));

                                while (true) {
                                    begin = valid.InputDate("Please enter begin day (dd-MM-yyyy):");
                                    end = valid.InputDate("Please enter end day (dd-MM-yyyy):");
                                    if (!valid.compareDate1(begin, end)) {
                                        System.out.println("begin day must be before end day");
                                    } else {
                                        break;
                                    }
                                }
                                program.setBegin(begin);
                                program.setEnd(end);
                                while (true) {
                                    day = valid.inputInt("Enter day (30 to 40):");
                                    if (day <= 30 && day >= 40) {
                                        System.out.println("day shouble be 30 to 40");
                                    } else {
                                        break;
                                    }
                                }
                                program.setDays(day);
                                program.setCost(valid.inputDouble("Enter cost: "));
                                program.setLocation(valid.inputString("Enter location:"));
                                break;
                            case 8:
                                System.out.println(program);
                                System.out.println("Success!");
                                result = false;
                                break;
                        }
                    }
                }

            }
            if (!found) {
                System.out.println("Flower with ID " + id + " not found.");
            }
            cont = valid.checkInputYN("Do you want to update more?(y/n)");
        }
    }

    public void searchPrograms() {
        boolean cont = true;
        while (cont) {
            boolean found = false;
            String id = valid.inputStringRegrex("Please enter programID(Pxxx):", "P[0-9]{3}");
            for (Programs program : pro) {
                if (program.getId().equalsIgnoreCase(id)) {
                    found = true;
                    System.out.println(program);
                }
            }
            if (!found) {
                System.out.println("The programs does not exist");
            }
            cont = valid.checkInputYN("Do you want to find more ? (Y/N)");
        }
    }

    public void deleteprogram() {//delete 1
        String fID = valid.inputStringRegrex("Please enter programID(Pxxx):", "P[0-9]{3}");
        String confirm;
        boolean exist = false;
        for (Programs program : pro) {
            if (program.getId().equalsIgnoreCase(fID)) {
                confirm = valid.inputForm("y", "n", "Are you sure you want to delete this ? (Y/N): ");
                if (confirm.equalsIgnoreCase("n")) {
                    System.out.println("Delete nurse is cancel! ");
                    exist = true;
                    break;
                }
            } else {
                pro.remove(program);
                System.out.println("Delete sucessful!!!");
                exist = true;
                break;

            }
        }
        if (!exist) {
            System.out.println("The program does not exist");
            System.out.println("Delete failure!!");
        }

    }

    //    public void deleteALL(){
    //        boolean result = true;
    //        double cost = valid.inputDouble("Input cost: ");
    //        int reset;
    //        do {
    //            reset = 0;
    //            for (Flower fl : flower) {
    //                if (fl.getPrice() == cost) {
    //                    reset +=1 ;
    //                    flower.remove(fl);
    //                    break;
    //                }
    //            }
    //            if (reset == 0) {
    //                result =false;
    //            }
    //        } while (result);
    //        System.out.println("delete success!!!");
    //    }
    public void addStudents() {
        boolean cont = true;
        String id;
        while (cont) {
            while (true) {
                id = valid.inputStringRegrex("Please enter studentmID(SE,SB,GD,MCxxx):", "SE[0-9]{3}|SB[0-9]{3}|GD[0-9]{3}|MC[0-9]{3}").toUpperCase();
                if (valid.exist(id, 2, stu, pro, null)) {
                    System.out.println("the id fields must be uniqque!");
                } else {
                    break;
                }
            }
            String name = valid.checkNameInput("Enter name:");
            String major = valid.inputStringRegrex("Enter major: (SE,SB,GD,MC)", "SE|SB|GD|MC").toUpperCase();
            String email = valid.inputStringRegrex("Enter email:(...@fpt.edu.vn”) ", "^[A-Z0-9._%+-]+@FPT.EDU.VN$");
            String phone = valid.inputStringRegrex("Enter phone: ", "0[987532]{1}[0-9]{8}");
            String passport = valid.inputStringRegrex("Enter passport:[VNxxxxx]", "VN[0-9]{5}").toUpperCase();
            String address = valid.inputString("Enter address:");
            Student student = new Student(id, name, major, email, passport, address, phone);
            stu.add(student);
            cont = valid.checkInputYN("Do you want to add more?(Y/N)");
        }

    }

    public void updateStudent() {
        String id;
        boolean found = false;
        boolean cont = true;
        boolean result = true;
        while (cont) {
            id = valid.inputStringRegrex("Please enter studentmID(SE,SB,GD,MCxxx):", "SE[0-9]{3}|SB[0-9]{3}|GD[0-9]{3}|MC[0-9]{3}").toUpperCase();
            for (Student student : stu) {
                if (student.getId().equalsIgnoreCase(id)) {
                    found = true;
                    while (result) {
                        System.out.println(" -------------------------------");
                        System.out.println("|=======Update Student==========|");
                        System.out.println("|    1. Update name             |");
                        System.out.println("|    2. Update major            |");
                        System.out.println("|    3. Update email            |");
                        System.out.println("|    4. Update phone            |");
                        System.out.println("|    5. Update passport         |");
                        System.out.println("|    6. Update address          |");
                        System.out.println("|    7. Update all              |");
                        System.out.println("|    8. Show output and quit!!  |");
                        System.out.println(" -------------------------------");
                        int choice = valid.inputInt("Please enter your choice: ");
                        switch (choice) {
                            case 1:
                                student.setName(valid.checkNameInput("Enter name:").toUpperCase());
                                break;
                            case 2:
                                student.setMajor(valid.inputStringRegrex("Enter major: (SE,SB,GD,MC)", "SE|SB|GD|MC").toUpperCase());
                                break;
                            case 3:
                                student.setEmail(valid.inputStringRegrex("Enter email: ", "^[A-Z0-9._%+-]+@FPT.EDU.VN$"));
                                break;
                            case 4:
                                student.setPhone(valid.inputStringRegrex("Enter phone: ", "0[987532]{1}[0-9]{8}"));
                                break;
                            case 5:
                                student.setPassport(valid.inputStringRegrex("Enter passport:", "VN[0-9]{5}").toUpperCase());
                                break;
                            case 6:
                                student.setAddress(valid.inputString("Enter address:"));
                                break;
                            case 7:
                                student.setName(valid.checkNameInput("Enter name:"));
                                student.setMajor(valid.inputStringRegrex("Enter major: (SE,SB,GD,MC)", "SE|SB|GD|MC").toUpperCase());
                                student.setEmail(valid.inputStringRegrex("Enter email:(...@fpt.edu.vn”) ", "^[A-Z0-9._%+-]+@FPT.EDU.VN$"));
                                student.setPhone(valid.inputStringRegrex("Enter emai;", "0[987532]{1}[0-9]{8}"));
                                student.setPassport(valid.inputStringRegrex("Enter passport:", "VN[0-9]{5}"));
                                student.setAddress(valid.inputString("Enter address:"));
                                break;
                            case 8:
                                System.out.println(stu);
                                System.out.println("Success!");
                                result = false;
                                break;
                        }
                    }
                }
            }
            if (!found) {
                System.out.println("Student with ID " + id + " not found.");
            }
            cont = valid.checkInputYN("Do you want to update more?");
        }
    }

    public void register() {
        boolean conti = true;
        Programs proRegist = new Programs();// chuong trinh
        Student stuRegist = new Student();// hoc sinh
        List<Register> reg = new ArrayList<>();// dang ki hs va chuong trinh
        String idP = null, idS = null;
        while (conti) {
            int count = 1;
            while (conti) {// neu student va program co roi thi nhap lai
                boolean cont = true;
                while (cont) {//input 1 program
                    boolean found = false;
                    idP = valid.inputStringRegrex("Please enter programID(Pxxx):", "P[0-9]{3}");
                    for (Programs program : pro) {
                        if (program.getId().equalsIgnoreCase(idP)) {
                            found = true;
                            System.out.println(program);
                            proRegist = program;
                            cont = false;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("The program does not exist");
                    }
                }
                cont = true;
                while (cont) {//input 1 student
                    boolean found = false;
                    idS = valid.inputStringRegrex("Please enter studentID(SE,SB,GD,MCxxx):", "SE[0-9]{3}|SB[0-9]{3}|GD[0-9]{3}|MC[0-9]{3}");
                    for (Student stud : stu) {
                        if (stud.getId().equalsIgnoreCase(idS)) {
                            found = true;
                            System.out.println(stud);
                            stuRegist = stud;
                            cont = false;
                            break;
                        }
                    }
                    for (Register regist : reg) {
                        if (regist.getStu().getId().equalsIgnoreCase(idS)) {
                            count++;
                            regist.getStu().setCount(count);
                        }
                    }
                    if (!found) {
                        System.out.println("The student does not exist");
                    }
                }
                if (reg.isEmpty()) {
                    conti = false;
                }
                for (Register regist : reg) {
                    if (regist.getPro().getId().equalsIgnoreCase(idP) && regist.getStu().getId().equalsIgnoreCase(idS)) {
                        System.out.println("This student has already registered for this program!");
                        System.out.println("Enter another program");
                        conti = true;
                        break;
                    } else {
                        conti = false;
                    }
                }
            }
            Date registDate = null;
            while (true) {
                registDate = valid.InputDate("Enter reigistDate(dd-MM-yyyy)");
                if (registDate.before(proRegist.getEnd()) && registDate.after(proRegist.getBegin())) {
                    System.out.println("registration date is valid");
                    break;
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String formattedbegin = dateFormat.format(proRegist.getBegin());
                    String formattedend = dateFormat.format(proRegist.getEnd());
                    System.out.println("Must be between begin and end date: " + "begin: " + formattedbegin + "  end: " + formattedend);
                }
            }
            String email = valid.inputStringRegrex("Enter email:(...@gmail.com”) ", "^[A-Z0-9._%+-]+@GMAIL.COM$");
            String phone = valid.inputStringRegrex("Enter phone: ", "0[987532]{1}[0-9]{8}");
            Register register = new Register(stuRegist, proRegist, email, phone, registDate);
            reg.add(register);
            System.out.println("Add successfully");
            conti = valid.checkInputYN("Do you want to add more ?(Y/N)");
        }
        valid.saveToTextFile(reg);
    }

    public void printByStudentId(int i) {
        List<Register> reg = new ArrayList<>();
        valid.loadFromTextFiles(reg, pro, stu);
        String idS = valid.inputStringRegrex("Please enter studentID(SE,SB,GD,MCxxx):", "SE[0-9]{3}|SB[0-9]{3}|GD[0-9]{3}|MC[0-9]{3}");

        for (Register regist : reg) {
            if (i == 1) {
                if (regist.getStu().getId().equalsIgnoreCase(idS)) {
                    System.out.println(regist);// in ra đơn đăng kí có mã sinh viên và chỉ khác program
                }
            } else if (i == 2) {
                if (regist.getStu().getCount() > 2) {
                    System.out.println(regist);
                }
            }
        }
    }

    public void printbyProgramID(int i) {
        boolean found = true;
        if (i == 1) {
            String idP = valid.inputStringRegrex("Please enter programID(Pxxx):", "P[0-9]{3}");
            int countp = valid.loadfileName(idP, null, 2);
            for (Programs program : pro) {
                if (program.getId().equalsIgnoreCase(idP)) {
                    System.out.println(program.getName() + " is " + countp + " student");
                }
            }
        } else {
            for (Student student : stu) {
                int counts = valid.loadfileName(null, student.getId(), 1);
                if (counts > 2) {
                    System.out.println(student);
                    found = false;
                }
            }if(found){
                System.out.println("none of the students that have more than 2 the text files ");
            }
        }
    }

    public void savePrograms() {
        List<Object> objct = new ArrayList<>(pro);
        valid.savetoFile("programs.dat", objct);
    }

    public void loadPrograms() {
        pro.clear();
        valid.loadfromPrograms("programs.dat", pro);
    }

    public void saveStudents() {
        List<Object> objct = new ArrayList<>(stu);
        valid.savetoFile("studednts.dat", objct);
    }

    public void loadStudents() {
        stu.clear();
        valid.loadfromStudents("studednts.dat", stu);
    }

}
