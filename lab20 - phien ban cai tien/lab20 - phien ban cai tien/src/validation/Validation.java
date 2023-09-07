package validation;

import Object.Programs;
import Object.Register;
import Object.Student;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Validation {

    Scanner sc = new Scanner(System.in);

    public String inputStringRegrex(String mess, String regex) {
        System.out.println(mess);
        while (true) {
            String input = sc.nextLine();
            if (!input.toUpperCase().matches(regex)) {
                System.out.println(mess + "again.");
                continue;
            }
            return input;
        }
    }//input co dieu kien

    public String inputString(String mess) {// not null
        System.out.println(mess);
        while (true) {
            String result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                result = result.substring(0, 1).toUpperCase() + result.substring(1).toLowerCase();
                return result;
            }
        }
    }

    public String toUpperthefirst(String name) {
        StringBuilder sb = new StringBuilder();
        String[] parts = name.split(" ");
        for (String part : parts) {
            sb.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1).toLowerCase()).append(" ");
        }
        name = sb.toString();
        return name;
    }

    public String checkNameInput(String output) { //input name
        String name, pattern;
        System.out.println(output);
        while (true) {
            name = input();
            pattern = "^[a-zA-Z ]+$";
            if (name.matches(pattern)) {
                name = toUpperthefirst(name);
                return name;
            } else {
                System.out.println("Please! input again.");
            }
        }
    }

    public int inputInt(String mess) {//must be positive
        while (true) {
            System.out.println(mess);
            try {
                int integer = Integer.parseInt(sc.nextLine().trim());
                if (integer < 0) {
                    System.out.println("Please, Input a positive number");
                    System.out.println("Enter again: ");
                } else {
                    return integer;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer!!");
            }

        }
    }

    public double inputDouble(String mess) {//must be positive
        while (true) {
            System.out.println(mess);
            try {
                double db = Double.parseDouble(sc.nextLine().trim());
                if (db < 0) {
                    System.out.println("Please, Input a positive number");
                    System.out.println("Enter again: ");
                } else {
                    return db;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer!!");
            }
        }
    }

    public boolean exist(String id, int ops, List<Student> stu, List<Programs> pro, List<Register> reg) {
        if (ops == 1) {
            for (Programs programs : pro) {
                if (programs.getId().equalsIgnoreCase(id)) {
                    return true;
                }
            }

        }
        if (ops == 2) {
            for (Student student : stu) {
                if (student.getId().equalsIgnoreCase(id)) {
                    return true;
                }
            }
        }
        if (ops == 3) {
            for (Register regist : reg) {
                if (regist.getStu().getId().equalsIgnoreCase(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String inputForm(String s, String i, String output) {
        String field;
        while (true) {
            System.out.println(output);
            field = input();
            if (field.equalsIgnoreCase(s) || field.equalsIgnoreCase(i)) {
                return field.toLowerCase();
            } else {
                System.out.printf("Input again!!! (%s/%s)\n", s, i);
            }
        }
    }

    public String inputFormtosort(String s, String i, String a, String b, String output) {
        String field;
        while (true) {
            System.out.println(output);
            field = input();
            if (field.equalsIgnoreCase(s) || field.equalsIgnoreCase(i) || field.equalsIgnoreCase(a) || field.equalsIgnoreCase(b)) {
                return field.toLowerCase();
            } else {
                System.out.printf("Input again!!! (%s/%s/%s/%s)\n", s, i, a, b);
            }
        }
    }

    public boolean checkInputYN(String mess) {
        System.out.println(mess);
        while (true) {
            String result = sc.nextLine();

            if (result.toUpperCase().equalsIgnoreCase("Y")) {
                return true;
            }

            if (result.toUpperCase().equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }

    public String input() {// not null
        while (true) {
            String result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public boolean checkyearValid(int day, int month, int year) {
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (year == currentYear && month == currentMonth && day <= currentDay) {
            return true;
        } else if (year == currentYear && month < currentMonth) {
            return true;
        } else if (year < currentYear && year > 1990 && month <= 12 && month >= 1) {
            return true;
        }
        return false;
    }

    public boolean isNamNhuan(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 4 == 0 && year % 100 != 0) {
            return true;
        }
        return false;
    }

    public boolean checkDateValid(int day, int month, int year) {
        if (!checkyearValid(day, month, year)) {
            System.out.println("Invalid month and year!");
            return false;
        }
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (day > 31 || day <= 0) {
                return false;
            }
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day > 30 || day <= 0) {
                return false;
            }
        }
        if (month == 2) {
            if (isNamNhuan(year)) {
                if (day > 29 || day <= 0) {
                    return false;
                }
            } else if (day > 28 || day <= 0) {
                return false;
            }
        }
        return true;
    }

    public Date getDate(String s) {// ep chuoi thanh kieu date
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy");
        try {
            date = format.parse(s);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }

    public Date InputDate(String output) {
        String pattern, data;
        int day, month, year;
        Date date;
        while (true) {
            System.out.println(output);
            pattern = "^[0-9]{1,2}-[0-9]{1,2}-[0-9]{4}$";
            data = input();
            if (data.matches(pattern)) {
                try {
                    StringTokenizer br = new StringTokenizer(data, "-");
                    day = Integer.parseInt(br.nextToken());
                    month = Integer.parseInt(br.nextToken());
                    year = Integer.parseInt(br.nextToken());
                    if (checkDateValid(day, month, year)) {
                        date = getDate(data);
                        return date;
                    } else {
                        System.out.println("wrong date!!");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Data format is invalid!!!");
            }
        }
    }

    public boolean compareDate1(Date day1, Date day2) {
        if (day1.before(day2)) {
            return true;
        } else {
            return false;
        }
    }

    public void savetoFile(String filename, List<Object> s) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(filename);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(s);
            System.out.println("Save file successful!!!");
        } catch (IOException e) {
            System.out.println("Save file fail!!");
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Programs> loadfromPrograms(String filename, List<Programs> pr) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(filename);
            ois = new ObjectInputStream(fis);
            List<Programs> program = (List<Programs>) ois.readObject();
            for (Programs pros : program) {
                pr.add(pros);
            }
            System.out.println("Load file successful!!!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                try {
                    ois.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return pr;
    }

    public List<Student> loadfromStudents(String filename, List<Student> st) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(filename);
            ois = new ObjectInputStream(fis);
            List<Student> students = (List<Student>) ois.readObject();
            for (Student stu : students) {
                st.add(stu);
            }
            System.out.println("Load file successful!!!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    ois.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return st;
    }

    public void saveToTextFile(List<Register> registrations) {
        String directoryPath = "RegistrationForm";
        File directory = new File(directoryPath);
        boolean finish = false;
        String filename = "";
        // Create the directory if it doesn't exist
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                System.out.println("Failed to create the directory for registration files.");
                return;
            }
        }
        for (Register register : registrations) {
            Student student = register.getStu();
            Programs program = register.getPro();

            StringBuilder filenameBuilder = new StringBuilder(student.getId());
            filenameBuilder.append("_").append(program.getId()).append(".txt").append(".txt");
            filename = filenameBuilder.toString();
            try {
                FileWriter writer = new FileWriter(new File(directory, filename));
                writer.write("      Aboard Program Registration Form\n");
                writer.write("Information Student:\n");
                writer.write("Student id: " + student.getId() + "          Student name:" + student.getName() + "\n");
                writer.write("Major: " + student.getMajor() + "                Email: " + student.getEmail() + "    Phone: " + student.getPhone() + "    Passport: " + student.getPassport() + "\n");
                writer.write("Address: " + student.getAddress() + "         Email of the parents: " + register.getEmailparents() + "    Phone of the parents: " + register.getPhoneparents() + "\n\n");

                writer.write("Information of the aboard program:\n");
                writer.write("Program’s id: " + program.getId() + "          Program’s name: " + program.getName() + "\n");
                writer.write("Time: " + program.getTime() + "              Days: " + program.getDays() + "        Location: " + program.getLocation() + "      Cost: " + program.getCost() + "\n\n");

                writer.write("Information of the registration:\n");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String formatted = dateFormat.format(register.getRegistDate());
                writer.write("registration date: " + formatted);
                writer.close();
                System.out.println("Registration information saved to " + filename);
            } catch (IOException e) {
                System.out.println("An error occurred while saving the registration information.");
                e.printStackTrace();
            }
        }
    }

    public void loadFromTextFiles(List<Register> reg, List<Programs> pro, List<Student> stu) {
        String directoryPath = "RegistrationForm";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            System.out.println("The directory for registration files does not exist.");
            return;
        }

        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("No registration files found in the directory.");
            return;
        }

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    Student student = new Student();
                    Programs program = new Programs();
                    String line, Emailofparent = "", phone = "";
                    Date date = null;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("Student id:")) {
                            String studentId = line.split(":")[1].replace("Student name", "").trim();
                            for (Student st : stu) {
                                if (st.getId().equalsIgnoreCase(studentId)) {
                                    student = st;
                                }
                            }
                        } else if (line.startsWith("Email of the parents:")) {
                            Emailofparent = line.split(":")[1].trim();
                        } else if (line.startsWith("Phone of the parents:")) {
                            phone = line.split(":")[1].trim();
                        } else if (line.startsWith("Program’s id:")) {
                            String programId = line.split(":")[1].replace("Program’s name", "").trim();
                            for (Programs pg : pro) {
                                if (pg.getId().equalsIgnoreCase(programId)) {
                                    program = pg;
                                }
                            }

                        } else if (line.startsWith("registration date:")) {
                            date = getDate(line.split(":")[1].trim());
                        }
                    }
                    Register register = new Register(student, program, Emailofparent, phone, date);
                    reg.add(register);
                } catch (FileNotFoundException e) {
                    System.out.println("File not found: " + file.getName());
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("An error occurred while reading the file: " + file.getName());
                    e.printStackTrace();
                }
            }
        }
    }

    public int loadfileName(String idP, String idS, int i) {
        int countp = 0, counts = 0;
        String directoryPath = "RegistrationForm";
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            String[] filenames = directory.list();
            if (filenames != null) {
                for (String filename : filenames) {
                    if (i == 1) {
                        filename = filename.split("_")[0];
                        if (filename.equalsIgnoreCase(idS)) {
                            counts++;
                        }
                    }
                    if (i == 2) {
                        filename = filename.split("_")[1].replace(".txt", "");
                        if (filename.equalsIgnoreCase(idP)) {
                            countp++;
                        }
                    }
                }
            }
        }
        if (i == 1) {
            return counts;
        } else {
            return countp;
        }
    }
}
