package com.example.TeamTracker.Repository;

import com.example.TeamTracker.Model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

//Setting this class as a Central Repo for Employee Objects
@Repository
@Transactional
//Extending JPA Repo so we can use default methods
public interface EmployeeRepository extends JpaRepository<Employee, Long> {



    //Custom Query for retrieving only first 5 employees from the DB
    @Query(
            value = "SELECT * FROM EMPLOYEES LIMIT 5",
            nativeQuery = true)
    Collection<Employee> findAllByName();

    Employee findEmployeeById(Long id);

    public Page<Employee> findAll( Pageable pageable);

    //Query for selecting certain object attributes,in purpose of filtering
    @Query("SELECT employee FROM Employee employee" +
            " WHERE (:personalNumber IS NULL OR LOWER(employee.personalNumber) LIKE LOWER(CONCAT('%', :personalNumber, '%'))) AND " +
            "(:emea2 IS NULL OR LOWER(employee.emea2) LIKE LOWER(CONCAT('%', :emea2, '%'))) AND " +
            "(:wiw IS NULL OR LOWER(employee.wiw) LIKE LOWER(CONCAT('%', :wiw, '%'))) AND " +
            "(:scrum IS NULL OR LOWER(employee.scrum) LIKE LOWER(CONCAT('%', :scrum, '%'))) AND " +
            "(:keyPlayer IS NULL OR LOWER(employee.keyPlayer) LIKE LOWER(CONCAT('%', :keyPlayer, '%'))) AND " +
            "(:employeeName IS NULL OR LOWER(employee.employeeName) LIKE LOWER(CONCAT('%', :employeeName, '%'))) AND " +
            "(:name IS NULL OR LOWER(employee.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:cc IS NULL OR LOWER(employee.cc) LIKE LOWER(CONCAT('%', :cc, '%'))) AND " +
            "(:role IS NULL OR LOWER(employee.role) LIKE LOWER(CONCAT('%', :role, '%'))) AND " +
            "(:dateOfBirth IS NULL OR LOWER(employee.dateOfBirth) LIKE LOWER(CONCAT('%', :dateOfBirth, '%'))) AND " +
            "(:privatePhone IS NULL OR LOWER(employee.privatePhone) LIKE LOWER(CONCAT('%', :privatePhone, '%'))) AND " +
            "(:companyPhone IS NULL OR LOWER(employee.companyPhone) LIKE LOWER(CONCAT('%', :companyPhone, '%'))) AND " +
            "(:jobTitle IS NULL OR LOWER(employee.jobTitle) LIKE LOWER(CONCAT('%', :jobTitle, '%'))) AND " +
            "(:grade IS NULL OR LOWER(employee.grade) LIKE LOWER(CONCAT('%', :grade, '%'))) AND " +
            "(:validFromNow IS NULL OR LOWER(employee.validFromNow) LIKE LOWER(CONCAT('%', :validFromNow, '%'))) AND " +
            "(:basicSalary IS NULL OR LOWER(employee.basicSalary) LIKE LOWER(CONCAT('%', :basicSalary, '%'))) AND " +
            "(:employmentStatus IS NULL OR LOWER(employee.employmentStatus) LIKE LOWER(CONCAT('%', :employmentStatus, '%'))) AND " +
            "(:onboardingDate IS NULL OR LOWER(employee.onboardingDate) LIKE LOWER(CONCAT('%', :onboardingDate, '%'))) AND " +
            "(:exitDate IS NULL OR LOWER(employee.exitDate) LIKE LOWER(CONCAT('%', :exitDate, '%'))) AND " +
            "(:contractType IS NULL OR LOWER(employee.contractType) LIKE LOWER(CONCAT('%', :contractType, '%'))) AND " +
            "(:calendar IS NULL OR LOWER(employee.calendar) LIKE LOWER(CONCAT('%', :calendar, '%'))) AND " +
            "(:qaPandT IS NULL OR LOWER(employee.qaPandT) LIKE LOWER(CONCAT('%', :qaPandT, '%'))) AND " +
            "(:qaZeroOutage IS NULL OR LOWER(employee.qaZeroOutage) LIKE LOWER(CONCAT('%', :qaZeroOutage, '%'))) AND " +
            "(:qaEsaris IS NULL OR LOWER(employee.qaEsaris) LIKE LOWER(CONCAT('%', :qaEsaris, '%'))) AND " +
            "(:dataPrivacyTraining IS NULL OR LOWER(employee.dataPrivacyTraining) LIKE LOWER(CONCAT('%', :dataPrivacyTraining, '%'))) AND " +
            "(:codeOfConduct IS NULL OR LOWER(employee.codeOfConduct) LIKE LOWER(CONCAT('%', :codeOfConduct, '%'))) AND " +
            "(:srp2022 IS NULL OR LOWER(employee.srp2022) LIKE LOWER(CONCAT('%', :srp2022, '%'))) AND " +
            "(:isrp2022 IS NULL OR LOWER(employee.isrp2022) LIKE LOWER(CONCAT('%', :isrp2022, '%'))) AND " +
            "(:bonus2022 IS NULL OR LOWER(employee.bonus2022) LIKE LOWER(CONCAT('%', :bonus2022, '%'))) AND " +
            "(:f2f IS NULL OR LOWER(employee.f2f) LIKE LOWER(CONCAT('%', :f2f, '%'))) AND " +
            "(:tasks IS NULL OR LOWER(employee.tasks) LIKE LOWER(CONCAT('%', :tasks, '%'))) AND " +
            "(:notes IS NULL OR LOWER(employee.notes) LIKE LOWER(CONCAT('%', :notes, '%'))) AND " +
            "(:computer IS NULL OR LOWER(employee.computer) LIKE LOWER(CONCAT('%', :computer, '%'))) AND " +
            "(:inventory IS NULL OR LOWER(employee.inventory) LIKE LOWER(CONCAT('%', :inventory, '%'))) AND " +
            "(:sn IS NULL OR LOWER(employee.sn) LIKE LOWER(CONCAT('%', :sn, '%'))) AND " +
            "(:hostname IS NULL OR LOWER(employee.hostname) LIKE LOWER(CONCAT('%', :hostname, '%'))) AND " +
            "(:image IS NULL OR LOWER(employee.image) LIKE LOWER(CONCAT('%', :image, '%')))")
    Page<Employee> findAllByParameters(Pageable pageable,
                                       @Param("personalNumber") Long personalNumber,
                                       @Param("emea2") String emea2,
                                       @Param("wiw") String wiw,
                                       @Param("scrum") String scrum,
                                       @Param("keyPlayer") Boolean keyPlayer,
                                       @Param("employeeName") String employeeName,
                                       @Param("name") String name,
                                       @Param("cc") String cc,
                                       @Param("role") String role,
                                       @Param("dateOfBirth") LocalDate dateOfBirth,
                                       @Param("privatePhone") String privatePhone,
                                       @Param("companyPhone") String companyPhone,
                                       @Param("jobTitle") String jobTitle,
                                       @Param("grade") Character grade,
                                       @Param("validFromNow") LocalDate validFromNow,
                                       @Param("basicSalary") Integer basicSalary,
                                       @Param("employmentStatus") String employmentStatus,
                                       @Param("onboardingDate") LocalDate onboardingDate,
                                       @Param("exitDate") LocalDate exitDate,
                                       @Param("contractType") String contractType,
                                       @Param("calendar") String calendar,
                                       @Param("qaPandT") LocalDate qaPandT,
                                       @Param("qaZeroOutage") LocalDate qaZeroOutage,
                                       @Param("qaEsaris") LocalDate qaEsaris,
                                       @Param("dataPrivacyTraining") LocalDate dataPrivacyTraining,
                                       @Param("codeOfConduct") LocalDate codeOfConduct,
                                       @Param("srp2022") String srp2022,
                                       @Param("isrp2022") String isrp2022,
                                       @Param("bonus2022") Integer bonus2022,
                                       @Param("f2f") LocalDate f2f,
                                       @Param("tasks") String tasks,
                                       @Param("notes") String notes,
                                       @Param("computer") String computer,
                                       @Param("inventory") Long inventory,
                                       @Param("sn") String sn,
                                       @Param("hostname") String hostname,
                                       @Param("image") String image
    );
}