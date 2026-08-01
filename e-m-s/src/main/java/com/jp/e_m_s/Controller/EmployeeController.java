package com.jp.e_m_s.Controller;

import com.jp.e_m_s.DTO.EmployeeRequestDTO;
import com.jp.e_m_s.DTO.EmployeeResponseDTO;
import com.jp.e_m_s.Entity.Employee;
import com.jp.e_m_s.Entity.EmployeeStatus;
import com.jp.e_m_s.Entity.Gender;
import com.jp.e_m_s.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService empservice;
     EmployeeController(EmployeeService ser){
         empservice=ser;
     }
    @PostMapping
    public EmployeeResponseDTO saveEmployee(@Valid @RequestBody EmployeeRequestDTO requestDTO) {
        return empservice.saveEmployee(requestDTO);
    }
    @GetMapping
    public Page<EmployeeResponseDTO> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return empservice.getAllEmployees(page, size);
    }
    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable Long id) {
        return empservice.getEmployeeById(id);
    }
    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(@PathVariable Long id,
                                   @RequestBody EmployeeRequestDTO employee) {

        return empservice.updateEmployee(id, employee);
    }
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        return empservice.deleteEmployee(id);
    }
    @GetMapping("/search/name")
    public List<EmployeeResponseDTO> searchEmployeeByName(
            @RequestParam String name) {

        return empservice.searchEmployeeByName(name);

    }
    @GetMapping("/search/email")
    public EmployeeResponseDTO searchEmployeeByEmail(
            @RequestParam String email) {

        return empservice.searchEmployeeByEmail(email);

    }
    @GetMapping("/sort")
    public List<EmployeeResponseDTO> sortEmployees(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {

        return empservice.sortEmployees(field, direction);
    }
    @GetMapping("/filter/department/{departmentId}")
    public List<EmployeeResponseDTO> filterByDepartment(
            @PathVariable Long departmentId) {

        return empservice.filterByDepartment(departmentId);
    }
    @GetMapping("/filter/status/{status}")
    public List<EmployeeResponseDTO> filterByStatus(
            @PathVariable EmployeeStatus status) {

        return empservice.filterByStatus(status);
    }
    @GetMapping("/filter/gender/{gender}")
    public List<EmployeeResponseDTO> filterByGender(
            @PathVariable Gender gender) {

        return empservice.filterByGender(gender);
    }
    @GetMapping("/filter/salary")
    public List<EmployeeResponseDTO> filterBySalaryRange(
            @RequestParam BigDecimal minSalary,
            @RequestParam BigDecimal maxSalary) {

        return empservice.filterBySalaryRange(minSalary, maxSalary);
    }
    @GetMapping("/filter/joining-date")
    public List<EmployeeResponseDTO> filterByJoiningDate(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        return empservice.filterByJoiningDate(startDate, endDate);
    }
    @GetMapping("/filter/salaryGE/{salary}")
    public List<EmployeeResponseDTO> filterBySalaryG(@PathVariable BigDecimal salary){
         return empservice.filterBySalaryGE(salary);
    }
    @GetMapping("/filter/salaryLE/{salary}")
    public List<EmployeeResponseDTO> filterBySalaryL(@PathVariable BigDecimal salary){
         return empservice.filterBySalaryLE(salary);
    }
    @GetMapping("/filter")
    public List<EmployeeResponseDTO> filterEmployees(

            @RequestParam(required = false) Long departmentId,

            @RequestParam(required = false) EmployeeStatus status,

            @RequestParam(required = false) Gender gender,

            @RequestParam(required = false) BigDecimal minSalary,

            @RequestParam(required = false) BigDecimal maxSalary,

            @RequestParam(required = false) LocalDate startDate,

            @RequestParam(required = false) LocalDate endDate) {

         return empservice.filterEmployees(
                departmentId,
                status,
                gender,
                minSalary,
                maxSalary,
                startDate,
                endDate
                );
    }
}