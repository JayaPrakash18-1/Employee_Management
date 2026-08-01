package com.jp.e_m_s.Service;
import com.jp.e_m_s.DTO.EmployeeRequestDTO;
import com.jp.e_m_s.DTO.EmployeeResponseDTO;
import com.jp.e_m_s.Entity.Department;
import com.jp.e_m_s.Entity.Employee;
import com.jp.e_m_s.Entity.EmployeeStatus;
import com.jp.e_m_s.Entity.Gender;
import com.jp.e_m_s.Exception.EmployeeNotFoundException;
import com.jp.e_m_s.Repository.DepartmentRepository;
import com.jp.e_m_s.Repository.EmployeeRepository;
import com.jp.e_m_s.mapper.EmployeeMapper;
import com.jp.e_m_s.specification.EmployeeSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<EmployeeResponseDTO> getAllEmployees(int page, int size) {

        Page<Employee> employeePage =
                employeeRepository.findAll(PageRequest.of(page, size));

        return employeePage.map(employeeMapper::toResponse);
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
    public List<EmployeeResponseDTO> searchEmployeeByName(String name) {

        return employeeRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name)
                .stream()
                .map(employeeMapper::toResponse)
                .toList();

    }
    public EmployeeResponseDTO searchEmployeeByEmail(String email) {

        Employee employee = employeeRepository
                .findByEmailIgnoreCase(email)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with email : " + email));

        return employeeMapper.toResponse(employee);

    }
    public List<EmployeeResponseDTO> sortEmployees(String field,
                                                   String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();

        return employeeRepository.findAll(sort)
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }
    public List<EmployeeResponseDTO> filterByDepartment(Long departmentId) {

        return employeeRepository
                .findByDepartmentDepartmentId(departmentId)
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }
    public List<EmployeeResponseDTO> filterByStatus(EmployeeStatus status) {

        return employeeRepository
                .findByStatus(status)
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }
    public List<EmployeeResponseDTO> filterByGender(Gender gender) {

        return employeeRepository
                .findByGender(gender)
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }
    public List<EmployeeResponseDTO> filterBySalaryRange(
            BigDecimal minSalary,
            BigDecimal maxSalary) {

        return employeeRepository
                .findBySalaryBetween(minSalary, maxSalary)
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }
    public List<EmployeeResponseDTO> filterByJoiningDate(
            LocalDate startDate,
            LocalDate endDate) {

        return employeeRepository
                .findByJoiningDateBetween(startDate, endDate)
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }
    public List<EmployeeResponseDTO> filterBySalaryGE(BigDecimal salary){
        return employeeRepository.findBySalaryGreaterThanEqual(salary).stream().map(employeeMapper::toResponse).toList();

    }
    public List<EmployeeResponseDTO> filterBySalaryLE(BigDecimal salary){
        return employeeRepository.findBySalaryLessThanEqual(salary).stream().map(employeeMapper::toResponse).toList();
    }
    public List<EmployeeResponseDTO> filterEmployees(
            Long departmentId,
            EmployeeStatus status,
            Gender gender,
            BigDecimal minSalary,
            BigDecimal maxSalary,
            LocalDate startDate,
            LocalDate endDate) {

        Specification<Employee> specification = null;

        if (status != null) {
            specification = EmployeeSpecification.hasStatus(status);
        }

        if (departmentId != null) {
            if (specification == null) {
                specification = EmployeeSpecification.hasDepartment(departmentId);
            } else {
                specification = specification.and(
                        EmployeeSpecification.hasDepartment(departmentId));
            }
        }

        if (gender != null) {
            if (specification == null) {
                specification = EmployeeSpecification.hasGender(gender);
            } else {
                specification = specification.and(
                        EmployeeSpecification.hasGender(gender));
            }
        }

        if (minSalary != null && maxSalary != null) {
            if (specification == null) {
                specification = EmployeeSpecification.salaryBetween(
                        minSalary, maxSalary);
            } else {
                specification = specification.and(
                        EmployeeSpecification.salaryBetween(
                                minSalary, maxSalary));
            }
        }

        if (startDate != null && endDate != null) {
            if (specification == null) {
                specification = EmployeeSpecification.joiningDateBetween(
                        startDate, endDate);
            } else {
                specification = specification.and(
                        EmployeeSpecification.joiningDateBetween(
                                startDate, endDate));
            }
        }

        List<Employee> employees;

        if (specification == null) {
            employees = employeeRepository.findAll();
        } else {
            employees = employeeRepository.findAll(specification);
        }

        return employees.stream()
                .map(employeeMapper::toResponse)
                .toList();
    }
}