package bussiness;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import models.Patient;

public class PatientList extends HashMap<String, Patient> {

    //1. thêm bệnh nhân
    public void addPatient(NurseList nurseList) {
        //nếu chưa tồn tại nurse
        if (nurseList.isEmpty()) {
            System.out.println("Do not have any nurse to take care of new patient!");
        } else {
            Patient newPatient = new Patient();
            String id;
            System.out.println("Enter information for new Patient!");
            while (true) {
                id = tool.InputHandle.getString("Enter patient id: ", "This field cannot be empty!", "P\\d{4}");
                if (!this.containsKey(id)) {
                    break;
                } else {
                    System.out.println("Patient ID existed!");
                }
            }
            newPatient.inputInfor();

            int i = 2;
            NurseList nl = new NurseList();
            //nhập 2 id của nurse, chống trùng nhau và đã có 2 tasks
            int flag = 0;
            while (i > 0) {
                if(flag == 3){
                    System.out.println("Enter wrong 3 times, please do this command again in menu [please check nurse list before]!");
                    return;
                }
                System.out.print(3 - i + ". ");
                String nId = tool.InputHandle.getString("Enter nurse assigned id: ", "This field can not be empty!", "N\\d{4}");
                if (nurseList.containsKey(nId) && !nl.containsKey(nId) && nurseList.get(nId).getCount() < 2) {
                    nl.put(nId, nurseList.get(nId));
                    i--;
                    nurseList.get(nId).incNumOfPatient();
                } else {
                    flag++;
                }
            }
            newPatient.setNl(nl);
            newPatient.setId(id);
            this.put(id, newPatient);
        }

    }

    //2. show patient in range of Date
    public void displayPatient() {
        String startDate = tool.InputHandle.getDate("Enter startDate [dd/mm/yyyy]: ", "dd/mm/yyyy");
        String endDate = tool.InputHandle.getDate("Enter endDate [dd/mm/yyyy]: ", "dd/mm/yyyy");

        System.out.println("LIST OF PATIENTS");
        System.out.println("Start date: " + startDate);
        System.out.println("Start date: " + endDate);
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("|  No.| Patient Id | Admission Date | Full name          |     Phone | Diagnosis |");
        System.out.println("----------------------------------------------------------------------------------");
        int count = 1;
        for (String key : this.keySet()) {
            Patient patient = this.get(key);
            //so sánh ngày nhập viện để in ra patient
            if (tool.InputHandle.toDate(patient.getAdmissionDate(), "dd/MM/yyyy").compareTo(tool.InputHandle.toDate(startDate, "dd/MM/yyyy")) >= 0
                    && tool.InputHandle.toDate(patient.getAdmissionDate(), "dd/MM/yyyy").compareTo(tool.InputHandle.toDate(endDate, "dd/MM/yyyy")) <= 0) {
                String str = String.format("|%5d", count);
                System.out.print(str);
                patient.showInfo();
                count++;
            }
        }
        System.out.println("----------------------------------------------------------------------------------");
    }

    //7. Sort patients
    private void sortProcess(String typeSort, String sequence) {
        Comparator<Patient> comparator;
        if (typeSort.contains("name") && sequence.equalsIgnoreCase("ASC")) {
            comparator = Comparator.comparing(Patient::getName);
        } else if (typeSort.contains("name") && sequence.equalsIgnoreCase("DESC")) {
            comparator = Comparator.comparing(Patient::getName);
            comparator = comparator.reversed();
        } else if (typeSort.contains("id") && sequence.equalsIgnoreCase("ASC")) {
            comparator = Comparator.comparing(Patient::getId);
        } else {
            comparator = Comparator.comparing(Patient::getId);
            comparator = comparator.reversed();
        }

        List<Patient> sortPatients = new ArrayList<>(this.values());
        sortPatients.sort(comparator);

        int count = 1;
        for (Patient patient : sortPatients) {
            String str = String.format("|%5d", count);
            System.out.print(str);
            patient.showInfo();
            count++;
        }
    }

    public void sortCollection() {
        System.out.println("LIST OF PATIENTS");
        String typeSort = tool.InputHandle.getString("Sorted by: ", "Must be [patient's name] or [patient's id]!", "^(patient's name|patient's id)$");
        String sequence = tool.InputHandle.getString("Sort order: ", "Invalid", "^(ASC|DESC)$");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("|  No.| Patient Id | Admission Date | Full name          |     Phone | Diagnosis |");
        sortProcess(typeSort, sequence);
        System.out.println("----------------------------------------------------------------------------------");
    }

}
