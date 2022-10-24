package com.example.TeamTracker.Service.Employee;

import com.example.TeamTracker.DTO.EmployeeDTO;
import com.example.TeamTracker.Model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


//Creating Interface for Employee related methods
public interface EmployeeService {


    //Abstract method  for retrieving all employees
    Collection<Employee> getAllEmployees();

    //Abstract method  for retrieving first 5 employees from DB
    Collection<Employee> getFirstFive();

    //Abstract method  for creating new Employee objects
    Employee createNewEmployee(Employee employee);

    //Abstract method for deleting Employee objects based on their ID
    void deleteEmployeeById(Long id);

    Employee updateEmployee(Employee employee);

    Page<Employee> findEmployeesWithPaginationAndSorting(int offset,int pageSize,String field,String sortDirection);

    Page<Employee> findEmployeesWithPagination(int offset,int pageSize);

    List<Employee> findEmployeesWithSorting(String field);

    Page<EmployeeDTO> findAllByParameters(
            Pageable pageable,
            Long id,
            Long personal_number,
            String emea2,
            String wiw,
            String scrum,
            Boolean keyPlayer,
            String employeeName,
            String name,
            String cc,
            String role,
            LocalDate dateOfBirth,
            String privatePhone,
            String companyPhone,
            String jobTitle,
            Character grade,
            LocalDate validFromNow,
            Integer basicSalary,
            String employmentStatus,
            LocalDate onboardingDate,
            LocalDate exitDate,
            String contractType,
            String calendar,
            LocalDate qaPandT,
            LocalDate qaZeroOutage,
            LocalDate qaEsaris,
            LocalDate dataPrivacyTraining,
            LocalDate codeOfConduct,
            String srp2022,
            String isrp2022,
            Integer bonus2022,
            LocalDate f2f,
            String tasks,
            String notes,
            String computer,
            Long inventory,
            String sn,
            String hostname,
            String image
    );

    Employee findEmployeeById(Long id);

}
