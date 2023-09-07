package Object;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Register implements Serializable {

    private Student stu;
    private Programs pro;
    private String emailparents;
    private String phoneparents;
    private Date registDate;

    public Register() {
    }

    public Register(Student stu, Programs pro, String emailparents, String phoneparents, Date registDate) {
        this.stu = stu;
        this.pro = pro;
        this.emailparents = emailparents;
        this.phoneparents = phoneparents;
        this.registDate = registDate;
    }

    public Student getStu() {
        return stu;
    }

    public Programs getPro() {
        return pro;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    public void setPro(Programs pro) {
        this.pro = pro;
    }

    public String getEmailparents() {
        return emailparents;
    }

    public String getPhoneparents() {
        return phoneparents;
    }

    public void setEmailparents(String emailparents) {
        this.emailparents = emailparents;
    }

    public void setPhoneparents(String phoneparents) {
        this.phoneparents = phoneparents;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedbegin = dateFormat.format(getRegistDate());
        return "Register{" + "Student id:" + stu.getId() + ", Student name:" + stu.getName() + ", Major:" + stu.getMajor() + ", Email:" + stu.getEmail()
                + ", Phone:" + stu.getPhone() + ", Passport:" + stu.getPassport() + ", Address:" + stu.getAddress() + ", Email of parents:" + getEmailparents()
                + ", Phone of parents:" + getPhoneparents() + ", Program's id: " + pro.getId() + ", Program’s name: " + pro.getName() + ", Time: "
                + pro.getTime() + ", Days: " + pro.getDays() + ", Location: " + pro.getLocation() + ", cost: " + pro.getCost() + ", registraion date: " + formattedbegin + '}';
    }

}
