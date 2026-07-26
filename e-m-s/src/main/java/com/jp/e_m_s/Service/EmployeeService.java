package com.jp.e_m_s.Service;
import com.jp.e_m_s.DTO.EmployeeRequestDTO;
import com.jp.e_m_s.DTO.EmployeeResponseDTO;
import com.jp.e_m_s.Entity.Employee;
import com.jp.e_m_s.Exception.EmployeeNotFoundException;
import com.jp.e_m_s.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO requestDTO) {
        Employee employee=new Employee();
        employee.setFirstName(requestDTO.getFirstName());
        employee.setLastName(requestDTO.getLastName());
        employee.setEmail(requestDTO.getEmail());
        employee.setPhoneNumber(requestDTO.getPhoneNumber());
        employee.setGender(requestDTO.getGender());
        employee.setDateOfBirth(requestDTO.getDateOfBirth());
        employee.setBloodGroup(requestDTO.getBloodGroup());
        employee.setAddress(requestDTO.getAddress());
        employee.setDesignation(requestDTO.getDesignation());
        employee.setJoiningDate(requestDTO.getJoiningDate());
        employee.setSalary(BigDecimal.valueOf(requestDTO.getSalary()));
        employee.setStatus(requestDTO.getStatus());
        Employee savedEmployee=employeeRepository.save(employee);
        EmployeeResponseDTO response = new EmployeeResponseDTO();

        response.setEmployeeId(savedEmployee.getEmployeeId());
        response.setFirstName(savedEmployee.getFirstName());
        response.setLastName(savedEmployee.getLastName());
        response.setEmail(savedEmployee.getEmail());
        response.setPhoneNumber(savedEmployee.getPhoneNumber());
        response.setGender(savedEmployee.getGender());
        response.setDateOfBirth(savedEmployee.getDateOfBirth());
        response.setBloodGroup(savedEmployee.getBloodGroup());
        response.setAddress(savedEmployee.getAddress());
        response.setDesignation(savedEmployee.getDesignation());
        response.setJoiningDate(savedEmployee.getJoiningDate());
        response.setSalary(savedEmployee.getSalary());
        response.setStatus(savedEmployee.getStatus());

        return response;


    }
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public Employee getEmployeeById(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id : " + id));
    }
    public Employee updateEmployee(Long id, Employee updatedEmployee) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {

            Employee employee = optionalEmployee.get();

            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setPhoneNumber(updatedEmployee.getPhoneNumber());
            employee.setGender(updatedEmployee.getGender());
            employee.setDateOfBirth(updatedEmployee.getDateOfBirth());
            employee.setBloodGroup(updatedEmployee.getBloodGroup());
            employee.setAddress(updatedEmployee.getAddress());
            employee.setDesignation(updatedEmployee.getDesignation());
            employee.setJoiningDate(updatedEmployee.getJoiningDate());
            employee.setSalary(updatedEmployee.getSalary());
            employee.setStatus(updatedEmployee.getStatus());

            return employeeRepository.save(employee);
        }

        return null;
    }
    public String deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id : " + id));

        employeeRepository.delete(employee);

        return "Employee Deleted Successfully";
    }
}