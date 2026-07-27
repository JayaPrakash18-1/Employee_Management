package com.jp.e_m_s.Controller;

import com.jp.e_m_s.DTO.DepartmentRequestDTO;
import com.jp.e_m_s.DTO.DepartmentResponseDTO;
import com.jp.e_m_s.Service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public DepartmentResponseDTO createDepartment(
            @Valid @RequestBody DepartmentRequestDTO request) {

        return departmentService.createDepartment(request);
    }

    @GetMapping
    public List<DepartmentResponseDTO> getAllDepartments() {

        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public DepartmentResponseDTO getDepartmentById(@PathVariable Long id) {

        return departmentService.getDepartmentById(id);
    }

    @PutMapping("/{id}")
    public DepartmentResponseDTO updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequestDTO request) {

        return departmentService.updateDepartment(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {

        return departmentService.deleteDepartment(id);
    }
}