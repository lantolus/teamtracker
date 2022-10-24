package com.example.TeamTracker.Service.Employee;

import com.example.TeamTracker.DTO.EmployeeDTO;
import com.example.TeamTracker.Model.Employee;
import com.example.TeamTracker.Model.Mapper.EmployeeMapper;
import com.example.TeamTracker.Repository.EmployeeRepository;
import com.example.TeamTracker.Repository.HandlingLocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


//Setting this Class as a Service related to Employees
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final HandlingLocationRepository handlingLocationRepository;

    @Override //Overriding method to list all employees from Int
    public Collection<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override//Overriding method to list first 5 employees from Int
    public Collection<Employee> getFirstFive() {
        return employeeRepository.findAllByName();
    }

    @Override//Overriding method to create Employee Objects from Int
    public Employee createNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override //Overriding method to delete  employee objects by ID  from Int
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }


    //@Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    //Basic method to find 1 employee based on his ID for testing purpose
//    @Override
//    public Employee findEmployeeById(Long id) {
//        return employeeRepository.findEmployeeById(id);
//    }


    //Basic method for initializing employee objects with dummy data
    @Bean
    CommandLineRunner employeesInitializer() {
        return args -> {

            this.createNewEmployee(new Employee(1L, 11223344L, "A4561234", "pgondola", "Scrum 1", false, "Gondola Peter", "Gondola Peter", "T2B0000078", "Developer", LocalDate.of(2000,02,12), "911234567", "914789456", "Software Developer", 'G',LocalDate.of(2004,12,1), 1000, "Active",LocalDate.of(2014,12,1), null, "Permanent", "Flexi Telework", LocalDate.of(1898,12,1), LocalDate.of(2004,12,1),  LocalDate.of(2004,12,1),  LocalDate.of(2004,12,1),  LocalDate.of(2004,12,1), "z 900 na 1000", null, 0, null, null, null, "Dell Precision 7550", 26006000033436L, "1TSZ593", "TSSKKEWKS4722", "Nucleus"));
            this.createNewEmployee(new Employee(2L, 22334455L, "A1234567", "zuzupe", "Scrum 1", true, "Zozulák Peter", "Zozulak Peter", "T2B0000078", "PO", LocalDate.of(2000,12,18), "911234568", "914789453", "Employee Owner", 'E', LocalDate.of(2004,12,1), 1200, "Active", LocalDate.of(2004,12,19), null, "Fixed term contract", "Standard", null, null, null, null, null, null, null, 200, null, null, null, "Dell Precision 7550", 26006000033438L, "CYYG593", "TSSKKEWKS4714", "Local Image"));
            this.createNewEmployee(new Employee(3L, 33445566L, "A2092100", "pvalova", "Scrum 2", false, "Valová  Petra", "Valova Petra", "T2B0000078", "SM", LocalDate.of(2000,06,16), "911234569", "914789450", "Scrum master", 'F', LocalDate.of(2014,2,14), 900, "Active", LocalDate.of(2004,10,1), null, "Permanent", "Flexi Telework", LocalDate.of(2015,12,1), LocalDate.of(2000,12,12),  LocalDate.of(2004,12,1),  LocalDate.of(2004,12,1), LocalDate.of(2004,12,1), null, "z 850 na 900", 0, null, null, null, "Dell Precision 7550", 26006000033437L, "9Z2H593", "TSSKKEWKS4720", "R&D Nucleus"));
            this.createNewEmployee(new Employee(4L, 44556677L, "A5418767", "mava", "Scrum 2", false, "BSc. Martin Vávra", "Vavra Martin", "T2B0000099", "Developer", LocalDate.of(2000,12,1), "91134570", "914789447", "Senior Software Developer", 'F', LocalDate.of(2000,7,11), 1200, "Active", LocalDate.of(2019,12,1), null, "Fixed term contract ", "Standard", null, null, null, null, null, null, null, 0, null, null, null, "Dell Precision 7550", 26006000033441L, "5FV8593", "TSSKKEWKS4717", "Interne only"));
            this.createNewEmployee(new Employee(5L, 11223344L, "A4561234", "pgondola", "Scrum 1", false, "Fuko Moko", "Fuko Moko", "T2B0000078", "Developer", LocalDate.of(2000,07,12), "911234567", "914789456", "Software Developer", 'G',LocalDate.of(2004,12,1), 1000, "Active",LocalDate.of(2014,12,1), null, "Permanent", "Flexi Telework", LocalDate.of(1898,12,1), LocalDate.of(2004,12,1),  LocalDate.of(2004,12,1),  LocalDate.of(2004,12,1),  LocalDate.of(2004,12,1), "z 900 na 1000", null, 0, null, null, null, "Dell Precision 7550", 26006000033436L, "1TSZ593", "TSSKKEWKS4722", "Nucleus"));
            this.createNewEmployee(new Employee(6L, 22334455L, "A1234567", "zuzupe", "Scrum 1", true, "Karab Sakul", "Karab Sakul", "T2B0000078", "PO", LocalDate.of(2000,12,19), "911234568", "914789453", "Employee Owner", 'E', LocalDate.of(2004,12,1), 1200, "Active", LocalDate.of(2004,12,19), null, "Fixed term contract", "Standard", null, null, null, null, null, null, null, 200, null, null, null, "Dell Precision 7550", 26006000033438L, "CYYG593", "TSSKKEWKS4714", "Local Image"));
            this.createNewEmployee(new Employee(7L, 33445566L, "A2092100", "pvalova", "Scrum 2", false, "John Smith", "John Smith", "T2B0000078", "SM", LocalDate.of(2000,07,17), "911234569", "914789450", "Scrum master", 'F', LocalDate.of(2014,2,14), 900, "Active", LocalDate.of(2004,10,1), null, "Permanent", "Flexi Telework", LocalDate.of(2015,12,1), LocalDate.of(2000,12,12),  LocalDate.of(2004,12,1),  LocalDate.of(2004,12,1), LocalDate.of(2004,12,1), null, "z 850 na 900", 0, null, null, null, "Dell Precision 7550", 26006000033437L, "9Z2H593", "TSSKKEWKS4720", "R&D Nucleus"));
            this.createNewEmployee(new Employee(8L, 44556677L, "A5418767", "mava", "Scrum 2", false, "Wawicki Jacek", "Wawicki Jacek", "T2B0000099", "Developer", LocalDate.of(2000,12,11), "91134570", "914789447", "Senior Software Developer", 'F', LocalDate.of(2000,7,11), 1200, "Active", LocalDate.of(2019,12,1), null, "Fixed term contract ", "Standard", null, null, null, null, null, null, null, 0, null, null, null, "Dell Precision 7550", 26006000033441L, "5FV8593", "TSSKKEWKS4717", "Interne only"));
            this.createNewEmployee(new Employee(9L, 11223344L, "A4561234", "pgondola", "Scrum 1", false, "Mikal Zoran", "Mikal Zoran", "T2B0000078", "Developer", LocalDate.of(2000,02,12), "911234567", "914789456", "Software Developer", 'G',LocalDate.of(2004,12,1), 1000, "Active",LocalDate.of(2014,12,1), null, "Permanent", "Flexi Telework", LocalDate.of(1898,12,1), LocalDate.of(2004,12,1),  LocalDate.of(2004,12,1),  LocalDate.of(2004,12,1),  LocalDate.of(2004,12,1), "z 900 na 1000", null, 0, null, null, null, "Dell Precision 7550", 26006000033436L, "1TSZ593", "TSSKKEWKS4722", "Nucleus"));
            this.createNewEmployee(new Employee(10L, 22334455L, "A1234567", "zuzupe", "Scrum 1", true, "Zohan Barber", "Zohan Barber", "T2B0000078", "PO", LocalDate.of(2000,11,18), "911234568", "914789453", "Employee Owner", 'E', LocalDate.of(2004,12,1), 1200, "Active", LocalDate.of(2004,12,19), null, "Fixed term contract", "Standard", null, null, null, null, null, null, null, 200, null, null, null, "Dell Precision 7550", 26006000033438L, "CYYG593", "TSSKKEWKS4714", "Local Image"));
            this.createNewEmployee(new Employee(11L, 33445566L, "A2092100", "pvalova", "Scrum 2", false, "Borat Kazakh", "Borat Kazakh", "T2B0000078", "SM", LocalDate.of(2000,05,16), "911234569", "914789450", "Scrum master", 'F', LocalDate.of(2014,2,14), 900, "Active", LocalDate.of(2004,10,1), null, "Permanent", "Flexi Telework", LocalDate.of(2015,12,1), LocalDate.of(2000,12,12),  LocalDate.of(2004,12,1),  LocalDate.of(2004,12,1), LocalDate.of(2004,12,1), null, "z 850 na 900", 0, null, null, null, "Dell Precision 7550", 26006000033437L, "9Z2H593", "TSSKKEWKS4720", "R&D Nucleus"));
            this.createNewEmployee(new Employee(12L, 44556677L, "A5418767", "mava", "Scrum 2", false, "Yellow Short", "Yellow Short", "T2B0000099", "Developer", LocalDate.of(2000,12,2), "91134570", "914789447", "Senior Software Developer", 'F', LocalDate.of(2000,7,11), 1200, "Active", LocalDate.of(2019,12,1), null, "Fixed term contract ", "Standard", null, null, null, null, null, null, null, 0, null, null, null, "Dell Precision 7550", 26006000033441L, "5FV8593", "TSSKKEWKS4717", "Interne only"));

        };
    }

    public List<Employee> findEmployeesWithSorting(String field) {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Override
    public Page<Employee> findEmployeesWithPagination(int offset, int pageSize) {
        Page<Employee> Employees = employeeRepository.findAll(PageRequest.of(offset, pageSize));
        return Employees;
    }

    @Override
    public Page<Employee> findEmployeesWithPaginationAndSorting(int offset,
                                                                int pageSize,
                                                                String field,
                                                                String sortDirection
    ) {
        Sort sort = sortDirection.equalsIgnoreCase
                (Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending() :
                Sort.by(field).descending();

        Pageable pageable = PageRequest.of(offset, pageSize, sort);
        int totalPages = employeeRepository.findAll(pageable).getTotalPages();
        int totalElements = employeeRepository.findAll(pageable).getNumberOfElements();


        return this.employeeRepository.findAll(pageable);
    }


    //Method for getting all Employee objects based on parameters passed, which is mapped to the Employee DTO
    //And implementing paging into the Request
    @Override
    public Page<EmployeeDTO> findAllByParameters(Pageable pageable,
                                                 Long id,
                                                 Long personalNumber,
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
                                                 LocalDate qaZero_Outage,
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
                                                 String image) {
        return employeeRepository.findAllByParameters(pageable,
                personalNumber,
                emea2,
                wiw,
                scrum,
                keyPlayer,
                employeeName,
                name,
                cc,
                role,
                dateOfBirth,
                privatePhone,
                companyPhone,
                jobTitle,
                grade,
                validFromNow,
                basicSalary,
                employmentStatus,
                onboardingDate,
                exitDate,
                contractType,
                calendar,
                qaPandT,
                qaZero_Outage,
                qaEsaris,
                dataPrivacyTraining,
                codeOfConduct,
                srp2022,
                isrp2022,
                bonus2022,
                f2f,
                tasks,
                notes,
                computer,
                inventory,
                sn,
                hostname,
                image

        ).map(EmployeeMapper::toDto);
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id);
    }

}