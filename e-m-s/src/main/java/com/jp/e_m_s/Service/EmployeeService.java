package com.jp.e_m_s.Service;
import com.jp.e_m_s.DTO.EmployeeRequestDTO;
import com.jp.e_m_s.DTO.EmployeeResponseDTO;
import com.jp.e_m_s.Entity.Department;
import com.jp.e_m_s.Entity.Employee;
import com.jp.e_m_s.Exception.EmployeeNotFoundException;
import com.jp.e_m_s.Repository.DepartmentRepository;
import com.jp.e_m_s.Repository.EmployeeRepository;
import com.jp.e_m_s.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository,DepartmentRepository departmentRepository,EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository=departmentRepository;
        this.employeeMapper=employeeMapper;
    }
    public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO requestDTO) {

        // Find Department
        Department department = departmentRepository.findById(requestDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Employee employee = employeeMapper.toEntity(requestDTO, department);

        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(savedEmployee);

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