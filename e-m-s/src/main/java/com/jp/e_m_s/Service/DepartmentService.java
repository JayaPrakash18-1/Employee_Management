package com.jp.e_m_s.Service;

import com.jp.e_m_s.DTO.DepartmentRequestDTO;
import com.jp.e_m_s.DTO.DepartmentResponseDTO;
import com.jp.e_m_s.Entity.Department;
import com.jp.e_m_s.Exception.DepartmentNotFoundException;
import com.jp.e_m_s.Repository.DepartmentRepository;
import com.jp.e_m_s.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository departmentRepository,
                             DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    // Create Department
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO request) {

        Department department = departmentMapper.toEntity(request);

        Department savedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponse(savedDepartment);
    }

    // Get All Departments
    public List<DepartmentResponseDTO> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponse)
                .toList();
    }

    // Get Department By Id
    public DepartmentResponseDTO getDepartmentById(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id : " + id));

        return departmentMapper.toResponse(department);
    }

    // Update Department
    public DepartmentResponseDTO updateDepartment(Long id,
                                                  DepartmentRequestDTO request) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id : " + id));

        departmentMapper.updateEntity(department, request);

        Department updatedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponse(updatedDepartment);
    }

    // Delete Department
    public String deleteDepartment(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id : " + id));

        departmentRepository.delete(department);

        return "Department Deleted Successfully";
    }

    // Search Department By Name
    public List<DepartmentResponseDTO> searchDepartmentByName(String name) {

        return departmentRepository
                .findByDepartmentNameContainingIgnoreCase(name)
                .stream()
                .map(departmentMapper::toResponse)
                .toList();
    }
}