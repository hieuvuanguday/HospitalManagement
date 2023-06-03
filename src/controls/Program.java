package controls;

import bussiness.Hospital;
import ui.Menu;

public class Program {

    public static void main(String[] args) {
        Menu menu = new Menu("---------HOSPITAL MANAGEMENT---------");
        menu.addNewOption("CREATE A NURSE");
        menu.addNewOption("FIND THE NURSE");
        menu.addNewOption("UPDATE THE NURSE");
        menu.addNewOption("DELETE THE NURSE");
        menu.addNewOption("ADD A PATIENT");
        menu.addNewOption("DISPLAY PATIENTS");
        menu.addNewOption("SORT THE PATIENTS LIST");
        menu.addNewOption("SAVE DATA");
        menu.addNewOption("LOAD DATA");
        menu.addNewOption("QUIT");
        Hospital hm = new Hospital();
        int choice;
        while (true) {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1: {
                    while (true) {
                        hm.createNurse();
                        hm.saveData();
                        String flag = tool.InputHandle.getString("Continue add new nurse? [Y/N]: ");
                        if (flag.equalsIgnoreCase("N")) {
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    hm.findNurseByName();
                    break;
                }
                case 3: {
                    hm.updateNurse();
                    hm.saveData();
                    break;
                }
                case 4: {
                    hm.deleteNurse();
                    hm.saveData();
                    break;
                }
                case 5: {
                    while (true) {
                        hm.addPatient();
                        hm.saveData();
                        String flag = tool.InputHandle.getString("Continue add new patient? [Y/N]: ");
                        if (flag.equalsIgnoreCase("N")) {
                            break;
                        }
                    }
                    break;
                }
                case 6: {
                    hm.displayPatient();
                    break;
                }
                case 7: {
                    hm.sortCollection();
                    break;
                }
                case 8: {
                    hm.saveData();
                    System.out.println("Data saved successfully!");
                    break;
                }
                case 9: {
                    hm.loadData();
                    System.out.println("Load data successfully!");
                    break;
                }
                case 10: {
                    hm.quitProgram();
                    return;
                }
            }
        }
    }
}
