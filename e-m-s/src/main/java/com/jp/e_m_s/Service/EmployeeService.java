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
    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toResponse)
                .toList();

    }
    public EmployeeResponseDTO getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id : " + id));

        return employeeMapper.toResponse(employee);

    }
    public EmployeeResponseDTO updateEmployee(Long id,
                                              EmployeeRequestDTO requestDTO) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id : " + id));

        Department department = departmentRepository.findById(requestDTO.getDepartmentId())
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));

        employeeMapper.updateEntity(employee, requestDTO, department);

        Employee updatedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(updatedEmployee);

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