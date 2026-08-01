package com.jp.e_m_s.mapper;

import com.jp.e_m_s.Entity.Department;
import com.jp.e_m_s.Entity.Employee;
import com.jp.e_m_s.DTO.EmployeeRequestDTO;
import com.jp.e_m_s.DTO.EmployeeResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequestDTO request, Department department){

        Employee employee = new Employee();

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setGender(request.getGender());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setBloodGroup(request.getBloodGroup());
        employee.setAddress(request.getAddress());
        employee.setDesignation(request.getDesignation());
        employee.setJoiningDate(request.getJoiningDate());
        employee.setSalary(request.getSalary());
        employee.setStatus(request.getStatus());

        employee.setDepartment(department);

        return employee;
    }

    public EmployeeResponseDTO toResponse(Employee employee){

        EmployeeResponseDTO response = new EmployeeResponseDTO();

        response.setEmployeeId(employee.getEmployeeId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setEmail(employee.getEmail());
        response.setPhoneNumber(employee.getPhoneNumber());
        response.setGender(employee.getGender());
        response.setDateOfBirth(employee.getDateOfBirth());
        response.setBloodGroup(employee.getBloodGroup());
        response.setAddress(employee.getAddress());
        response.setDesignation(employee.getDesignation());
        response.setJoiningDate(employee.getJoiningDate());
        response.setSalary(employee.getSalary());
        response.setStatus(employee.getStatus());
        response.setDepartmentName(employee.getDepartment().getDepartmentName());
        response.setDepartmentId(employee.getDepartment().getDepartmentId());

        return response;
    }
    public void updateEntity(Employee employee,
                             EmployeeRequestDTO request,
                             Department department){

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setGender(request.getGender());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setBloodGroup(request.getBloodGroup());
        employee.setAddress(request.getAddress());
        employee.setDesignation(request.getDesignation());
        employee.setJoiningDate(request.getJoiningDate());
        employee.setSalary(request.getSalary());
        employee.setStatus(request.getStatus());
        employee.setDepartment(department);


    }
}