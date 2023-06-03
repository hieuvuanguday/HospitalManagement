package bussiness;

import java.time.Year;
import java.util.HashMap;
import models.Nurse;

public class NurseList extends HashMap<String, Nurse> {

    //1. tạo y tá mới, kiểm tra đã tồn tại ID hay chưa?
    public void createNurse() {
        Nurse newNurse = new Nurse();
        String staffID;
        System.out.println("Enter information for new Nurse!");
        //kiểm tra y tá đã tồn tại chưa?
        while (true) {
            staffID = tool.InputHandle.getString("Enter patient id: ", "This feild cannot be empty!", "N\\d{4}");
            if (!this.containsKey(staffID)) {
                break;
            } else {
                System.out.println("Nurse ID existed!");
            }
        }
        newNurse.inputInfor();
        newNurse.setStaffID(staffID);
        this.put(staffID, newNurse);
    }

    //2. tìm y tá theo tên hoặc một phần của tên
    public void findNurseByName() {
        String name = tool.InputHandle.getString("Enter nurse's name: ", "Must be long in range[1-20]!", 1, 25);
        int flag = 0;
        for (String key : this.keySet()) {
            Nurse nurse = this.get(key);
            if (nurse.getName().contains(name)) {
                nurse.showInfo();
                flag = 1;
            }
        }
        if (flag != 1) {
            System.out.println("Nurse not found!");
        }
    }

    //tìm y tá theo id
    public Nurse find(String id) {
        if (this.containsKey(id)) {
            return this.get(id);
        } else {
            return null;
        }
    }

    //3. Cập nhật thông tin y tá
    public void updateNurse() {
        String updateID = tool.InputHandle.getString("Enter StaffID: ", "Follow the format [Nxxxx] please!", "N[0-9][0-9][0-9][0-9]");
        if (this.get(updateID) != null) {
            System.out.println("Update the nurse (input blank to keep the old infomation)");
            //input new information
            String name = tool.InputHandle.getString("Enter new name: ");
            int currentYear = Year.now().getValue();
            String yob = tool.InputHandle.getString("Enter new year of birth: ");
            String gender = tool.InputHandle.getString("Enter new gender: ");
            String address = tool.InputHandle.getString("Enter new address: ");
            String phone = tool.InputHandle.getString("Enter new phone: ");
            String department = tool.InputHandle.getString("Enter new department: ");
            String shift = tool.InputHandle.getString("Enter new shift: ");
            String salary = tool.InputHandle.getString("Enter new salary: ");
            //bắt lỗi sai thì update fail
            try {
                if ((currentYear - Integer.parseInt(yob)) <= 0) {
                    throw new Exception("Age must be positive number!");
                }
                if (!gender.isEmpty() && !gender.matches("^(Male|Female)$")) {
                    throw new Exception("Gender must be Male or Female!");
                }
                if (!phone.isEmpty() && !phone.matches("^((0)|(\\+84))\\d{9}$")) {
                    throw new Exception("Phone must be [0xxxxxxxxx]or[+84xxxxxxxxx]!");
                }
                if ((!department.isEmpty() && department.length() < 3) || (!department.isEmpty() && department.length() > 50)) {
                    throw new Exception("Length of [department] field must be in range [3-50]!");
                }
                if (Double.parseDouble(salary) < 0) {
                    throw new Exception("Salary must be positive number!");
                }
            } catch (Exception e) {
                System.out.println("Update failed! " + e.getMessage());
                //cập nhật thông tin mới
            } finally {
                if (!name.isEmpty()) {
                    this.get(updateID).setName(name);
                }
                if (!yob.isEmpty()) {
                    this.get(updateID).setAge(currentYear - Integer.parseInt(yob));
                }
                if (!gender.isEmpty()) {
                    this.get(updateID).setGender(gender);
                }
                if (!address.isEmpty()) {
                    this.get(updateID).setAddress(address);
                }
                if (!phone.isEmpty()) {
                    this.get(updateID).setPhone(phone);
                }
                if (!department.isEmpty()) {
                    this.get(updateID).setDepartment(department);
                }
                if (!shift.isEmpty()) {
                    this.get(updateID).setShift(shift);
                }
                if (!salary.isEmpty()) {
                    this.get(updateID).setSalary(Double.parseDouble(salary));
                }
                System.out.println("Update successfully!");
            }
        } else {
            System.out.println("The nurse does not exit!");
        }
    }

    //4. Xóa y tá
    public void deleteNurse() {
        String delStaffID = tool.InputHandle.getString("Enter staffID: ", "Follow the format [Nxxxx] please!", "N[0-9][0-9][0-9][0-9]");
        for (String key : this.keySet()) {
            Nurse nurse = this.get(key);
            if (nurse.getStaffID().equalsIgnoreCase(delStaffID)) {
                if (nurse.getCount() != 0) {
                    System.out.println("Can not delete this nurse because still has task!");
                } else {
                    String confirm = tool.InputHandle.getString("Are you want to delete [Y/N]? ");
                    if (confirm.equalsIgnoreCase("Y")) {
                        this.remove(nurse.getStaffID());
                        System.out.println("Delete nurse successfully!");
                        return;
                    } else {
                        return;
                    }
                }
            }
        }
        System.out.println("The nurse does not exist");
    }
}
