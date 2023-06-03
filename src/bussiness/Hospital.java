package bussiness;

import java.util.ArrayList;
import java.util.Map.Entry;
import models.Nurse;
import models.Patient;
import tool.FileHandle;

public class Hospital {

    NurseList nurseList = new NurseList();
    PatientList patientList = new PatientList();
    //đường dẫn tới file
    private final String nursePath = "\\src\\files\\nurses.dat";
    private final String patientPath = "\\src\\files\\patients.dat";

    public Hospital() {
    }

    //1. Tạo y tá
    public void createNurse() {
        nurseList.createNurse();
    }

    //2. Tìm y tá bằng tên, một phần tên
    public void findNurseByName() {
        nurseList.findNurseByName();
    }

    //3. Cập nhật thông tin y tá
    public void updateNurse() {
        nurseList.updateNurse();
    }

    //4. Xóa y tá
    public void deleteNurse() {
        nurseList.deleteNurse();
    }

    //5. Thêm bệnh nhân
    public void addPatient() {
        patientList.addPatient(nurseList);
    }

    //6. In danh sách bệnh nhân trong khoảng ngày nhập viện
    public void displayPatient() {
        patientList.displayPatient();
    }

    //7. Sắp xếp danh sách bệnh nhân
    public void sortCollection() {
        patientList.sortCollection();
    }

    //8. Save data
    public void saveData() {
        ArrayList<String> dta = new ArrayList<>();
        for (Entry<String, Nurse> item : nurseList.entrySet()) {
            dta.add(item.getValue().toString());
        }
        FileHandle.writeToFile(nursePath, dta);
        dta.clear();
        for (Entry<String, Patient> item : patientList.entrySet()) {
            dta.add(item.getValue().toString());
        }
        FileHandle.writeToFile(patientPath, dta);
    }

    //9. Load data
    public void loadData() {
        nurseList.clear();
        patientList.clear();
        ArrayList<String> dta = new ArrayList<>();
        dta.addAll(FileHandle.readFromFile(nursePath));
        dta.addAll(FileHandle.readFromFile(patientPath));
        for (String item : dta) {
            String lineSplit[] = item.trim().split("\\|");
            //đọc data đầu tiên xem nó là nurse hay patient
            if (lineSplit[0].matches("N\\d{4}")) {
                //ghi vào HashMap mới tạo
                nurseList.put(lineSplit[0],
                        new Nurse(lineSplit[1],
                                Integer.parseInt(lineSplit[2]),
                                lineSplit[3],
                                lineSplit[4],
                                lineSplit[5],
                                lineSplit[0],
                                lineSplit[6],
                                lineSplit[7],
                                Double.parseDouble(lineSplit[8])));
            } else if (lineSplit[0].matches("P\\d{4}")) {
                String[] nll = lineSplit[9].split("\\|");
                NurseList nl = new NurseList();
                for (String n : nll) {
                    nl.put(n, nurseList.find(n));
                }
                //menu sẽ lưu mảng các option để mình chọn
                patientList.put(lineSplit[0], new Patient(lineSplit[1],
                        Integer.parseInt(lineSplit[2]),
                        lineSplit[3],
                        lineSplit[4],
                        lineSplit[5],
                        lineSplit[0],
                        lineSplit[6],
                        lineSplit[7],
                        lineSplit[8],
                        nl)
                );
            }
        }
        for (String nuKey : nurseList.keySet()) {
            for (String  paKey : patientList.keySet()) {
                Patient patient = patientList.get(paKey);
                for (String nuListKey : patient.getNl().keySet()) {
                    if(nuListKey.contains(nuKey))
                        nurseList.get(nuKey).incNumOfPatient();
                }
            }
        }
    }

    //10. Quit
    public void quitProgram() {
        String confirm = tool.InputHandle.getString("DO YOU WANT TO EXIT PROGRAM? [Y/N]: ");
        if (confirm.equalsIgnoreCase("Y")) {
            //save data if has changed
            saveData();
            //Thoát
            System.exit(0);
        }
    }
}
