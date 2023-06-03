package models;

import bussiness.NurseList;
import java.util.Map.Entry;
import tool.InputHandle;

public class Patient extends Person {

    private String id;
    private String diagnosis;
    private String admissionDate;
    private String dischargeDate;
    private NurseList nl;
//    private String nurseAssigned; 

    public Patient() {
    }

    public Patient(String name, int age, String gender, String address, String phone, String id, String diagnosis, String admissionDate, String dischargeDate, NurseList nl) {
        super(name, age, gender, address, phone);
        this.id = id;
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.nl = nl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public NurseList getNl() {
        return nl;
    }

    public void setNl(NurseList nl) {
        this.nl = nl;
    }

    @Override
    public void inputInfor() {
        super.inputInfor();
        do {
            this.diagnosis = tool.InputHandle.getString("Enter Diagnosis: ");
        } while (!InputHandle.getStringNonBlank(this.diagnosis));

        this.admissionDate = tool.InputHandle.getDate("Enter AdmissionDate [dd/mm/yyyy]: ", "dd/mm/yyyy");
        do{
            this.dischargeDate = tool.InputHandle.getDate("Enter DischargeDate [dd/mm/yyyy]: ", "dd/mm/yyyy");
        }while(tool.InputHandle.toDate(this.dischargeDate, "dd/MM/yyyy").compareTo(tool.InputHandle.toDate(this.admissionDate, "dd/MM/yyyy")) < 0);
    }

    public String toStringNl() {
        String ret = "";
        for (Entry<String, Nurse> item : nl.entrySet()) {
            ret += String.format("%s:", item.getKey());
        }
        return ret.substring(0, ret.length() - 1);
    }

    @Override
    public void showInfo() {
        String str = String.format("|%12s|%16s|%-20s|%11s|%11s|", this.id, this.admissionDate, this.name,
                this.phone, this.diagnosis);
        System.out.println(str);
    }

    @Override
    public String toString() {
        String str = String.format("%s|%s|%d|%s|%s|%s|%s|%s|%s|%s", this.id, this.name, this.age,
                this.gender, this.address, this.phone, this.diagnosis,
                this.admissionDate, this.dischargeDate,
                toStringNl());
        return str;
    }
}
