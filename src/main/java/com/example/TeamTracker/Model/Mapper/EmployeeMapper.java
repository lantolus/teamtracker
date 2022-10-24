package com.example.TeamTracker.Model.Mapper;

import   com.example.TeamTracker.DTO.EmployeeDTO;
import com.example.TeamTracker.Model.Employee;
//Creating EmployeeMapper which will map the DTO to the Entity Employee objects
public class EmployeeMapper {
    public static EmployeeDTO toDto(Employee entity) {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId(entity.getID());
        employeeDTO.setPersonalNumber(entity.getPersonalNumber());
        employeeDTO.setEmea2(entity.getEmea2());
        employeeDTO.setWiw(entity.getWiw());
        employeeDTO.setScrum(entity.getScrum());
        employeeDTO.setKeyPlayer(entity.getKeyPlayer().booleanValue());
        employeeDTO.setEmployeeName(entity.getEmployeeName());
        employeeDTO.setName(entity.getName());
        employeeDTO.setCc(entity.getCc());
        employeeDTO.setRole(entity.getRole());
        employeeDTO.setDateOfBirth(entity.getDateOfBirth());
        employeeDTO.setPrivatePhone(entity.getPrivatePhone());
        employeeDTO.setCompanyPhone(entity.getCompanyPhone());
        employeeDTO.setJobTitle(entity.getJobTitle());
        employeeDTO.setGrade(entity.getGrade());
        employeeDTO.setValidFromNow(entity.getValidFromNow());
        employeeDTO.setBasicSalary(entity.getBasicSalary());
        employeeDTO.setEmploymentStatus(entity.getEmploymentStatus());
        employeeDTO.setOnboardingDate(entity.getOnboardingDate());
        employeeDTO.setExitDate(entity.getExitDate());
        employeeDTO.setContractType(entity.getContractType());
        employeeDTO.setCalendar(entity.getCalendar());
        employeeDTO.setQaPandT(entity.getQaPandT());
        employeeDTO.setQaZeroOutage(entity.getQaZeroOutage());
        employeeDTO.setQaEsaris(entity.getQaEsaris());
        employeeDTO.setDataPrivacyTraining(entity.getDataPrivacyTraining());
        employeeDTO.setCodeOfConduct(entity.getCodeOfConduct());
        employeeDTO.setSrp2022(entity.getSrp2022());
        employeeDTO.setIsrp2022(entity.getIsrp2022());
        employeeDTO.setBonus2022(entity.getBonus2022());
        employeeDTO.setF2f(entity.getF2f());
        employeeDTO.setTasks(entity.getTasks());
        employeeDTO.setNotes(entity.getNotes());
        employeeDTO.setComputer(entity.getComputer());
        employeeDTO.setInventory(entity.getInventory());
        employeeDTO.setSn(entity.getSn());
        employeeDTO.setHostname(entity.getHostname());
        employeeDTO.setImage(entity.getImage());

        //returning the employeeDTO based on FE request
        return employeeDTO;
    }
}
