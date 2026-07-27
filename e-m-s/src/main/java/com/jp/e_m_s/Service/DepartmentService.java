package com.jp.e_m_s.Service;

import com.jp.e_m_s.Entity.Department;
import com.jp.e_m_s.Repository.DepartmentRepository;
import com.jp.e_m_s.DTO.DepartmentRequestDTO;
import com.jp.e_m_s.DTO.DepartmentResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO request) {

        Department department = new Department();

        department.setDepartmentName(request.getDepartmentName());
        department.setDepartmentCode(request.getDepartmentCode());
        department.setLocation(request.getLocation());

        Department savedDepartment = departmentRepository.save(department);

        DepartmentResponseDTO response = new DepartmentResponseDTO();

        response.setDepartmentId(savedDepartment.getDepartmentId());
        response.setDepartmentName(savedDepartment.getDepartmentName());
        response.setDepartmentCode(savedDepartment.getDepartmentCode());
        response.setLocation(savedDepartment.getLocation());

        return response;
    }

    public List<DepartmentResponseDTO> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public DepartmentResponseDTO getDepartmentById(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));

        return mapToResponse(department);
    }

    public DepartmentResponseDTO updateDepartment(Long id,
                                                  DepartmentRequestDTO request) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));

        department.setDepartmentName(request.getDepartmentName());
        department.setDepartmentCode(request.getDepartmentCode());
        department.setLocation(request.getLocation());

        return mapToResponse(departmentRepository.save(department));
    }

    public String deleteDepartment(Long id){

        Department department=departmentRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("Department not found"));

        departmentRepository.delete(department);

        return "Department Deleted Successfully";
    }

    private DepartmentResponseDTO mapToResponse(Department department){

        DepartmentResponseDTO response=new DepartmentResponseDTO();

        response.setDepartmentId(department.getDepartmentId());
        response.setDepartmentName(department.getDepartmentName());
        response.setDepartmentCode(department.getDepartmentCode());
        response.setLocation(department.getLocation());

        return response;
    }

}