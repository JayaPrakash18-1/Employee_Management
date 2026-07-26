package com.jp.e_m_s.Controller;

import com.jp.e_m_s.DTO.EmployeeRequestDTO;
import com.jp.e_m_s.DTO.EmployeeResponseDTO;
import com.jp.e_m_s.Entity.Employee;
import com.jp.e_m_s.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService empservice;
     EmployeeController(EmployeeService ser){
         empservice=ser;
     }
    @PostMapping
    public EmployeeResponseDTO saveEmployee(@RequestBody EmployeeRequestDTO requestDTO) {
        return empservice.saveEmployee(requestDTO);
    }
    @GetMapping
    public List<Employee> getAllEmployee(){
         return empservice.getAllEmployees();
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return empservice.getEmployeeById(id);
    }
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                   @RequestBody Employee employee) {

        return empservice.updateEmployee(id, employee);
    }
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        return empservice.deleteEmployee(id);
    }
}