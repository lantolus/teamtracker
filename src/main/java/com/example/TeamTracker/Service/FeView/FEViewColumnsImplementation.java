package com.example.TeamTracker.Service.FeView;

import com.example.TeamTracker.Model.FEViewTable;
import com.example.TeamTracker.Repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Service
public class FEViewColumnsImplementation {

    ViewRepository viewRepository;


    @Autowired
    public FEViewColumnsImplementation(ViewRepository viewRepository) {
        this.viewRepository=viewRepository;
    }

    //Creating an Arraylist of ViewObjects based on the references from Frontend
    List<FEViewTable> feViewObjects = new ArrayList<FEViewTable>();

    //Basic method to initialize list with the object names,and IDs from Frontend
    public void initializeList() {
        feViewObjects.add(new FEViewTable(0, "Name of Employee or Applicant", "employeeName","string",null));
        feViewObjects.add(new FEViewTable(1, "Name", "name","string",null));
        feViewObjects.add(new FEViewTable(2, "Date of birth", "dateOfBirth","date",null));
        feViewObjects.add(new FEViewTable(3, "Private phone", "privatePhone","phone",null));
        feViewObjects.add(new FEViewTable(4, "Personal number", "personal_number","Number",null));
        feViewObjects.add(new FEViewTable(5, "WIW", "wiw","string",null));
        feViewObjects.add(new FEViewTable(6, "EMEA2", "emea2","string",null));
        feViewObjects.add(new FEViewTable(7, "CoCe", "cc","string",null));
        feViewObjects.add(new FEViewTable(8, "SCRUM", "scrum","string",null));
        feViewObjects.add(new FEViewTable(9, "Role", "role","string",null));
        feViewObjects.add(new FEViewTable(10, "Job title(EN)", "jobTitle","string",null));
        feViewObjects.add(new FEViewTable(11, "Company phone", "companyPhone","phone",null));
        feViewObjects.add(new FEViewTable(12, "Grade", "grade","select",Arrays.asList("A","B","C","D","E","F")));
        feViewObjects.add(new FEViewTable(13, "Valid from", "validFrom","date",null));
        feViewObjects.add(new FEViewTable(15, "Onboarding status", "onBoardingDate","date",null));
        feViewObjects.add(new FEViewTable(16, "Employment status", "employmentStatus","select",Arrays.asList("Active","Inactive","Maternity","Leave")));
        feViewObjects.add(new FEViewTable(17, "Contract type", "contractType","select",Arrays.asList("Permanent","FixedTerm")));
        feViewObjects.add(new FEViewTable(18, "Exit date", "exitDate","date",null));
        feViewObjects.add(new FEViewTable(19, "Calendar type", "calendar","select",Arrays.asList("Standard","Flexi","Telework")));
        feViewObjects.add(new FEViewTable(20, "Key player", "keyPlayer","boolean",null));
        feViewObjects.add(new FEViewTable(21, "QA P&T", "qA_PandT","date",null));
        feViewObjects.add(new FEViewTable(22, "QA ZERO OUTAGE", "qa_Zero_Outage","date",null));
        feViewObjects.add(new FEViewTable(23, "QA ESARIS", "qa_Esaris","date",null));
        feViewObjects.add(new FEViewTable(24, "Data privacy training", "dataPrivacyTraining","date",null));
        feViewObjects.add(new FEViewTable(25, "Code of conduct", "codeOfConduct","date",null));
        feViewObjects.add(new FEViewTable(26, "SRP 2022", "srp_2022","string",null));
        feViewObjects.add(new FEViewTable(27, "Bonus 2022", "bonus_2022 ","Number",null));
        feViewObjects.add(new FEViewTable(28, "iSRP 2022", "isrp_2022","string",null));
        feViewObjects.add(new FEViewTable(29, "F2F", "f2f","date", null));
        feViewObjects.add(new FEViewTable(30, "Tasks", "tasks","text",null));
        feViewObjects.add(new FEViewTable(31, "Notes", "notes","text",null));
        feViewObjects.add(new FEViewTable(32, "Computer", "computer","string",null));
        feViewObjects.add(new FEViewTable(33, "Inventory", "inventory","string",null));
        feViewObjects.add(new FEViewTable(34, "SN", "sn","string",null));
        feViewObjects.add(new FEViewTable(35, "Hostname", "hostname","string",null));
        feViewObjects.add(new FEViewTable(36, "Image", "image","string",null));

    }
    //GET method for returning the FEViewTable Content
    public ArrayList<FEViewTable> getFeViewColumns() {
        return (ArrayList<FEViewTable>) feViewObjects;
    }

    //Method for splitting the arraylist coming as request from frontend, and splitting by coma
    public ArrayList<String> sortView(Long id) {
        String str = viewRepository.getById(id).getSelectedColumns();
        ArrayList<String> columns = new ArrayList<>(Arrays.asList(str.split(",")));
        return columns;
    }

    /*
    Method for comparing both Arraylists --> ArrayList<FEViewTable> feViewObjects and ArrayList<String> columns
    Purpose : to find common elements from both elements and to add these elements into 3rd Arraylist named commons
    which is being returned to frontend to generate the View
     */
    public ArrayList<FEViewTable> findCommon(ArrayList<FEViewTable> feViewObjects, ArrayList<String> columns) {
        ArrayList<FEViewTable> commons = new ArrayList<>();

        for (FEViewTable fe : feViewObjects) {

            for (String column : columns)
            {
                if (fe.getName().equals(column)) {
                    commons.add(fe);
                }
            }
        }
        return commons;
    }
}


