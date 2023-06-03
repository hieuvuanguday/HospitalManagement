package models;

import java.io.Serializable;
import tool.InputHandle;
import java.time.Year;

public class Person implements Serializable {

    protected String name;
    protected int age;
    protected String gender;
    protected String address;
    protected String phone;

    public Person() {
    }

    public Person(String name, int age, String gender, String address, String phone) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void inputInfor() {
        do {
            this.name = tool.InputHandle.getString("Enter Name: ");
        } while (!InputHandle.getStringNonBlank(this.name));

        int currentYear = Year.now().getValue();
        this.age = currentYear - tool.InputHandle.getPositiveInt("Enter Year Of Birth: ", "Year must be positive integer!");

        do {
            this.gender = tool.InputHandle.getString("Enter Gender [Male/Female]: ", "[Male] or [Female] please!", "^(Male|Female)$");
        } while (!InputHandle.getStringNonBlank(this.gender));

        do {
            this.address = tool.InputHandle.getString("Enter Address: ");
        } while (!InputHandle.getStringNonBlank(this.address));

        do {
            this.phone = tool.InputHandle.getString("Enter Phone: ", "Must be [0xxxxxxxxx] or [+84xxxxxxxxx]!", "^((0)|(\\+84))\\d{9}$");
        } while (!InputHandle.getStringNonBlank(this.phone));
    }

    public void showInfo() {
        String str = String.format("|%25s|%-4d|%-10s|%-50s|%10s|", this.name,
                this.age, this.gender, this.address, this.phone);
        System.out.println(str);
    }
}
