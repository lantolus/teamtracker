//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.TeamTracker.Controller;


import com.example.TeamTracker.Exceptions.EmployeeNotFoundException;
import com.example.TeamTracker.Exceptions.MandatoryFieldsNullException;
import com.example.TeamTracker.Model.ApiResponse;
import com.example.TeamTracker.Model.Employee;
import com.example.TeamTracker.Service.Employee.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

//Setting this Class as a Controller , so we can call APIs
@RestController

//Allowing FE to interact /w BE trough port 3000
@CrossOrigin(origins = "http://localhost:3000/")
//Setting Basic path for Employee related APIs
@RequestMapping("api/v1/")
public class EmployeeController {

    //Injecting Employee Service field Param
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //Mapping for Query API ,to list all  the employees found in DB
    @RequestMapping(path = "employees/findAll", produces = {"application/json"}, method = RequestMethod.GET)
    public Collection<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }



    //API for retrieving first 5 employees from DB
    @RequestMapping(path = "employees/findFive", produces = {"application/json"}, method = RequestMethod.GET)
    public Collection<Employee> getFirstFiveEmployees() {
        return employeeService.getFirstFive();
    }

    // API for posting new Employee objects from FE to DB
    @RequestMapping(path = "employees/createNew", method = RequestMethod.POST)
    public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) {
        try {
            employeeService.createNewEmployee(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new MandatoryFieldsNullException();
        }
    }

    @RequestMapping(path = "employees/findById/{id}", method = RequestMethod.GET)
    public Employee findEmployeeById(@PathVariable("id") Long id) {
         Employee employee = employeeService.findEmployeeById(id);
         if (employee==null) {
             throw new EmployeeNotFoundException();
         }
            else return employee;
    }



    //API for deleting employees by ID
    @RequestMapping(path = "employees/deleteById/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable("id") Long id) {
        try {
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new EmployeeNotFoundException();
        }
    }

    /*
    API for Patching/Updating employee objects,so basically for changing any attribute of these objects
    on this endpoint :
    /api/v1/employee/update/{id} --> 1

    The Request should contain these :
            {
                "op":"replace",
                "path":"/jobTitle",
                "value":"Senior developer"
             }

     Using this @RequestBody we can update the job of the employee object with ID 1
     */
    private Employee applyPatchToEmployee(JsonPatch patch,
                                          Employee targetEmployee,
                                          ObjectMapper objectMapper)
            throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetEmployee, JsonNode.class));
        return objectMapper.treeToValue(patched, Employee.class);
    }

    @PatchMapping(path = "employees/update/{id}", consumes = {"application/json-patch+json"})
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
                                                   @RequestBody JsonPatch patch,
                                                   ObjectMapper objectMapper) {
        try {
            objectMapper.registerModule(new JavaTimeModule());
            Employee employee = employeeService.findEmployeeById(id);
            Employee employeePatched = applyPatchToEmployee(patch, employee, objectMapper);
            employeeService.updateEmployee(employeePatched);
            return ResponseEntity.ok(employeePatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    /*
    API for Filtering and Sorting the returned Pageable of employees ,with 2 required fields, PageNumber and PageSize
    For sorting need to pass the sortDirection -->[ASC,DSC even lowercase]
    For filtering it's needed to pass an entity attribute and a value > Example : jobTitle=developer
    sortDirection to be Descending-->DSC, and sort key to be the name
    Example : <Hosting>/api/v1/employees/filter/?pageNumber=0&pageSize=2&sortDirection=DSC&sortKey=name&jobTitle=developer
    So this will return a page of 2 employee objects sorted by name at descending order,and filtering the objects where
    job title equals developer
     */
    @GetMapping("/employees/filter")
    public ResponseEntity<Page> getEmployeeParamList(
            @RequestParam(name = "id",required = false) Long id,
            @RequestParam(name = "pageNumber") Integer pageNumber,
            @RequestParam(name = "pageSize") Integer pageSize,
            @RequestParam(name = "personalNumber",required = false) Long personalNumber,
            @RequestParam(value = "emea2",required = false) String emea2,
            @RequestParam(value = "wiw",required = false) String wiw,
            @RequestParam(value = "scrum",required = false) String scrum,
            @RequestParam(value = "keyPlayer",required = false) Boolean keyPlayer,
            @RequestParam(value = "employeeName",required = false) String employeeName,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "cc",required = false) String cc,
            @RequestParam(value = "role",required = false) String role,
            @RequestParam(value = "dateOfBirth",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfBirth,
            @RequestParam(value = "privatePhone",required = false) String privatePhone,
            @RequestParam(value = "companyPhone",required = false) String companyPhone,
            @RequestParam(value = "jobTitle",required = false) String jobTitle,
            @RequestParam(value = "grade",required = false) Character grade,
            @RequestParam(value = "validFromNow",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate validFromNow,
            @RequestParam(value = "basicSalary",required = false) Integer basicSalary,
            @RequestParam(value = "employmentStatus",required = false) String employmentStatus,
            @RequestParam(value = "onboardingDate",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate onboardingDate,
            @RequestParam(value = "exitDate",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate exitDate,
            @RequestParam(value = "contractType",required = false) String contractType,
            @RequestParam(value = "calendar",required = false) String calendar,
            @RequestParam(value = "qaPandT",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate qaPandT,
            @RequestParam(value = "qaZeroOutage",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate qaZeroOutage,
            @RequestParam(value = "qaEsaris",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate qaEsaris,
            @RequestParam(value = "dataPrivacyTraining",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataPrivacyTraining,
            @RequestParam(value = "codeOfConduct",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate codeOfConduct,
            @RequestParam(value = "srp2022",required = false) String srp2022,
            @RequestParam(value = "isrp2022",required = false) String isrp2022,
            @RequestParam(value = "bonus2022",required = false) Integer bonus2022,
            @RequestParam(value = "f2f",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate f2f,
            @RequestParam(value = "tasks",required = false) String tasks,
            @RequestParam(value = "notes",required = false) String notes,
            @RequestParam(value = "computer",required = false) String computer,
            @RequestParam(value = "inventory",required = false) Long inventory,
            @RequestParam(value = "sn",required = false) String sn,
            @RequestParam(value = "hostname",required = false) String hostname,
            @RequestParam(value = "image",required = false) String image,
            @RequestParam(value = "sortKey",required = false) String sortKey,
            @RequestParam(value = "sortDirection",required = false) String sortDirection

    ) {
        //Setting the Sort.Direction based on Param,and by Sort.Direction enum
        Sort.Direction direction = (sortDirection.equalsIgnoreCase(Sort.Direction.ASC.toString()) ? Sort.Direction.ASC : Sort.Direction.DESC);

        //Creating a PageRequest based on pageNumber,PageSize,and setting sorting direction based on passed argument,and
        //field on based which the sorting's gonna be implemented
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortKey));

        return ResponseEntity.ok(employeeService.findAllByParameters(
                pageable,
                id,
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
                qaZeroOutage,
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
                image));
    }

    @GetMapping("/{field}")
    private ApiResponse<List<Employee>> getEmployeesWithSort(@PathVariable String field) {
        List<Employee> allEmployees = employeeService.findEmployeesWithSorting(field);
        return new ApiResponse<>(allEmployees.size(), allEmployees);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    private ApiResponse<Page<Employee>> getEmployeesWithPagination(@PathVariable int offset,
                                                                   @PathVariable int pageSize) {
        Page<Employee> EmployeesWithPagination = employeeService.findEmployeesWithPagination(offset, pageSize);
        return new ApiResponse<>(EmployeesWithPagination.getSize(), EmployeesWithPagination);
    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}/{sortDirection}")
    private ApiResponse<Page<Employee>> getEmployeesWithPaginationAndSort(@PathVariable int offset,
                                                                          @PathVariable int pageSize,
                                                                          @PathVariable String field,
                                                                          @PathVariable String sortDirection) {

        Page<Employee> EmployeesWithPagination = employeeService.findEmployeesWithPaginationAndSorting(offset, pageSize, field, sortDirection);
        EmployeesWithPagination.getTotalPages();
        EmployeesWithPagination.getTotalElements();
        return new ApiResponse<>(EmployeesWithPagination.getSize(), EmployeesWithPagination);
    }
}