package models;

import tool.InputHandle;

public class Nurse extends Person {

    private String staffID;
    private String department;
    private String shift;
    private double salary;
    private int count = 0;

    public Nurse() {
    }

    public Nurse(String name, int age, String gender, String address, String phone, String staffID, String department, String shift, double salary) {
        super(name, age, gender, address, phone);
        this.staffID = staffID;
        this.department = department;
        this.shift = shift;
        this.salary = salary;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getCount() {
        return count;
    }

    public void incNumOfPatient() {
        count++;
    }

    public void decNumOfPatient() {
        count--;
    }
    
    @Override
    public void inputInfor() {
        super.inputInfor();

        do {
            this.department = tool.InputHandle.getString("Enter Department: ", "Invalid!", 3, 50);
        } while (!InputHandle.getStringNonBlank(this.department));

        do {
            this.shift = tool.InputHandle.getString("Enter Shift: ");
        } while (!InputHandle.getStringNonBlank(this.shift));

        this.salary = tool.InputHandle.getPositiveReal("Enter Salary: ", "Salary must be positive number!");
    }

    @Override
    public void showInfo() {
        String str = String.format("|%4s|%-25s|%4d|%6s|%-30s|%10s|%-30s|%5s|%-10.2f|", this.staffID, this.name,
                this.age, this.gender, this.address, this.phone, this.department, this.shift, this.salary);
        System.out.println(str);
    }

    @Override
    public String toString() {
        String str = String.format("%s|%s|%d|%s|%s|%s|%s|%s|%f", this.staffID, this.name,
                this.age, this.gender, this.address, this.phone, this.department, this.shift, this.salary);
        return str;
    }
}
