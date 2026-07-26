package com.jp.e_m_s.Service;
import com.jp.e_m_s.Entity.Employee;
import com.jp.e_m_s.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Employee saveEmployee(Employee employee) {
          return employeeRepository.save(employee);

    }
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public Employee getEmployeeById(Long id) {

        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        }

        return null;
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

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            employeeRepository.deleteById(id);
            return "Employee Deleted Successfully";
        }

        return "Employee Not Found";
    }
}