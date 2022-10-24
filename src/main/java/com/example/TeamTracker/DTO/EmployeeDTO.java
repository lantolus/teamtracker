package com.example.TeamTracker.DTO;

import lombok.Data;

import java.time.LocalDate;


//Employee DTO for selective returning obj attributes to frontend based on request
@Data
public class EmployeeDTO {

    private Long id;
    private Long personalNumber;
    private String emea2;
    private String wiw;
    private String scrum;
    private Boolean keyPlayer;
    private String employeeName;
    private String name;
    private String cc;
    private String role;
    private LocalDate dateOfBirth;
    private String privatePhone;
    private String companyPhone;
    private String jobTitle;
    private Character grade;
    private LocalDate validFromNow;
    private Integer basicSalary;
    private String employmentStatus;
    private LocalDate onboardingDate;
    private LocalDate exitDate;
    private String contractType;
    private String calendar;
    private LocalDate qaPandT;
    private LocalDate qaZeroOutage;
    private LocalDate qaEsaris;
    private LocalDate dataPrivacyTraining;
    private LocalDate codeOfConduct;
    private String srp2022;
    private String isrp2022;
    private Integer bonus2022;
    private LocalDate f2f;
    private String tasks;
    private String notes;
    private String computer;
    private Long inventory;
    private String sn;
    private String hostname;
    private String image;
}
