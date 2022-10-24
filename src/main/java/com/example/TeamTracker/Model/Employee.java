//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.TeamTracker.Model;

import com.example.TeamTracker.Security.IntegerToAttrConverter;
import com.example.TeamTracker.Security.StringToAttrConverter;
import lombok.*;

import java.time.LocalDate;
import javax.persistence.*;
//Allowing this class to Create a TABLE based on Entity"s Attributes
@Table(
        name = "Employees",
        uniqueConstraints = {@UniqueConstraint(
                name = "id",
                columnNames = {"id"}
        )}
)

//Setting the name of the Entity within class
@Entity(
        name = "Employee"
)
@ToString
@EqualsAndHashCode
//Generating constructors, getters,and setters for this Obj
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Employee {


    //Creating Auto-Generated primary key ID
    @Id
    @JoinColumn(name = "id")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "employee_Sequence"
    )
    private Long id;


    //Creating Columns based on all attributes
    @Column ()
    private Long personalNumber;

    @Column (
            nullable = false
            )
    private String emea2;


    @Column(
            nullable = false
            )
    private String wiw;

    @Column (
            nullable = false
            )
    private String scrum;

    @Column (
            )
    private Boolean keyPlayer;

    @Column()
    private String employeeName;

    @Column (
            nullable = false
            )
    private String name;

    @Column (
            nullable = false
            )
    private String cc;

    @Column (
            nullable = false
            )
    private String role;

    @Column (
            nullable = false
            )
    private LocalDate dateOfBirth;

    @Column (
            )
    private String privatePhone;

    @Column (
            nullable = false
            )
    private String companyPhone;

    @Column (
            nullable = false
            )
    private String jobTitle;

    @Column
            (
            nullable = false
            )
    private Character grade;

    @Column (
            nullable = false
            )
    private LocalDate validFromNow;

    @Convert(converter = IntegerToAttrConverter.class)
    @Column (
            )
    private Integer basicSalary;

    @Column (
            )
    private String employmentStatus;

    @Column (
            )
    private LocalDate onboardingDate;

    @Column (
            )
    private LocalDate exitDate;

    @Column (
            )
    private String contractType;

    @Column (
            )
    private String calendar;

    @Column (
            )
    private LocalDate qaPandT;

    @Column (
            )
    private LocalDate qaZeroOutage;

    @Column (
            )
    private LocalDate qaEsaris;

    @Column (
            )
    private LocalDate dataPrivacyTraining;

    @Column (
            )
    private LocalDate codeOfConduct;

    //Applying encryption on DB layer
    @Convert(converter = StringToAttrConverter.class)
    @Column (
            )
    private String srp2022;

    //Applying encryption on DB layer
    @Convert(converter = StringToAttrConverter.class)
    @Column (
            )
    private String isrp2022;

    //Applying encryption on DB layer
    @Convert(converter = IntegerToAttrConverter.class)
    @Column (
            )
    private Integer bonus2022;

    @Column (
            )
    private LocalDate f2f;

    //Setting this column to be type LONGTEXT so it can contain more that
    @Column(columnDefinition = "LONGTEXT")
    private String tasks;

    //Setting this column to be type LONGTEXT so it can contain more that
    @Column(columnDefinition = "LONGTEXT")
    private String notes;

    @Column (
            )
    private String computer;

    @Column (
            )
    private Long inventory;

    @Column (
            )
    private String sn;

    @Column (
            )
    private String hostname;

    @Column (
            )
    private String image;

    public Long getID() {
        return this.id;
    }

}
