package Object;

import java.io.Serializable;

public class Student implements Serializable{
    private String id ,name,major,email,passport,address,phone;
    private int count;
    public Student() {
    }

    public Student(String id, String name, String major, String email, String passport, String address, String phone) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.email = email;
        this.passport = passport;
        this.address = address;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public String getEmail() {
        return email;
    }

    public String getPassport() {
        return passport;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCount() {
        return count;
    }
    
    public void setCount(int count){
        this.count = count;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" + "id: " + id + ", name: " + name + ", major: " + major + ", email: " + email + ", passport: " + passport + ", address: " + address + ", phone: " + phone + '}';
    }
    
}